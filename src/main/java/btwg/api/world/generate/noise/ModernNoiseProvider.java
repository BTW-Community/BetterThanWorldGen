package btwg.api.world.generate.noise;

import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import btwg.api.world.generate.noise.spline.Key;
import btwg.api.world.generate.noise.spline.Spline;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;

import java.util.Arrays;
import java.util.Random;

public class ModernNoiseProvider extends NoiseProvider {
    public static final int CONTINENTALNESS_SCALE = 1024;
    public static final int EROSION_SCALE = 512;
    public static final int RIDGES_SCALE = 512;
    public static final int WEIRDNESS_SCALE = 256;

    public static final int TEMPERATURE_SCALE = 1024;
    public static final int HUMIDITY_SCALE = 512;

    public static final int TERRAIN_SCALE = 64;

    // TODO: fix interpolation
    public static final int NOISE_SUBSCALE = 16;
    public static final int NOISE_SIZE = NOISE_SUBSCALE + 1;

    public static final int TOTAL_Y_HEIGHT = 256;

    private static final Spline continentalnessSpline = Spline.of(
            new Key(-1.50, 40.0 / TOTAL_Y_HEIGHT),  // start
            new Key(-1.00, 50.0 / TOTAL_Y_HEIGHT),  // deep ocean
            new Key(-0.40, 85.0 / TOTAL_Y_HEIGHT),  // shallow shelf
            new Key(0.00, 110.0 / TOTAL_Y_HEIGHT),  // plains
            new Key(0.60, 150.0 / TOTAL_Y_HEIGHT),  // highlands
            new Key(1.00, 180.0 / TOTAL_Y_HEIGHT),  // interior plateaus
            new Key(1.50, 210.0 / TOTAL_Y_HEIGHT)   // end
    );

    private static final int DRIVER_OCTAVES = 4;
    private static final int TERRAIN_OCTAVES = 3;

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

    private double[] interpolatedContinentalness;
    private double[] interpolatedErosion;
    private double[] interpolatedRidges;
    private double[] interpolatedWeirdness;

    private double[] interpolatedTemperature;
    private double[] interpolatedHumidity;

    private double[] interpolatedTerrain;

    public ModernNoiseProvider(long seed) {
        super(seed);

        Random rand = new Random(seed);

        this.continentalnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.erosionGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.ridgesGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.weirdnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);

        this.temperatureGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.humidityGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);

        this.terrainGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), TERRAIN_OCTAVES);
    }

    public static double smoothstep(double edge0, double edge1, double x) {
        double t = clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
        return t * t * (3.0 - 2.0 * t);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        this.initNoiseFields(chunkX, chunkZ);

        double[] terrain = new double[16 * 16 * TOTAL_Y_HEIGHT];

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                double continentalness = this.interpolatedContinentalness[idx(i, k, 16)];
                int height = (int) Math.round(continentalness * TOTAL_Y_HEIGHT);

                for (int j = 0; j < TOTAL_Y_HEIGHT; j++) {
                    int idx = idx(i, j, k, TOTAL_Y_HEIGHT, 16);

                    double terrainValue = this.terrain[idx];

                    int distance = j - height;

                    int amplitude = 10;
                    int innerFadeRadius = 4;
                    int outerFadeRadius = 40;

                    double fade = 1 - smoothstep(innerFadeRadius, outerFadeRadius, Math.abs(distance));
                    double offset = amplitude * terrainValue * fade;

                    double density = -distance + offset;
                    terrain[idx] = density;
                }
            }
        }

        return terrain;
    }

    private void initNoiseFields(int chunkX, int chunkZ) {
        this.continentalness = getNoise2D(this.continentalnessGenerator, this.continentalness, chunkX, chunkZ, 16, 16, 1, 1, CONTINENTALNESS_SCALE);
        // TODO: fix interpolation
        this.interpolatedContinentalness = this.continentalness;
        this.transformNoise(this.interpolatedContinentalness, continentalnessSpline);

        this.terrain = getNoise3D(this.terrainGenerator, this.terrain, chunkX, 0, chunkZ, 16, TOTAL_Y_HEIGHT, 16, TERRAIN_SCALE);
    }

    private double[] getNoise2D(OpenSimplexOctavesFast generator, double[] noiseArray, int chunkX, int chunkZ, int sizeX, int sizeZ, int stepX, int stepZ, int scale) {
        if (noiseArray == null || noiseArray.length < sizeX * sizeZ) {
            noiseArray = new double[sizeX * sizeZ];
        }

        int x = chunkX * 16;
        int z = chunkZ * 16;

        for (int i = 0; i < sizeX; ++i) {
            for (int k = 0; k < sizeZ; ++k) {
                double noise = generator.noise2(x + i * stepX, z + k * stepZ, 1D / scale);
                noiseArray[idx(i, k, sizeZ)] = noise;
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
                    noiseArray[idx(i, j, k, sizeY, sizeZ)] = noise;
                }
            }
        }

        return noiseArray;
    }

    private void transformNoise(double[] noise, Spline spline) {
        for (int i = 0; i < noise.length; i++) {
            noise[i] = spline.eval(noise[i]);
        }
    }

    private int idx(int x, int z, int sizeZ) {
        return x * sizeZ + z;
    }

    private int idx(int x, int y, int z, int sizeY, int sizeZ) {
        return x * sizeZ * sizeY + z * sizeY + y;
    }

    @Override
    public BiomeGenBase[] getBiomes(int chunkX, int chunkZ) {
        //this.temperature = getNoise2D(this.temperatureGenerator, this.temperature, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, TEMPERATURE_SCALE);
        //this.humidity = getNoise2D(this.humidityGenerator, this.humidity, chunkX, chunkZ, NOISE_SIZE, NOISE_SIZE, HUMIDITY_SCALE);

        BiomeGenBase[] biomes = new BiomeGenBase[256];
        Arrays.fill(biomes, BiomeConfiguration.RAINFOREST);
        return biomes;
    }

    @Override
    public byte getSeaLevel() {
        return (int) ((100) / 256.0 * TOTAL_Y_HEIGHT);
    }
}
