//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.world.generate;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData;
import btwg.api.configuration.WorldData;
import btwg.api.world.surface.DefaultSurfacer;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class ChunkProvider implements IChunkProvider {
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

    public ChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldData worldData) {
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
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, var7 + 5, var9 + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9);

        for(int var10 = 0; var10 < var4; ++var10) {
            for(int var11 = 0; var11 < var4; ++var11) {
                for(int var12 = 0; var12 < var5; ++var12) {
                    double var13 = (double)0.125F;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for(int var31 = 0; var31 < 8; ++var31) {
                        double var32 = (double)0.25F;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for(int var42 = 0; var42 < 4; ++var42) {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;
                            var43 -= var44;
                            double var45 = (double)0.25F;
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;

                            for(int var51 = 0; var51 < 4; ++var51) {
                                if ((var47 += var49) > (double)0.0F) {
                                    blockIDs[var43 += var44] = (short)(Block.stone.blockID);
                                } else if (var12 * 8 + var31 < var6) {
                                    blockIDs[var43 += var44] = (short)(Block.waterStill.blockID);
                                } else {
                                    blockIDs[var43 += var44] = 0;
                                }
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }

    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata, BiomeGenBase[] biomes) {
        byte var5 = 63;
        double var6 = 0.03125F;
        this.soilDepthNoise = this.noiseGen4.generateNoiseOctaves(this.soilDepthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, var6 * (double)2.0F, var6 * (double)2.0F, var6 * (double)2.0F);

        for(int i = 0; i < 16; ++i) {
            for(int k = 0; k < 16; ++k) {
                BiomeGenBase biome = biomes[k + i * 16];

                if (!((BiomeInterface) biome).isVanilla()) {
                    int depth = -1;

                    for (int j = 127; j >= 0; --j) {
                        int index = (k * 16 + i) * 128 + j;
                        if (j <= 0 + this.rand.nextInt(5)) {
                            blockIDs[index] = (short) Block.bedrock.blockID;
                        }

                        int blockID = blockIDs[index];

                        if (blockID == 0 || blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID || blockID == Block.ice.blockID) {
                            depth = -1;
                        }

                        var surfacer = ((BiomeInterface) biome).getSurfacer().orElse(new BiomeData<>(DefaultSurfacer.INSTANCE));

                        // TODO: Update with real version handling
                        surfacer.get(BetterThanWorldGen.V1_0_0).replaceBlockForLocation(this.worldObj, k, j, i, depth, biome, blockIDs, metadata);

                        depth++;
                    }
                }
                else {
                    float temperature = biome.getFloatTemperature();
                    int soilDepth = (int) (this.soilDepthNoise[i + k * 16] / (double) 3.0F + (double) 3.0F + this.rand.nextDouble() * (double) 0.25F);
                    int var13 = -1;
                    short topBlock = biome.topBlock;
                    byte topBlockMetadata = biome.topBlockMetadata;
                    short fillerBlock = biome.fillerBlock;
                    byte fillerBlockMetadata = biome.fillerBlockMetadata;

                    for (int j = 127; j >= 0; --j) {
                        int index = (k * 16 + i) * 128 + j;
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

    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        if (this.parabolicField == null) {
            this.parabolicField = new float[25];

            for(int var8 = -2; var8 <= 2; ++var8) {
                for(int var9 = -2; var9 <= 2; ++var9) {
                    float var10 = 10.0F / MathHelper.sqrt_float((float)(var8 * var8 + var9 * var9) + 0.2F);
                    this.parabolicField[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 684.412;
        double var45 = 684.412;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121, 1.121, (double)0.5F);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, (double)200.0F, (double)200.0F, (double)0.5F);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / (double)80.0F, var45 / (double)160.0F, var44 / (double)80.0F);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        boolean var43 = false;
        boolean var42 = false;
        int var12 = 0;
        int var13 = 0;

        for(int var14 = 0; var14 < par5; ++var14) {
            for(int var15 = 0; var15 < par7; ++var15) {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;

                BiomeGenBase currentBiome = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];
                // TODO: Update with real version handling
                var currentHeightData = ((BiomeInterface) currentBiome).getHeightData().get(BetterThanWorldGen.V1_0_0);

                for(int var21 = -var19; var21 <= var19; ++var21) {
                    for(int var22 = -var19; var22 <= var19; ++var22) {
                        BiomeGenBase neighborBiome = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];

                        // TODO: Update with real version handling
                        var neighborHeightData = ((BiomeInterface) neighborBiome).getHeightData().get(BetterThanWorldGen.V1_0_0);

                        float var24 = this.parabolicField[var21 + 2 + (var22 + 2) * 5] / (neighborHeightData.height() + 2.0F);

                        if (neighborHeightData.height() > currentHeightData.height()) {
                            var24 /= 2.0F;
                        }

                        var16 += neighborHeightData.variance() * var24;
                        var17 += neighborHeightData.height() * var24;
                        var18 += var24;
                    }
                }

                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var13] / (double)8000.0F;
                if (var47 < (double)0.0F) {
                    var47 = -var47 * 0.3;
                }

                var47 = var47 * (double)3.0F - (double)2.0F;
                if (var47 < (double)0.0F) {
                    var47 /= (double)2.0F;
                    if (var47 < (double)-1.0F) {
                        var47 = (double)-1.0F;
                    }

                    var47 /= 1.4;
                    var47 /= (double)2.0F;
                } else {
                    if (var47 > (double)1.0F) {
                        var47 = (double)1.0F;
                    }

                    var47 /= (double)8.0F;
                }

                ++var13;

                for(int var46 = 0; var46 < par6; ++var46) {
                    double var48 = (double)var17;
                    double var26 = (double)var16;
                    var48 += var47 * 0.2;
                    var48 = var48 * (double)par6 / (double)16.0F;
                    double var28 = (double)par6 / (double)2.0F + var48 * (double)4.0F;
                    double var30 = (double)0.0F;
                    double var32 = ((double)var46 - var28) * (double)12.0F * (double)128.0F / (double)128.0F / var26;
                    if (var32 < (double)0.0F) {
                        var32 *= (double)4.0F;
                    }

                    double var34 = this.noise1[var12] / (double)512.0F;
                    double var36 = this.noise2[var12] / (double)512.0F;
                    double var38 = (this.noise3[var12] / (double)10.0F + (double)1.0F) / (double)2.0F;
                    if (var38 < (double)0.0F) {
                        var30 = var34;
                    } else if (var38 > (double)1.0F) {
                        var30 = var36;
                    } else {
                        var30 = var34 + (var36 - var34) * var38;
                    }

                    var30 -= var32;
                    if (var46 > par6 - 4) {
                        double var40 = (double)((float)(var46 - (par6 - 4)) / 3.0F);
                        var30 = var30 * ((double)1.0F - var40) + (double)-10.0F * var40;
                    }

                    par1ArrayOfDouble[var12] = var30;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
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
        return "RandomLevelSource";
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
