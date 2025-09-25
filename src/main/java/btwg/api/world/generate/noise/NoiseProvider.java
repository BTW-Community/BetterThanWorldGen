package btwg.api.world.generate.noise;

import net.minecraft.src.BiomeGenBase;

public abstract class NoiseProvider {
    public final long seed;

    public NoiseProvider(long seed) {
        this.seed = seed;
    }

    public abstract double[] getTerrainNoise(int chunkX, int chunkZ);
    public abstract BiomeGenBase[] getBiomes(int chunkX, int chunkZ);

    public byte getSeaLevel() {
        return 63;
    }
}
