package btwg.api.world.generate.noise;

import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import btwg.api.world.generate.noise.spline.Key;
import btwg.api.world.generate.noise.spline.Spline;
import btwg.mod.BiomeConfiguration;
import net.minecraft.src.BiomeGenBase;

import java.util.Arrays;
import java.util.Random;

public class ModernNoiseProvider extends NoiseProvider {
    public static final int CONTINENTALNESS_SCALE = 4096;
    public static final int EROSION_SCALE = 2048;
    public static final int RIDGES_SCALE = 384;
    public static final int WEIRDNESS_SCALE = 256;

    public static final int TEMPERATURE_SCALE = 1024;
    public static final int HUMIDITY_SCALE = 512;

    public static final int TERRAIN_SCALE = 384;

    private static final int DRIVER_OCTAVES = 4;
    private static final int TERRAIN_OCTAVES = 6;

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

    private static final Spline erosionToThickness = Spline.of(
            new Key(-1.0, 8),  // low erosion: sharp
            new Key(-0.5, 10),
            new Key( 0.0, 12),
            new Key( 0.5, 14),
            new Key( 1.0, 16)   // high erosion: soft
    );

    private static final Spline erosionToAmplitude = Spline.of(
            new Key(-1.0, 10),
            new Key(-0.5, 8),
            new Key( 0.0,  6),
            new Key( 0.5,  4),
            new Key( 1.0,  3)
    );

    private static final Spline erosionToHeightBias = Spline.of(
            new Key(-1.0,  16),
            new Key(-0.5,  8),
            new Key( 0.0,  4),
            new Key( 0.5,  2),
            new Key( 1.0,  1)
    );

    private final OpenSimplexOctavesFast continentalnessGenerator;
    private final OpenSimplexOctavesFast erosionGenerator;
    private final OpenSimplexOctavesFast ridgesGenerator;
    private final OpenSimplexOctavesFast weirdnessGenerator;

    private final OpenSimplexOctavesFast temperatureGenerator;
    private final OpenSimplexOctavesFast humidityGenerator;

    private final OpenSimplexOctavesFast terrainGenerator;

    private double[] continentalness;

    private double[] erosion;
    private double[] thickness;
    private double[] amplitude;
    private double[] heightBias;

    private double[] ridges;
    private double[] weirdness;

    private double[] temperature;
    private double[] humidity;

    private double[] terrain;


    public ModernNoiseProvider(long seed) {
        super(seed);

        Random rand = new Random(seed);

        this.continentalnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES + 2);
        this.erosionGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES + 2);
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

    private static double lerp(double start, double stop, double amount) {
        return start + (stop - start) * amount;
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        this.initNoiseFields(chunkX, chunkZ);

        double[] terrain = new double[16 * 16 * TOTAL_Y_HEIGHT];

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int colIdx = idx(i, k, 16);

                double thickness = this.thickness[colIdx];
                double amplitude = this.amplitude[colIdx];
                double heightBias = this.heightBias[colIdx];

                double continentalness = this.continentalness[colIdx];
                double height = continentalness * TOTAL_Y_HEIGHT + heightBias;

                for (int j = 0; j < TOTAL_Y_HEIGHT; j++) {
                    int idx = idx(i, j, k, TOTAL_Y_HEIGHT, 16);

                    double terrainValue = this.terrain[idx];

                    double distance = j - height;

                    int innerFadeRadius = 8;
                    int outerFadeRadius = 32;

                    double fade = 1 - smoothstep(innerFadeRadius, outerFadeRadius, Math.abs(distance));

                    double densityBase = -distance / thickness;
                    double erosionOffset = amplitude * terrainValue * fade;

                    double density = densityBase + erosionOffset;
                    terrain[idx] = density;
                }
            }
        }

        return terrain;
    }

    private void initNoiseFields(int chunkX, int chunkZ) {
        this.continentalness = getNoise2D(this.continentalnessGenerator, this.continentalness, chunkX, chunkZ, 16, 16, 1, 1, CONTINENTALNESS_SCALE);
        // TODO: fix interpolation
        this.transformNoise(this.continentalness, continentalnessSpline);

        this.erosion = getNoise2D(this.erosionGenerator, this.erosion, chunkX, chunkZ, 16, 16, 1, 1, EROSION_SCALE);
        // TODO: fix interpolation
        this.thickness = this.transformNoise(this.thickness, this.erosion, erosionToThickness);
        this.amplitude = this.transformNoise(this.amplitude, this.erosion, erosionToAmplitude);
        this.heightBias = this.transformNoise(this.heightBias, this.erosion, erosionToHeightBias);

        this.ridges = getNoise2D(this.ridgesGenerator, this.ridges, chunkX, chunkZ, 16, 16, 1, 1, RIDGES_SCALE);
        // TODO: fix interpolation
        //this.transformNoise(this.ridges, ridgeSpline);

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

    private double[] transformNoise(double[] destination, double[] noise, Spline spline) {
        if (destination == null || destination.length < noise.length) {
            destination = new double[noise.length];
        }

        for (int i = 0; i < noise.length; i++) {
            destination[i] = spline.eval(noise[i]);
        }

        return destination;
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
