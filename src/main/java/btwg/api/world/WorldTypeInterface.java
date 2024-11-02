package btwg.api.world;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;

public interface WorldTypeInterface {
    IChunkProvider getChunkProviderOverworld(World world, long seed, boolean featuresEnabled, String generatorOptions);
    WorldTypeInterface setChunkProviderOverworld(ChunkProviderProvider chunkProvider);
    
    IChunkProvider getChunkProviderNether(World world, long seed, boolean featuresEnabled, String generatorOptions);
    WorldTypeInterface setChunkProviderNether(ChunkProviderProvider chunkProvider);
    
    IChunkProvider getChunkProviderEnd(World world, long seed, boolean featuresEnabled, String generatorOptions);
    WorldTypeInterface setChunkProviderEnd(ChunkProviderProvider chunkProvider);
    
    @FunctionalInterface
    interface ChunkProviderProvider {
        IChunkProvider provideChunkProvider(World world, long seed, boolean featuresEnabled, String generatorOptions);
    }
}
