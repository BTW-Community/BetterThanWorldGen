package btwg.api.world.generate.noise;

public class LegacyNoiseProvider extends NoiseProvider {
    public LegacyNoiseProvider(long seed) {
        super(seed);
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        return new double[0];
    }

    @Override
    public int[] getBiomes(int chunkX, int chunkZ) {
        return new int[0];
    }
}
