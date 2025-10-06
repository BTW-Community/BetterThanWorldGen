package btwg.api.world.generate.noise;

import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeNoiseVector;
import btwg.api.biome.DefaultBiomes;
import btwg.api.block.StoneType;
import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import btwg.api.world.generate.noise.spline.Key;
import btwg.api.world.generate.noise.spline.Spline;
import net.minecraft.src.BiomeGenBase;

import java.util.Arrays;
import java.util.Random;

public final class NoiseProvider {
    public final long seed;

    public static final int CONTINENTALNESS_SCALE = 2048;
    public static final int EROSION_SCALE = 1024;
    public static final int RIDGES_SCALE = 512;
    public static final int VALLEY_SCALE = 96;
    public static final int WEIRDNESS_SCALE = 2048;

    public static final int TEMPERATURE_SCALE = 4096;
    public static final int HUMIDITY_SCALE = 4096;

    public static final int TERRAIN_SCALE = 256;

    public static final int STONE_TYPE_SCALE = 1024;

    // Cave generation scales
    public static final int CHEESE_CAVE_SCALE_XZ = 320;
    public static final int CHEESE_CAVE_SCALE_Y = 128;

    public static final int SPAGHETTI_CAVE_SCALE_XZ = 240;
    public static final int SPAGHETTI_CAVE_SCALE_Y = 96;

    public static final int NOODLE_CAVE_SCALE_XZ = 144;
    public static final int NOODLE_CAVE_SCALE_Y = 96;

    public static final int PILLAR_SCALE = 32;

    public static final double SPAGHETTI_CAVE_RADIUS = 0.15;
    public static final double NOODLE_CAVE_RADIUS = 0.08;

    public static final double CAVE_THRESHOLD = -0.7;

    private static final int DRIVER_OCTAVES = 6;
    private static final int TERRAIN_OCTAVES = 6;

    private static final int CHEESE_CAVES_OCTAVES = 4;
    private static final int SPAGHETTI_CAVES_OCTAVES = 3;
    private static final int NOODLE_CAVES_OCTAVES = 3;
    private static final int CAVE_PILLAR_OCTAVES = 2;

    private static final int STONE_TYPE_OCTAVES = 2;

    // TODO: fix interpolation
    public static final int NOISE_SUBSCALE = 16;
    public static final int NOISE_SIZE = NOISE_SUBSCALE + 1;

    public static final int TOTAL_HEIGHT = 256;
    public static final int SEA_LEVEL = 100;

    public static final double SECOND_STRATA_RATIO;
    public static final double THIRD_STRATA_RATIO;
    static {
        int minThirdStrataHeight = 24;
        int totalDiff = SEA_LEVEL - minThirdStrataHeight;

        double secondStrataPortion = 4.0/5.0;
        double thirdStrataPortion = 2.0/5.0;

        SECOND_STRATA_RATIO = (totalDiff * secondStrataPortion + minThirdStrataHeight) / SEA_LEVEL;
        THIRD_STRATA_RATIO = (totalDiff * thirdStrataPortion + minThirdStrataHeight) / SEA_LEVEL;

        System.out.println("Second strata: " + SECOND_STRATA_RATIO);
        System.out.println("Third strata: " + THIRD_STRATA_RATIO);
    }

    private static final double STONE_TYPE_WARP_FACTOR = 0.5;

    private static final Spline continentalnessSpline = Spline.of(
            new Key(-1.50, 40.0 / TOTAL_HEIGHT),
            new Key(-1.00, 50.0 / TOTAL_HEIGHT),
            new Key(-0.40, 85.0 / TOTAL_HEIGHT),
            new Key(-0.10, 95.0 / TOTAL_HEIGHT),
            new Key(0.00, 105.0 / TOTAL_HEIGHT),
            new Key(0.40, 110.0 / TOTAL_HEIGHT),
            new Key(0.60, 130.0 / TOTAL_HEIGHT),
            new Key(0.70, 140.0 / TOTAL_HEIGHT),
            new Key(0.85, 160.0 / TOTAL_HEIGHT),
            new Key(1.00, 190.0 / TOTAL_HEIGHT),
            new Key(1.20, 220.0 / TOTAL_HEIGHT)
    );

    private static final Spline erosionToThickness = Spline.of(
            new Key(-1.0, 0.2),
            new Key(-0.5, 0.4),
            new Key( 0.0, 0.6),
            new Key( 0.5, 0.8),
            new Key( 1.0, 1.0)
    );

    private static final Spline erosionToAmplitude = Spline.of(
            new Key(-1.0, 1.0),
            new Key(-0.5, 0.8),
            new Key( 0.0,  0.6),
            new Key( 0.5,  0.4),
            new Key( 1.0,  0.3)
    );

    private static final Spline erosionToHeightBias = Spline.of(
            new Key(-1.0,  1.0),
            new Key(-0.5,  0.5),
            new Key( 0.0,  0.25),
            new Key( 0.5,  0.125),
            new Key( 1.0,  0.0625)
    );

    private static final Spline pillarSpline = Spline.of(
            new Key(-1.0, 0),
            new Key( 0.0, 0),
            new Key( 1.0, 0.1),
            new Key( 1.15, 0.3),
            new Key( 1.3, 1.0)
    );

    private final OpenSimplexOctavesFast continentalnessGenerator;
    private final OpenSimplexOctavesFast erosionGenerator;
    private final OpenSimplexOctavesFast ridgesGenerator;
    private final OpenSimplexOctavesFast riverGenerator;
    private final OpenSimplexOctavesFast weirdnessGenerator;

    private final OpenSimplexOctavesFast temperatureGenerator;
    private final OpenSimplexOctavesFast humidityGenerator;

    private final OpenSimplexOctavesFast terrainGenerator;

    // Cave noise generators
    private final OpenSimplexOctavesFast cheeseCaveGenerator;
    private final OpenSimplexOctavesFast spaghettiCave1Generator;
    private final OpenSimplexOctavesFast spaghettiCave2Generator;
    private final OpenSimplexOctavesFast noodleCave1Generator;
    private final OpenSimplexOctavesFast noodleCave2Generator;
    private final OpenSimplexOctavesFast pillarGenerator;

    private final OpenSimplexOctavesFast stoneTypeGenerator;

    private double[] continentalness;
    private double[] heightmap;
    private double[] strata2Heightmap;
    private double[] strata3Heightmap;

    private double[] erosion;
    private double[] thickness;
    private double[] amplitude;
    private double[] heightBias;

    private double[] ridges;
    private double[] valleys;
    private double[] weirdness;

    private double[] temperature;
    private double[] humidity;

    private double[] terrain;

    private double[] stoneTypes;

    private double[] finalTerrainNoise;
    private double[] finalCaveNoise;
    private StoneType[] finalStoneTypes;

    private final double[] gaussianBlur;

    private int lastChunkX = Integer.MAX_VALUE;
    private int lastChunkZ = Integer.MAX_VALUE;

    private final Random rand;

    public NoiseProvider(long seed) {
        this.seed = seed;

        this.rand = new Random(seed);

        this.continentalnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.erosionGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.ridgesGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.riverGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.weirdnessGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);

        this.temperatureGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);
        this.humidityGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), DRIVER_OCTAVES);

        this.terrainGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), TERRAIN_OCTAVES);

        this.cheeseCaveGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), CHEESE_CAVES_OCTAVES);
        this.spaghettiCave1Generator = new OpenSimplexOctavesFast(seed + rand.nextLong(), SPAGHETTI_CAVES_OCTAVES);
        this.spaghettiCave2Generator = new OpenSimplexOctavesFast(seed + rand.nextLong(), SPAGHETTI_CAVES_OCTAVES);
        this.noodleCave1Generator = new OpenSimplexOctavesFast(seed + rand.nextLong(), NOODLE_CAVES_OCTAVES);
        this.noodleCave2Generator = new OpenSimplexOctavesFast(seed + rand.nextLong(), NOODLE_CAVES_OCTAVES);
        this.pillarGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), CAVE_PILLAR_OCTAVES);

        this.stoneTypeGenerator = new OpenSimplexOctavesFast(seed + rand.nextLong(), STONE_TYPE_OCTAVES);

        int gaussianBlurSize = 3;
        gaussianBlur = Arrays.stream(new double[] {
                1, 2, 1,
                2, 4, 2,
                1, 2, 1
        }).map(x -> x / (double) (gaussianBlurSize * gaussianBlurSize)).toArray();
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

    private static double ridge(double n, double exponent) {
        double r = 1.0 - Math.abs(n);   // [0,1], peaks high
        r = clamp(r, 0.0, 1.0);
        return Math.pow(r, exponent);   // sharpen ridges
    }

    private double calculateLaplacianCurvature() {
        double curvature = 0;
        for (int i = 1; i < 15; i++) {
            for (int k = 1; k < 15; k++) {
                int center = idx(i, k, 16);
                int left = idx(i - 1, k, 16);
                int right = idx(i + 1, k, 16);
                int up = idx(i, k - 1, 16);
                int down = idx(i, k + 1, 16);

                double laplacian = (this.heightmap[left] +
                        this.heightmap[right] +
                        this.heightmap[up] +
                        this.heightmap[down] -
                        4 * this.heightmap[center]);
                curvature += Math.abs(laplacian);
            }
        }
        return curvature / (14 * 14); // Average over interior points
    }

    private double calculateChunkSlope() {
        double totalSlope = 0;
        for (int i = 1; i < 15; i++) {
            for (int k = 1; k < 15; k++) {
                int center = idx(i, k, 16);
                int right = idx(i + 1, k, 16);
                int down = idx(i, k + 1, 16);

                double slopeX = Math.abs(this.heightmap[right] - this.heightmap[center]);
                double slopeZ = Math.abs(this.heightmap[down] - this.heightmap[center]);
                totalSlope += Math.sqrt(slopeX * slopeX + slopeZ * slopeZ);
            }
        }
        return totalSlope / (14 * 14);
    }

    public double[] generateTerrainNoise(int chunkX, int chunkZ) {
        this.initNoiseFields(chunkX, chunkZ);

        if (this.finalTerrainNoise == null) {
            this.finalTerrainNoise = new double[16 * 16 * TOTAL_HEIGHT];
        }

        // Calculate valleys for generating rivers
        double rawCurvature = calculateLaplacianCurvature();
        double curvature = smoothstep(0.05, 0.15, 0.05 * rawCurvature);

        double rawSlope = calculateChunkSlope();
        double slope = smoothstep(2, 6, rawSlope);

        double valleyFactor = clamp(slope * curvature, 0.0, 1.0);

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int colIdx = idx(i, k, 16);

                // Erosion to manipulate 3D noise parameters
                double thickness = 8;
                double amplitude = 10;
                double heightBias = 8;

                thickness *= this.thickness[colIdx];
                amplitude *= this.amplitude[colIdx];
                heightBias *= this.heightBias[colIdx];

                // Base heightmap from continentalness
                double continentalness = this.heightmap[colIdx];
                double height = continentalness * TOTAL_HEIGHT + heightBias;

                // Create ridges
                // Exponent to sharpen the ridge mask, map to amplitude to control 3D noise
                double ridgePeak = ridge(this.ridges[colIdx], 2.0);
                double ridgeMask = smoothstep(0.25, 1.0, ridgePeak);
                double ridgeAmp = lerp(0.6, 2.5, ridgeMask);

                // Generate valley noise
                double valleyBlur = 0;

                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        int riverIdx = idx((int) clamp(i + x, 0, 15), (int) clamp(k + z, 0, 15), 16);

                        double valleyNoise = this.valleys[riverIdx];
                        valleyNoise = smoothstep(0.35, 0.55, Math.abs(valleyNoise));
                        valleyNoise = clamp(valleyNoise * valleyFactor, 0.0, 1.0);

                        valleyBlur += valleyNoise * gaussianBlur[idx(x + 1, z + 1, 3)];
                    }
                }

                double valleyMask = Math.pow(valleyBlur, 0.8);

                double baseDepth = 4;
                int seaLevel = this.getSeaLevel();
                double seaFade = smoothstep(seaLevel - 16, seaLevel + 8, height);
                double depth = baseDepth * seaFade * valleyMask;

                height -= depth;

                for (int j = 0; j < TOTAL_HEIGHT; j++) {
                    // Calculate base 3D noise density, then modulate it based on distance to the target heightmap
                    int idx = idx(i, j, k, TOTAL_HEIGHT, 16);
                    double terrainValue = this.terrain[idx];

                    double distance = j - height;

                    // Reduce influence of 3D noise with distance from target heightmap
                    int innerFadeRadius = 8;
                    int outerFadeRadius = 32;
                    double fade = 1 - smoothstep(innerFadeRadius, outerFadeRadius, Math.abs(distance));

                    // Calculate final density
                    double densityBase = -distance / thickness;
                    double erosionDetail = amplitude * terrainValue * fade;
                    double detail = ridgeAmp * erosionDetail;

                    double density = densityBase + detail;
                    this.finalTerrainNoise[idx] = density;
                }
            }
        }

        return this.finalTerrainNoise;
    }

    public double[] generateCaveNoise(int chunkX, int chunkZ) {
        if (chunkX != this.lastChunkX || chunkZ != this.lastChunkZ) {
            this.initNoiseFields(chunkX, chunkZ);
        }

        if (this.finalCaveNoise == null) {
            this.finalCaveNoise = new double[16 * 16 * TOTAL_HEIGHT];
        }

        int baseX = chunkX * 16;
        int baseZ = chunkZ * 16;

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int colIdx = idx(i, k, 16);
                double surfaceHeight = this.heightmap[colIdx] * TOTAL_HEIGHT;

                for (int j = 0; j < TOTAL_HEIGHT; j++) {
                    int idx = idx(i, j, k, TOTAL_HEIGHT, 16);

                    int worldX = baseX + i;
                    int worldZ = baseZ + k;

                    // Calculate depth from surface for cave density modulation
                    double depthFromSurface = surfaceHeight - j;

                    // Don't generate caves in bottom 5 blocks
                    if (j < 5) {
                        this.finalCaveNoise[idx] = 1.0; // Solid
                        continue;
                    }

                    // Calculate cave density
                    double caveDensity = calculateCaveDensity(worldX, j, worldZ, depthFromSurface);

                    this.finalCaveNoise[idx] = caveDensity;
                }
            }
        }

        return this.finalCaveNoise;
    }

    private double calculateCaveDensity(int x, int y, int z, double depthFromSurface) {
        // Cheese caves (large caverns)
        double cheese = cheeseCaveGenerator.noise3(x, y, z, 1.0 / CHEESE_CAVE_SCALE_XZ, 1.0 / CHEESE_CAVE_SCALE_Y);

        // Fade out cheese near surface
        double surfaceFade = smoothstep(8, 20, depthFromSurface);
        cheese = lerp(1.0, cheese, surfaceFade);

        // Pillar caves (vertical columns)
        double pillarNoise = pillarGenerator.noise2(x, z, 1.0 / PILLAR_SCALE);
        cheese += pillarSpline.eval(pillarNoise);

        // Spaghetti caves (winding tunnels)
        // Use two noise functions to create tubular structures
        double spaghetti1 = spaghettiCave1Generator.noise3(
                x, y * 0.75, z, 1.0 / SPAGHETTI_CAVE_SCALE_XZ, 1.0 / SPAGHETTI_CAVE_SCALE_Y
        );
        double spaghetti2 = spaghettiCave2Generator.noise3(
                x, y * 0.75, z, 1.0 / SPAGHETTI_CAVE_SCALE_XZ, 1.0 / SPAGHETTI_CAVE_SCALE_Y
        );

        // Combine to create tubes
        double spaghettiDist = Math.sqrt(spaghetti1 * spaghetti1 + spaghetti2 * spaghetti2);
        double spaghettiDensity = 1.0;

        if (spaghettiDist < SPAGHETTI_CAVE_RADIUS) {
            spaghettiDensity = -1.0;
        }

        // Noodle caves (thin, vertical tunnels)
        double noodle1 = noodleCave1Generator.noise3(x, y, z, 1.0 / NOODLE_CAVE_SCALE_XZ, 1.0 / NOODLE_CAVE_SCALE_Y);
        double noodle2 = noodleCave2Generator.noise3(x, y, z, 1.0 / NOODLE_CAVE_SCALE_XZ, 1.0 / NOODLE_CAVE_SCALE_Y);

        double noodleDist = Math.sqrt(noodle1 * noodle1 + noodle2 * noodle2);
        double noodleDensity = 1.0;

        if (noodleDist < NOODLE_CAVE_RADIUS) {
            noodleDensity = -1.0;
        }

        // Combine all cave types (multiply densities - caves are where density is low)
        double combinedDensity = Math.min(Math.min(cheese, spaghettiDensity), noodleDensity);

        // Reduce cave frequency at very deep levels
        if (y < 20) {
            double deepFade = smoothstep(5, 16, y);
            combinedDensity = lerp(1.0, combinedDensity, deepFade);
        }

        return combinedDensity;
    }

    private void initNoiseFields(int chunkX, int chunkZ) {
        // TODO: fix interpolation to improve performance

        this.continentalness = this.getNoise2D(this.continentalnessGenerator, this.continentalness, chunkX, chunkZ, 16, 16, 1, 1, CONTINENTALNESS_SCALE);
        this.heightmap = this.transformNoise(this.heightmap, this.continentalness, continentalnessSpline);
        this.strata2Heightmap = this.getHeight(this.strata2Heightmap, SECOND_STRATA_RATIO);
        this.strata3Heightmap = this.getHeight(this.strata3Heightmap, THIRD_STRATA_RATIO);

        this.erosion = this.getNoise2D(this.erosionGenerator, this.erosion, chunkX, chunkZ, 16, 16, 1, 1, EROSION_SCALE);
        this.thickness = this.transformNoise(this.thickness, this.erosion, erosionToThickness);
        this.amplitude = this.transformNoise(this.amplitude, this.erosion, erosionToAmplitude);
        this.heightBias = this.transformNoise(this.heightBias, this.erosion, erosionToHeightBias);

        this.ridges = this.getNoise2D(this.ridgesGenerator, this.ridges, chunkX, chunkZ, 16, 16, 1, 1, RIDGES_SCALE);

        this.valleys = this.getNoise2D(this.riverGenerator, this.valleys, chunkX, chunkZ, 16, 16, 1, 1, VALLEY_SCALE);

        this.weirdness = this.getNoise2D(this.weirdnessGenerator, this.weirdness, chunkX, chunkZ, 16, 16, 1, 1, WEIRDNESS_SCALE);

        // TODO: add rivers

        this.terrain = this.getNoise3D(this.terrainGenerator, this.terrain, chunkX, 0, chunkZ, 16, TOTAL_HEIGHT, 16, TERRAIN_SCALE);

        this.stoneTypes = this.getNoise3D(this.stoneTypeGenerator, this.stoneTypes, chunkX, 0, chunkZ, 16, TOTAL_HEIGHT, 16, STONE_TYPE_SCALE);

        this.temperature = getNoise2D(this.temperatureGenerator, this.temperature, chunkX, chunkZ, 16, 16, 1, 1, TEMPERATURE_SCALE);
        this.humidity = getNoise2D(this.humidityGenerator, this.humidity, chunkX, chunkZ, 16, 16, 1, 1, HUMIDITY_SCALE);

        this.lastChunkX = chunkX;
        this.lastChunkZ = chunkZ;
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

    public BiomeGenBase[] generateBiomes(int chunkX, int chunkZ) {
        if (chunkX != this.lastChunkX || chunkZ != this.lastChunkZ) {
            this.initNoiseFields(chunkX, chunkZ);
        }

        BiomeGenBase[] biomes = new BiomeGenBase[256];

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int idx = idx(i, k, 16);

                // Normalize temperature and humidity to [0, 1]
                double temperature = this.temperature[idx] * 0.5 + 0.5;
                double humidity = this.humidity[idx] * 0.5 + 0.5;

                var biomeVector = new BiomeNoiseVector(
                        temperature,
                        humidity,
                        this.continentalness[idx],
                        this.erosion[idx],
                        this.weirdness[idx]
                );

                double closestDistance = Double.MAX_VALUE;
                BTWGBiome closestBiome = null;

                double targetHeight = this.heightmap[idx] * TOTAL_HEIGHT;

                for (BTWGBiome biome : BTWGBiome.biomeList) {
                    if (biome.noiseTarget.distanceSqFromTarget(biomeVector) < closestDistance) {
                        if (biome.noiseTarget.validator().test(biomeVector, (int) targetHeight)) {
                            closestDistance = biome.noiseTarget.distanceSqFromTarget(biomeVector);
                            closestBiome = biome;
                        }
                    }
                }

                if (closestBiome == null) {
                    closestBiome = DefaultBiomes.OCEAN;
                }

                biomes[idx] = closestBiome;
            }
        }

        //Arrays.fill(biomes, BiomeConfiguration.OUTBACK);

        return biomes;
    }

    public StoneType[] generateStoneTypes(int chunkX, int chunkZ) {
        if (this.finalStoneTypes == null) {
            this.finalStoneTypes = new StoneType[16 * 16 * TOTAL_HEIGHT];
        }

        if (chunkX != this.lastChunkX || chunkZ != this.lastChunkZ) {
            this.initNoiseFields(chunkX, chunkZ);
        }

        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int colIdx = idx(i, k, 16);

                double strata2Height = this.strata2Heightmap[colIdx];
                double strata3Height = this.strata3Heightmap[colIdx];

                for (int j = 0; j < TOTAL_HEIGHT; j++) {
                    int idx = idx(i, j, k, TOTAL_HEIGHT, 16);

                    int strata = 0;

                    if (j <= strata3Height) {
                        strata = 2;

                        if (j == strata3Height && this.rand.nextInt(2) == 0) {
                            strata = 1;
                        }
                    }
                    else if (j <= strata2Height) {
                        strata = 1;

                        if (j == strata2Height && this.rand.nextInt(2) == 0) {
                            strata = 0;
                        }
                    }

                    var validTypes = StoneType.STONE_TYPES_BY_STRATA.get(strata);

                    double stoneNoise = this.stoneTypes[idx];

                    stoneNoise = clamp(stoneNoise, -1.25, 1.24);
                    stoneNoise = stoneNoise / 2.5 + 0.5;
                    int index = (int) Math.floor(stoneNoise * validTypes.size());

                    this.finalStoneTypes[idx] = validTypes.get(index);
                }
            }
        }

        return this.finalStoneTypes;
    }

    public StoneType[] getStoneTypes(int chunkX, int chunkZ) {
        if (this.finalStoneTypes == null || chunkX != this.lastChunkX || chunkZ != this.lastChunkZ) {
            return this.generateStoneTypes(chunkX, chunkZ);
        }
        return this.finalStoneTypes;
    }

    private double[] getHeight(double[] dest, double multiplier) {
        if (dest == null) {
            dest = new double[16 * 16];
        }

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                int idx = idx(i, k, 16);
                dest[idx] = this.heightmap[idx] * TOTAL_HEIGHT * multiplier;
            }
        }

        return dest;
    }

    public BiomeNoiseVector getBiomeVector(int x, int z) {
        int chunkX = x >> 4;
        int chunkZ = z >> 4;

        int idx = idx(x & 15, z & 15, 16);

        double[] array = new double[256];

        array = this.getNoise2D(this.continentalnessGenerator, array, chunkX, chunkZ, 16, 16, 1, 1, CONTINENTALNESS_SCALE);
        double continentalness = array[idx];

        array = this.getNoise2D(this.erosionGenerator, array, chunkX, chunkZ, 16, 16, 1, 1, EROSION_SCALE);
        double erosion = array[idx];

        array = this.getNoise2D(this.weirdnessGenerator, array, chunkX, chunkZ, 16, 16, 1, 1, WEIRDNESS_SCALE);
        double weirdness = array[idx];

        array = this.getNoise2D(this.temperatureGenerator, array, chunkX, chunkZ, 16, 16, 1, 1, TEMPERATURE_SCALE);
        double temperature = array[idx] * 0.5 + 0.5;

        array = this.getNoise2D(this.humidityGenerator, array, chunkX, chunkZ, 16, 16, 1, 1, HUMIDITY_SCALE);
        double humidity = array[idx] * 0.5 + 0.5;

        return new BiomeNoiseVector(
                temperature,
                humidity,
                continentalness,
                erosion,
                weirdness,
                0.01
        );
    }

    public byte getSeaLevel() {
        return SEA_LEVEL;
    }
}
