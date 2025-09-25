package btwg.api.world.generate.noise;

public class ModernNoiseProvider extends NoiseProvider {
    public ModernNoiseProvider(long seed) {
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
