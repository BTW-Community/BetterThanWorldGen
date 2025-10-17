package btwg.api.client;

import btw.client.gui.debug.BTWDebugRegistry;
import btw.client.gui.debug.DebugInfoSection;
import btw.client.gui.debug.DebugRegistryUtils;
import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeNoiseVector;
import btwg.api.biome.CaveBiome;
import btwg.api.world.generate.ChunkProvider;
import btwg.api.world.generate.noise.NoiseProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ChunkProviderServer;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public abstract class DebugRegistry {
    public static final ResourceLocation BIOME_DEBUG_ID = new ResourceLocation("btwg", "biome_debug");
    public static final ResourceLocation CAVE_BIOME_DEBUG_ID = new ResourceLocation("btwg", "cave_biome_debug");

    public static void init() {
        DebugInfoSection biomeSection = DebugRegistryUtils.registerSection(BIOME_DEBUG_ID, DebugRegistryUtils.Side.LEFT);
        biomeSection.orderSection(BTWDebugRegistry.extendedGeneralSectionID);

        biomeSection.addEntry(DebugRegistry::addBiomeName);
        biomeSection.addEntry(DebugRegistry::addBiomeNoiseData);

        DebugInfoSection caveBiomeSection = DebugRegistryUtils.registerSection(CAVE_BIOME_DEBUG_ID, DebugRegistryUtils.Side.LEFT);
        caveBiomeSection.orderSection(BIOME_DEBUG_ID);

        // TODO: Fix cave debug breaking world gen
        //caveBiomeSection.addEntry(DebugRegistry::addCaveBiomeData);
    }

    private static Optional<String> addBiomeName(Minecraft mc, boolean ex) {
        return Optional.of(String.format(
                "Current Biome: §a%s\n",
                mc.theWorld.getBiomeGenForCoords((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ).biomeName
        ));
    }

    private static Optional<String> addBiomeNoiseData(Minecraft mc, boolean ex) {
        if (!MinecraftServer.getIsServer()) {
            ChunkProviderServer serverChunkProvider = (ChunkProviderServer) MinecraftServer.getServer().worldServerForDimension(0).getChunkProvider();
            IChunkProvider chunkProvider = serverChunkProvider.getCurrentChunkProvider();

            if (chunkProvider instanceof ChunkProvider btwgProvider) {
                var currentBiome = mc.theWorld.getBiomeGenForCoords((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);

                BiomeNoiseVector vector = btwgProvider.getNoiseProvider().getBiomeVector((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);
                String vectorText = String.format("T: §a%.2f§f, H: §a%.2f§f, C: §a%.2f§f, E: §a%.2f§f, W: §a%.2f§f",
                        vector.temperature(), vector.humidity(), vector.continentalness(), vector.erosion(), vector.weirdness());

                StringBuilder infoText = new StringBuilder(String.format("§nBiome Noise:§r\n%s\n", vectorText));

                if (currentBiome instanceof BTWGBiome btwgBiome) {
                    BiomeNoiseVector target = btwgBiome.noiseTarget.target();
                    String targetText = String.format("T: §a%.2f§f, H: §a%.2f§f, C: §a%.2f§f, E: §a%.2f§f, W: §a%.2f§f",
                            target.temperature(), target.humidity(), target.continentalness(), target.erosion(), target.weirdness());

                    infoText.append(String.format("§nCurrent Biome Target:§r\n%s\n", targetText));
                }

                record BiomeDistance(BTWGBiome biome, double distance) {}
                var distances = new ArrayList<BiomeDistance>();
                for (BTWGBiome biome : BTWGBiome.biomeList) {
                    distances.add(new BiomeDistance(biome, biome.noiseTarget.distanceSqFromTarget(vector)));
                }
                distances.sort(Comparator.comparingDouble(BiomeDistance::distance));
                distances = new ArrayList<>(distances.subList(0, Math.min(5, distances.size())));

                infoText.append("\n§nWeighted Distances:§r\n");

                for (BiomeDistance distance : distances) {
                    infoText.append(String.format("%s: §a%.2f§f\n", distance.biome().biomeName, distance.distance()));
                }

                return Optional.of(infoText.toString());
            }
        }
        return Optional.empty();
    }

    private static Optional<String> addCaveBiomeData(Minecraft mc, boolean ex) {
        if (!MinecraftServer.getIsServer()) {
            ChunkProviderServer serverChunkProvider = (ChunkProviderServer) MinecraftServer.getServer().worldServerForDimension(0).getChunkProvider();
            IChunkProvider chunkProvider = serverChunkProvider.getCurrentChunkProvider();

            if (chunkProvider instanceof ChunkProvider btwgProvider) {
                NoiseProvider noiseProvider = btwgProvider.getNoiseProvider();

                StringBuilder infoText = new StringBuilder("§nCave Biomes§r\n");

                Pair<CaveBiome, CaveBiome> caveBiomes = noiseProvider.getCaveBiomes((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);

                infoText.append(String.format("Shallow: §a%s\n§f", caveBiomes.getLeft().getName()));
                infoText.append(String.format("Deep: §a%s\n§f", caveBiomes.getRight().getName()));

                double[] heightmap = noiseProvider.getHeightmap((int) mc.thePlayer.posX >> 4, (int) mc.thePlayer.posZ >> 4);

                int i = (int) mc.thePlayer.posX & 15;
                int k = (int) mc.thePlayer.posZ & 15;

                boolean isDeep = mc.thePlayer.posY <= heightmap[i * 16 + k] * NoiseProvider.TERRAIN_SCALE / 2;
                String caveType = isDeep ? "Deep" : "Shallow";

                infoText.append(String.format("\nDepth: §a%s\n§f", caveType));

                return Optional.of(infoText.toString());
            }
        }
        return Optional.empty();
    }
}
