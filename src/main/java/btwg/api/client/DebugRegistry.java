package btwg.api.client;

import btw.client.gui.debug.BTWDebugRegistry;
import btw.client.gui.debug.DebugInfoSection;
import btw.client.gui.debug.DebugRegistryUtils;
import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeNoiseVector;
import btwg.api.world.generate.ChunkProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ChunkProviderServer;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.ResourceLocation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public abstract class DebugRegistry {
    public static final ResourceLocation BIOME_DEBUG_ID = new ResourceLocation("btwg", "biome_debug");

    public static void init() {
        DebugInfoSection biomeSection = DebugRegistryUtils.registerSection(BIOME_DEBUG_ID, DebugRegistryUtils.Side.LEFT);
        biomeSection.orderSection(BTWDebugRegistry.extendedGeneralSectionID);

        biomeSection.addEntry((mc, ex) -> Optional.of(String.format("Current Biome: §a%s\n", mc.theWorld.getBiomeGenForCoords((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ).biomeName)));
        biomeSection.addEntry((mc, ex) -> {
            if (!MinecraftServer.getIsServer()) {
                ChunkProviderServer serverChunkProvider = (ChunkProviderServer) MinecraftServer.getServer().worldServerForDimension(0).getChunkProvider();
                IChunkProvider chunkProvider = serverChunkProvider.getCurrentChunkProvider();

                if (chunkProvider instanceof ChunkProvider btwgProvider) {
                    var currentBiome = mc.theWorld.getBiomeGenForCoords((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);

                    BiomeNoiseVector vector = btwgProvider.getNoiseProvider().getBiomeVector((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ);
                    String vectorText = String.format("T: §a%.2f§f, H: §a%.2f§f, C: §a%.2f§f, E: §a%.2f§f, W: §a%.2f§f", vector.temperature(), vector.humidity(), vector.continentalness(), vector.erosion(), vector.weirdness());

                    StringBuilder infoText = new StringBuilder(String.format("§nBiome Noise:§r\n%s\n", vectorText));

                    if (currentBiome instanceof BTWGBiome btwgBiome) {
                        BiomeNoiseVector target = btwgBiome.noiseTarget.target();
                        String targetText = String.format("T: §a%.2f§f, H: §a%.2f§f, C: §a%.2f§f, E: §a%.2f§f, W: §a%.2f§f", target.temperature(), target.humidity(), target.continentalness(), target.erosion(), target.weirdness());
                        
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
        });
    }
}
