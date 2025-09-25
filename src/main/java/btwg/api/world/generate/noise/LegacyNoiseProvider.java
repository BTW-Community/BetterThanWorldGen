package btwg.api.world.generate.noise;

import btwg.api.biome.BiomeInterface;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.Random;

public class LegacyNoiseProvider extends NoiseProvider {
    protected final World world;
    protected final Random rand;

    protected final NoiseGeneratorOctaves noiseGen1;
    protected final NoiseGeneratorOctaves noiseGen2;
    protected final NoiseGeneratorOctaves noiseGen3;
    protected final NoiseGeneratorOctaves noiseGen5;
    protected final NoiseGeneratorOctaves noiseGen6;

    protected double[] noise1;
    protected double[] noise2;
    protected double[] noise3;
    protected double[] noise5;
    protected double[] noise6;

    protected double[] initialNoiseField;
    protected float[] parabolicField;

    public LegacyNoiseProvider(World world, long seed) {
        super(seed);
        this.world = world;

        this.rand = new Random(seed);

        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
    }

    @Override
    public double[] getTerrainNoise(int chunkX, int chunkZ) {
        double[] noise = new double[16 * 16 * 128];
        
        byte subChunkSize = 4;
        byte heightSections = 16;
        int subChunkPlusOne = subChunkSize + 1;
        byte heightSectionsPlus1 = 17;
        int noiseWidth = subChunkSize + 1;
        BiomeGenBase[] biomes = this.world.getWorldChunkManager().getBiomesForGeneration(null, chunkX * 4 - 2, chunkZ * 4 - 2, subChunkPlusOne + 5, noiseWidth + 5);
        this.initialNoiseField = this.initializeNoiseField(this.initialNoiseField, chunkX * subChunkSize, 0, chunkZ * subChunkSize, subChunkPlusOne, heightSectionsPlus1, noiseWidth, biomes);

        for (int i = 0; i < subChunkSize; ++i) {
            for (int k = 0; k < subChunkSize; ++k) {
                for (int heightSection = 0; heightSection < heightSections; ++heightSection) {
                    double noiseScale = (double) 0.125F;
                    double noise00 = this.initialNoiseField[((i + 0) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 0];
                    double noise01 = this.initialNoiseField[((i + 0) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 0];
                    double noise10 = this.initialNoiseField[((i + 1) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 0];
                    double noise11 = this.initialNoiseField[((i + 1) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 0];
                    double noiseStep00 = (this.initialNoiseField[((i + 0) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 1] - noise00) * noiseScale;
                    double noiseStep01 = (this.initialNoiseField[((i + 0) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 1] - noise01) * noiseScale;
                    double noiseStep10 = (this.initialNoiseField[((i + 1) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 1] - noise10) * noiseScale;
                    double noiseStep11 = (this.initialNoiseField[((i + 1) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 1] - noise11) * noiseScale;

                    for (int j = 0; j < 8; ++j) {
                        double interpolationScale = 0.25F;
                        double interpValue1 = noise00;
                        double interpValue2 = noise01;
                        double interpStep1 = (noise10 - noise00) * interpolationScale;
                        double interpStep2 = (noise11 - noise01) * interpolationScale;

                        for (int blockX = 0; blockX < 4; ++blockX) {
                            int blockIndex = blockX + i * 4 << 11 | 0 + k * 4 << 7 | heightSection * 8 + j;
                            short heightOffset128 = 128;
                            blockIndex -= heightOffset128;
                            double interpolationScale2 = (double) 0.25F;
                            double heightStep = (interpValue2 - interpValue1) * interpolationScale2;
                            double heightValue = interpValue1 - heightStep;

                            for (int blockZ = 0; blockZ < 4; ++blockZ) {
                                heightValue += heightStep;
                                blockIndex += heightOffset128;

                                noise[blockIndex] = heightValue;
                            }

                            interpValue1 += interpStep1;
                            interpValue2 += interpStep2;
                        }

                        noise00 += noiseStep00;
                        noise01 += noiseStep01;
                        noise10 += noiseStep10;
                        noise11 += noiseStep11;
                    }
                }
            }
        }

        return noise;
    }

    protected double[] initializeNoiseField(double[] noiseArray, int x, int y, int z, int width, int height, int length, BiomeGenBase[] biomesForGeneration) {
        if (noiseArray == null) {
            noiseArray = new double[width * height * length];
        }

        if (this.parabolicField == null) {
            this.parabolicField = new float[25];

            for (int parabolicX = -2; parabolicX <= 2; ++parabolicX) {
                for (int parabolicZ = -2; parabolicZ <= 2; ++parabolicZ) {
                    float parabolicValue = 10.0F / MathHelper.sqrt_float(parabolicX * parabolicX + parabolicZ * parabolicZ + 0.2F);
                    this.parabolicField[parabolicX + 2 + (parabolicZ + 2) * 5] = parabolicValue;
                }
            }
        }

        double noiseScale1 = 684.412;
        double noiseScale2 = 684.412;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, x, z, width, length, 1.121, 1.121, 0.5);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, x, z, width, length, 200.0, 200.0, 0.5);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, x, y, z, width, height, length, noiseScale1 / 80.0, noiseScale2 / 160.0, noiseScale1 / 80.0);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, x, y, z, width, height, length, noiseScale1, noiseScale2, noiseScale1);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, x, y, z, width, height, length, noiseScale1, noiseScale2, noiseScale1);
        int noiseIndex = 0;
        int surfaceNoiseIndex = 0;

        for (int noiseX = 0; noiseX < width; ++noiseX) {
            for (int noiseZ = 0; noiseZ < length; ++noiseZ) {
                float totalVariance = 0.0F;
                float totalHeight = 0.0F;
                float totalWeight = 0.0F;
                byte biomeRadius = 2;

                BiomeGenBase currentBiome = biomesForGeneration[noiseX + 2 + (noiseZ + 2) * (width + 5)];
                // TODO: Update with real version handling
                var currentHeightData = ((BiomeInterface) currentBiome).getHeightData().get(BetterThanWorldGen.V1_0_0);

                for (int biomeX = -biomeRadius; biomeX <= biomeRadius; ++biomeX) {
                    for (int biomeZ = -biomeRadius; biomeZ <= biomeRadius; ++biomeZ) {
                        BiomeGenBase neighborBiome = biomesForGeneration[noiseX + biomeX + 2 + (noiseZ + biomeZ + 2) * (width + 5)];

                        // TODO: Update with real version handling
                        var neighborHeightData = ((BiomeInterface) neighborBiome).getHeightData().get(BetterThanWorldGen.V1_0_0);

                        float biomeWeight = this.parabolicField[biomeX + 2 + (biomeZ + 2) * 5] / (neighborHeightData.height() + 2.0F);

                        if (neighborHeightData.height() > currentHeightData.height()) {
                            biomeWeight /= 2.0F;
                        }

                        totalVariance += neighborHeightData.variance() * biomeWeight;
                        totalHeight += neighborHeightData.height() * biomeWeight;
                        totalWeight += biomeWeight;
                    }
                }

                totalVariance /= totalWeight;
                totalHeight /= totalWeight;
                totalVariance = totalVariance * 0.9F + 0.1F;
                totalHeight = (totalHeight * 4.0F - 1.0F) / 8.0F;

                double surfaceNoise = getSurfaceNoise(surfaceNoiseIndex);

                ++surfaceNoiseIndex;

                for (int noiseY = 0; noiseY < height; ++noiseY) {
                    double heightValue = totalHeight;
                    double varianceValue = totalVariance;
                    heightValue += surfaceNoise * 0.2;
                    heightValue = heightValue * height / 16.0;
                    double heightScale = height / 2.0 + heightValue * 4.0;
                    double finalNoise;
                    double heightDiff = (noiseY - heightScale) * 12.0 * 128.0 / 128.0 / varianceValue;

                    if (heightDiff < 0.0) {
                        heightDiff *= 4.0;
                    }

                    double noise1Value = this.noise1[noiseIndex] / 512.0;
                    double noise2Value = this.noise2[noiseIndex] / 512.0;
                    double noise3Value = (this.noise3[noiseIndex] / 10.0 + 1.0) / 2.0;

                    if (noise3Value < 0.0) {
                        finalNoise = noise1Value;
                    }
                    else if (noise3Value > 1.0) {
                        finalNoise = noise2Value;
                    }
                    else {
                        finalNoise = noise1Value + (noise2Value - noise1Value) * noise3Value;
                    }

                    finalNoise -= heightDiff;

                    if (noiseY > height - 4) {
                        double fadeHeight = (noiseY - (height - 4)) / 3.0;
                        finalNoise = finalNoise * (1.0 - fadeHeight) + -10.0 * fadeHeight;
                    }

                    noiseArray[noiseIndex] = finalNoise;
                    ++noiseIndex;
                }
            }
        }

        return noiseArray;
    }

    private double getSurfaceNoise(int surfaceNoiseIndex) {
        double surfaceNoise = this.noise6[surfaceNoiseIndex] / 8000.0;

        if (surfaceNoise < 0.0) {
            surfaceNoise = -surfaceNoise * 0.3;
        }

        surfaceNoise = surfaceNoise * 3.0 - 2.0;

        if (surfaceNoise < 0.0) {
            surfaceNoise /= 2.0;

            if (surfaceNoise < -1.0) {
                surfaceNoise = -1.0;
            }

            surfaceNoise /= 1.4;
            surfaceNoise /= 2.0;
        }
        else {
            if (surfaceNoise > 1.0) {
                surfaceNoise = 1.0;
            }

            surfaceNoise /= 8.0;
        }

        return surfaceNoise;
    }

    @Override
    public BiomeGenBase[] getBiomes(int chunkX, int chunkZ) {
        return this.world.getWorldChunkManager().loadBlockGeneratorData(null, chunkX * 16, chunkZ * 16, 16, 16);
    }
}
