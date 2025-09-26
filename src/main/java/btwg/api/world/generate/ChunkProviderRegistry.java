package btwg.api.world.generate;

import btwg.api.configuration.WorldData;
import btwg.api.world.generate.noise.LegacyNoiseProvider;
import btwg.api.world.generate.noise.ModernNoiseProvider;
import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

public class ChunkProviderRegistry {
    public static final ResourceLocation VANILLA = new ResourceLocation("vanilla");
    public static final ResourceLocation VANILLA_NETHER = new ResourceLocation("vanilla_nether");
    public static final ResourceLocation VANILLA_END = new ResourceLocation("vanilla_end");

    public static final ResourceLocation BTWG_LEGACY = new ResourceLocation("btwg_legacy");
    public static final ResourceLocation BTWG = new ResourceLocation("btwg");

    private static final Map<ResourceLocation, ChunkProviderProvider<?>> CHUNK_PROVIDERS = new HashMap<>();

    static {
        registerChunkProvider(VANILLA, (world, seed, mapFeaturesEnabled, worldData) -> new ChunkProviderGenerate(world, seed, mapFeaturesEnabled));
        registerChunkProvider(VANILLA_NETHER, (world, seed, mapFeaturesEnabled, worldData) -> new ChunkProviderHell(world, seed));
        registerChunkProvider(VANILLA_END, (world, seed, mapFeaturesEnabled, worldData) -> new ChunkProviderEnd(world, seed));

        registerChunkProvider(BTWG_LEGACY, (world, seed, mapFeaturesEnabled, worldData) -> new ChunkProvider<>(world, new LegacyNoiseProvider(world, seed), mapFeaturesEnabled));
        registerChunkProvider(BTWG, (world, seed, mapFeaturesEnabled, worldData) -> new ChunkProvider<>(world, new ModernNoiseProvider(seed), mapFeaturesEnabled));
    }

    public static <T> void  registerChunkProvider(ResourceLocation id, ChunkProviderProvider<T> providerProvider) {
        CHUNK_PROVIDERS.put(id, providerProvider);
    }

    public static IChunkProvider createChunkProvider(ResourceLocation id, World world, long seed, boolean mapFeaturesEnabled, WorldData worldData) {
        return (IChunkProvider) CHUNK_PROVIDERS.get(id).create(world, seed, mapFeaturesEnabled, worldData);
    }

    @FunctionalInterface
    public interface ChunkProviderProvider<T> {
        T create(World world, long seed, boolean mapFeaturesEnabled, WorldData worldData);
    }
}
