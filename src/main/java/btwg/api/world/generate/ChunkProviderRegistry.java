package btwg.api.world.generate;

import btwg.api.configuration.WorldData;
import net.minecraft.src.*;

import java.util.HashMap;
import java.util.Map;

public class ChunkProviderRegistry {
    public static final ResourceLocation VANILLA = new ResourceLocation("vanilla");
    public static final ResourceLocation VANILLA_NETHER = new ResourceLocation("vanilla_nether");
    public static final ResourceLocation VANILLA_END = new ResourceLocation("vanilla_end");

    private static final Map<ResourceLocation, ChunkProviderProvider<?>> CHUNK_PROVIDERS = new HashMap<>();

    static {
        registerChunkProvider(VANILLA, (world, seed, mapFeaturesEnebaled, worldData) -> new ChunkProviderGenerate(world, seed, mapFeaturesEnebaled));
        registerChunkProvider(VANILLA_NETHER, (world, seed, mapFeaturesEnebaled, worldData) -> new ChunkProviderHell(world, seed));
        registerChunkProvider(VANILLA_END, (world, seed, mapFeaturesEnebaled, worldData) -> new ChunkProviderEnd(world, seed));

        registerChunkProvider(ChunkProvider.ID, ChunkProvider::new);
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
