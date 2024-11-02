package btwg.mixin;

import btwg.api.world.WorldTypeInterface;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldType.class)
public class WorldTypeMixin implements WorldTypeInterface {
    private ChunkProviderProvider overworldChunkProvider = (world, seed, featuresEnabled, generatorOptions) -> new ChunkProviderGenerate(world, seed, featuresEnabled);
    private ChunkProviderProvider netherChunkProvider = (world, seed, featuresEnabled, generatorOptions) -> new ChunkProviderHell(world, seed);
    private ChunkProviderProvider endChunkProvider = (world, seed, featuresEnabled, generatorOptions) -> new ChunkProviderEnd(world, seed);
    
    @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean featuresEnabled, String generatorOptions) {
        return overworldChunkProvider.provideChunkProvider(world, seed, featuresEnabled, generatorOptions);
    }
    
    @Override
    public WorldTypeInterface setChunkProviderOverworld(ChunkProviderProvider chunkProvider) {
        this.overworldChunkProvider = chunkProvider;
        return this;
    }
    
    @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, boolean featuresEnabled, String generatorOptions) {
        return netherChunkProvider.provideChunkProvider(world, seed, featuresEnabled, generatorOptions);
    }
    
    @Override
    public WorldTypeInterface setChunkProviderNether(ChunkProviderProvider chunkProvider) {
        this.netherChunkProvider = chunkProvider;
        return this;
    }
    
    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed, boolean featuresEnabled, String generatorOptions) {
        return endChunkProvider.provideChunkProvider(world, seed, featuresEnabled, generatorOptions);
    }
    
    @Override
    public WorldTypeInterface setChunkProviderEnd(ChunkProviderProvider chunkProvider) {
        this.endChunkProvider = chunkProvider;
        return this;
    }
}
