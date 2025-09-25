package btwg.api.world.generate.noise;

import net.minecraft.src.BiomeGenBase;

public class ModernNoiseProvider extends NoiseProvider {
    public ModernNoiseProvider(long seed) {
        super(seed);
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        return new double[0];
    }

    @Override
    public BiomeGenBase[] getBiomes(int chunkX, int chunkZ) {
        return new BiomeGenBase[0];
    }
}
