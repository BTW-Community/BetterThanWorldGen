//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.world.generate;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData;
import btwg.api.configuration.WorldData;
import btwg.api.world.surface.Surfacer;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class LegacyChunkProvider implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] noiseArray;
    private double[] soilDepthNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];
    private Random structureRand;

    public static final ResourceLocation ID = new ResourceLocation("btwg", "default");

    public LegacyChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldData worldData) {
        this.worldObj = world;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.rand = new Random(seed);
        this.structureRand = new Random(seed);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    }

    public void generateTerrain(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata) {
        byte subChunkSize = 4;
        byte heightSections = 16;
        byte seaLevel = 63;
        int subChunkPlusOne = subChunkSize + 1;
        byte heightSectionsPlus1 = 17;
        int noiseWidth = subChunkSize + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, subChunkPlusOne + 5, noiseWidth + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * subChunkSize, 0, chunkZ * subChunkSize, subChunkPlusOne, heightSectionsPlus1, noiseWidth);

        for (int i = 0; i < subChunkSize; ++i) {
            for (int k = 0; k < subChunkSize; ++k) {
                for (int heightSection = 0; heightSection < heightSections; ++heightSection) {
                    double noiseScale = (double) 0.125F;
                    double noise00 = this.noiseArray[((i + 0) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 0];
                    double noise01 = this.noiseArray[((i + 0) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 0];
                    double noise10 = this.noiseArray[((i + 1) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 0];
                    double noise11 = this.noiseArray[((i + 1) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 0];
                    double noiseStep00 = (this.noiseArray[((i + 0) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 1] - noise00) * noiseScale;
                    double noiseStep01 = (this.noiseArray[((i + 0) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 1] - noise01) * noiseScale;
                    double noiseStep10 = (this.noiseArray[((i + 1) * noiseWidth + k + 0) * heightSectionsPlus1 + heightSection + 1] - noise10) * noiseScale;
                    double noiseStep11 = (this.noiseArray[((i + 1) * noiseWidth + k + 1) * heightSectionsPlus1 + heightSection + 1] - noise11) * noiseScale;

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
                                if ((heightValue += heightStep) > (double) 0.0F) {
                                    blockIDs[blockIndex += heightOffset128] = (short) (Block.stone.blockID);
                                }
                                else if (heightSection * 8 + j < seaLevel) {
                                    blockIDs[blockIndex += heightOffset128] = (short) (Block.waterStill.blockID);
                                }
                                else {
                                    blockIDs[blockIndex += heightOffset128] = 0;
                                }
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
    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata, BiomeGenBase[] biomes) {
        byte var5 = 63;
        double var6 = 0.03125F;
        this.soilDepthNoise = this.noiseGen4.generateNoiseOctaves(this.soilDepthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, var6 * (double)2.0F, var6 * (double)2.0F, var6 * (double)2.0F);

        var defaultSurfacer = new BiomeData<>(Surfacer.DEFAULT);

        for(int k = 0; k < 16; ++k) {
            for(int i = 0; i < 16; ++i) {
                BiomeGenBase biome = biomes[i + k * 16];

                if (!((BiomeInterface) biome).isVanilla()) {
                    int depth = -1;
                    int lastSurface = -1;

                    for (int j = 127; j >= 0; --j) {
                        int index = (i * 16 + k) * 128 + j;
                        if (j <= 0 + this.rand.nextInt(5)) {
                            blockIDs[index] = (short) Block.bedrock.blockID;
                        }

                        int blockID = blockIDs[index];

                        if (depth == 0) {
                            lastSurface = j;
                        }

                        if (blockID == 0 || blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID || blockID == Block.ice.blockID) {
                            depth = -1;
                        }

                        var surfacer = ((BiomeInterface) biome).getSurfacer().orElse(defaultSurfacer);

                        // TODO: Update with real version handling
                        surfacer.get(BetterThanWorldGen.V1_0_0).replaceBlockForLocation(this.worldObj, chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);

                        depth++;
                    }
                }
                else {
                    float temperature = biome.getFloatTemperature();
                    int soilDepth = (int) (this.soilDepthNoise[k + i * 16] / (double) 3.0F + (double) 3.0F + this.rand.nextDouble() * (double) 0.25F);
                    int var13 = -1;
                    short topBlock = biome.topBlock;
                    byte topBlockMetadata = biome.topBlockMetadata;
                    short fillerBlock = biome.fillerBlock;
                    byte fillerBlockMetadata = biome.fillerBlockMetadata;

                    for (int j = 127; j >= 0; --j) {
                        int index = (i * 16 + k) * 128 + j;
                        if (j <= 0 + this.rand.nextInt(5)) {
                            blockIDs[index] = (short) Block.bedrock.blockID;
                        } else {
                            short currentBlockID = blockIDs[index];
                            if (currentBlockID == 0) {
                                var13 = -1;
                            } else if (currentBlockID == Block.stone.blockID) {
                                if (var13 == -1) {
                                    if (soilDepth <= 0) {
                                        topBlock = 0;
                                        fillerBlock = (short) Block.stone.blockID;
                                    } else if (j >= var5 - 4 && j <= var5 + 1) {
                                        topBlock = biome.topBlock;
                                        fillerBlock = biome.fillerBlock;
                                    }

                                    if (j < var5 && topBlock == 0) {
                                        if (temperature < 0.15F) {
                                            topBlock = (short) Block.ice.blockID;
                                        } else {
                                            topBlock = (short) Block.waterStill.blockID;
                                        }
                                    }

                                    var13 = soilDepth;
                                    if (j >= var5 - 1) {
                                        blockIDs[index] = topBlock;
                                        metadata[index] = topBlockMetadata;
                                    } else {
                                        blockIDs[index] = fillerBlock;
                                        metadata[index] = fillerBlockMetadata;
                                    }
                                } else if (var13 > 0) {
                                    --var13;
                                    blockIDs[index] = fillerBlock;
                                    metadata[index] = fillerBlockMetadata;
                                    if (var13 == 0 && fillerBlock == Block.sand.blockID && fillerBlockMetadata == 0) {
                                        var13 = this.rand.nextInt(4);
                                        fillerBlock = (short) Block.sandStone.blockID;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2) {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        short[] blockIDs = new short['耀'];
        byte[] metadata = new byte['耀'];
        this.generateTerrain(par1, par2, blockIDs, metadata);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, blockIDs, metadata, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
        this.ravineGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, blockIDs, metadata);
        }

        Chunk var4 = new Chunk(this.worldObj, blockIDs, metadata, par1, par2);
        byte[] var5 = var4.getBiomeArray();

        for(int var6 = 0; var6 < var5.length; ++var6) {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    private double[] initializeNoiseField(double[] noiseArray, int x, int y, int z, int width, int height, int length) {
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

                BiomeGenBase currentBiome = this.biomesForGeneration[noiseX + 2 + (noiseZ + 2) * (width + 5)];
                // TODO: Update with real version handling
                var currentHeightData = ((BiomeInterface) currentBiome).getHeightData().get(BetterThanWorldGen.V1_0_0);

                for (int biomeX = -biomeRadius; biomeX <= biomeRadius; ++biomeX) {
                    for (int biomeZ = -biomeRadius; biomeZ <= biomeRadius; ++biomeZ) {
                        BiomeGenBase neighborBiome = this.biomesForGeneration[noiseX + biomeX + 2 + (noiseZ + biomeZ + 2) * (width + 5)];

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

    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
        boolean var11 = false;
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            this.structureRand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
            var11 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, par2, par3);
            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, par2, par3);
            this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, par2, par3);
        }

        if (var6 != BiomeGenBase.desert && var6 != BiomeGenBase.desertHills && !var11 && this.rand.nextInt(4) == 0) {
            int var12 = var4 + this.rand.nextInt(16) + 8;
            int var13 = this.rand.nextInt(128);
            int var14 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
        }

        if (!var11 && this.rand.nextInt(8) == 0) {
            int var12 = var4 + this.rand.nextInt(16) + 8;
            int var13 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            int var14 = var5 + this.rand.nextInt(16) + 8;
            if (var13 < 63 || this.rand.nextInt(10) == 0) {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
            }
        }

        for(int var12 = 0; var12 < 8; ++var12) {
            int var13 = var4 + this.rand.nextInt(16) + 8;
            int var14 = this.rand.nextInt(128);
            int var15 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        var6.decorate(this.worldObj, this.rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        var4 += 8;
        var5 += 8;

        for(int var24 = 0; var24 < 16; ++var24) {
            for(int var13 = 0; var13 < 16; ++var13) {
                int var14 = this.worldObj.getPrecipitationHeight(var4 + var24, var5 + var13);
                if (this.worldObj.isBlockFreezable(var24 + var4, var14 - 1, var13 + var5)) {
                    this.worldObj.setBlock(var24 + var4, var14 - 1, var13 + var5, Block.ice.blockID, 0, 2);
                }

                if (this.worldObj.canSnowAt(var24 + var4, var14, var13 + var5)) {
                    this.worldObj.setBlock(var24 + var4, var14, var13 + var5, Block.snow.blockID, 0, 2);
                } else if (this.worldObj.canSnowAt(var24 + var4, var14 + 1, var13 + var5)) {
                    this.worldObj.setBlock(var24 + var4, var14 + 1, var13 + var5, Block.snow.blockID, 0, 2);
                }
            }
        }

        this.btwPostProcessChunk(this.worldObj, var4 - 8, var5 - 8);
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void saveExtraData() {
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "BTWGLegacyChunkProvider";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : (par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(par2, par3, par4) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : var5.getSpawnableList(par1EnumCreatureType));
    }

    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (short[])null, (byte[])null);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, (short[])null, (byte[])null);
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (short[])null, (byte[])null);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (short[])null, (byte[])null);
        }

    }

    private void btwPostProcessChunk(World worldObj, int iChunkX, int iChunkZ) {
        if (worldObj.provider.dimensionId == 0) {
            this.generateStrata(worldObj, iChunkX, iChunkZ);
            this.generateAdditionalBrownMushrooms(worldObj, iChunkX, iChunkZ);
        }

    }

    private void generateAdditionalBrownMushrooms(World worldObj, int iChunkX, int iChunkZ) {
        if (worldObj.rand.nextInt(4) == 0) {
            WorldGenerator mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
            int iMushroomX = iChunkX + worldObj.rand.nextInt(16) + 8;
            int iMushroomY = worldObj.rand.nextInt(25);
            int iMushroomZ = iChunkZ + worldObj.rand.nextInt(16) + 8;
            mushroomBrownGen.generate(worldObj, worldObj.rand, iMushroomX, iMushroomY, iMushroomZ);
        }

    }

    private void generateStrata(World world, int iChunkX, int iChunkZ) {
        Chunk chunk = world.getChunkFromChunkCoords(iChunkX >> 4, iChunkZ >> 4);

        for(int iTempI = 0; iTempI < 16; ++iTempI) {
            for(int iTempK = 0; iTempK < 16; ++iTempK) {
                int iTempJ = 0;

                for(int iStrataHeight = 24 + world.rand.nextInt(2); iTempJ <= iStrataHeight; ++iTempJ) {
                    int iTempBlockID = chunk.getBlockID(iTempI, iTempJ, iTempK);
                    if (iTempBlockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(iTempI, iTempJ, iTempK, 2);
                    }
                }

                for(int var10 = 48 + world.rand.nextInt(2); iTempJ <= var10; ++iTempJ) {
                    int iTempBlockID = chunk.getBlockID(iTempI, iTempJ, iTempK);
                    if (iTempBlockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(iTempI, iTempJ, iTempK, 1);
                    }
                }
            }
        }

    }

    public MapGenStronghold getStrongholdGenerator() {
        return this.strongholdGenerator;
    }

    public MapGenVillage getVillageGenerator() {
        return this.villageGenerator;
    }

    public MapGenMineshaft getMineshaftGenerator() {
        return this.mineshaftGenerator;
    }

    public MapGenScatteredFeature getScatteredFeatureGenerator() {
        return this.scatteredFeatureGenerator;
    }
}
