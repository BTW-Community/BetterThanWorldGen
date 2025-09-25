package btwg.api.world.generate.noise;

import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import net.minecraft.src.BiomeGenBase;

import java.util.Random;

public class ModernNoiseProvider extends NoiseProvider {
    public static final int CONTINENTALNESS_SCALE = 1024;
    public static final int EROSION_SCALE = 512;
    public static final int RIDGES_SCALE = 512;
    public static final int WEIRDNESS_SCALE = 256;

    public static final int TEMPERATURE_SCALE = 1024;
    public static final int HUMIDITY_SCALE = 512;

    public static final int TERRAIN_SCALE = 64;

    public static final int NOISE_SUBSCALE = 4;
    public static final int NOISE_SIZE = NOISE_SUBSCALE + 1;

    private final OpenSimplexOctavesFast continentalnessGenerator;
    private final OpenSimplexOctavesFast erosionGenerator;
    private final OpenSimplexOctavesFast ridgesGenerator;
    private final OpenSimplexOctavesFast weirdnessGenerator;

    private final OpenSimplexOctavesFast temperatureGenerator;
    private final OpenSimplexOctavesFast humidityGenerator;

    private final OpenSimplexOctavesFast terrainGenerator;

    private double[] continentalness;
    private double[] erosion;
    private double[] ridges;
    private double[] weirdness;

    private double[] temperature;
    private double[] humidity;

    private double[] terrain;

    public ModernNoiseProvider(long seed) {
        super(seed);

        Random rand = new Random(seed);

        this.continentalnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);
        this.erosionGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);
        this.ridgesGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);
        this.weirdnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);

        this.temperatureGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);
        this.humidityGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 4);

        this.terrainGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), 8);
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        this.initNoiseFields(chunkX, chunkZ);

        return new double[0];
    }

    private void initNoiseFields(int chunkX, int chunkZ) {
        this.continentalness = getNoise2D(this.continentalnessGenerator, this.continentalness, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, CONTINENTALNESS_SCALE);
        this.erosion = getNoise2D(this.erosionGenerator, this.erosion, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, EROSION_SCALE);
        this.ridges = getNoise2D(this.ridgesGenerator, this.ridges, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, RIDGES_SCALE);
        this.weirdness = getNoise2D(this.weirdnessGenerator, this.weirdness, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, WEIRDNESS_SCALE);

        this.terrain = getNoise2D(this.terrainGenerator, this.terrain, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, TERRAIN_SCALE);
    }
    
    private double[] interpolate(double[] noiseArray, int sizeX, int sizeZ, double n00, double n01, double n10, double n11) {
        if (noiseArray == null || noiseArray.length < sizeX * sizeZ) {
            noiseArray = new double[sizeX * sizeZ];
        }

        for (int i = 0; i < sizeX; i++) {
            for (int k = 0; k < sizeZ; k++) {
                double x = (double) i / (sizeX - 1);
                double z = (double) k / (sizeZ - 1);

                double nx0 = n00 * (1 - x) + n10 * x;
                double nx1 = n01 * (1 - x) + n11 * x;
                double nz = nx0 * (1 - z) + nx1 * z;

                noiseArray[i * sizeX + k] = nz;
            }
        }

        return noiseArray;
    }

    private double[] getNoise2D(OpenSimplexOctavesFast generator, double[] noiseArray, int chunkX, int chunkZ, int sizeX, int sizeZ, int scale) {
        if  (noiseArray == null || noiseArray.length < sizeX * sizeZ) {
            noiseArray = new double[sizeX * sizeZ];
        }

        int x = chunkX * 16;
        int z = chunkZ * 16;

        for (int i = 0; i < sizeX; ++i) {
            for (int k = 0; k < sizeZ; ++k) {
                double noise = generator.noise2(x + i, z + k, 1D / scale);
                noiseArray[i * sizeX + k] = noise;
            }
        }

        return noiseArray;
    }

    private double[] getNoise3D(OpenSimplexOctavesFast generator, double[] noiseArray, int chunkX, int y, int chunkZ, int sizeX, int sizeY, int sizeZ, int scale) {
        if  (noiseArray == null || noiseArray.length < sizeX * sizeY * sizeZ) {
            noiseArray = new double[sizeX * sizeY * sizeZ];
        }

        int x = chunkX * 16;
        int z = chunkZ * 16;

        for (int i = 0; i < sizeX; ++i) {
            for (int k = 0; k < sizeZ; ++k) {
                for (int j = 0; j < sizeY; ++j) {
                    double noise = generator.noise3(x + i, y + j, z + k, 1D / scale);
                    noiseArray[(i * sizeX + k) * sizeZ + j] = noise;
                }
            }
        }

        return noiseArray;
    }

    @Override
    public BiomeGenBase[] getBiomes(int chunkX, int chunkZ) {
        this.temperature = getNoise2D(this.temperatureGenerator, this.temperature, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, TEMPERATURE_SCALE);
        this.humidity = getNoise2D(this.humidityGenerator, this.humidity, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, HUMIDITY_SCALE);

        return new BiomeGenBase[0];
    }
}
