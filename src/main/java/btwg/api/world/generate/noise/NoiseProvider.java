package btwg.api.world.generate.noise;

public abstract class NoiseProvider {
    public final long seed;

    public NoiseProvider(long seed) {
        this.seed = seed;
    }

    public abstract double[] getTerrainNoise(int chunkX, int chunkZ);
    public abstract int[] getBiomes(int chunkX, int chunkZ);
}
