//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package btwg.api.world.generate;

import btwg.api.biome.BiomeInterface;
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
    private double[] stoneNoise = new double[256];
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
    
    public ChunkProvider(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
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
    
    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, var7 + 5, var9 + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * var4, 0, par2 * var4, var7, var8, var9);
        
        for(int var10 = 0; var10 < var4; ++var10) {
            for(int var11 = 0; var11 < var4; ++var11) {
                for(int var12 = 0; var12 < var5; ++var12) {
                    double var13 = 0.125;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;
                    
                    for(int var31 = 0; var31 < 8; ++var31) {
                        double var32 = 0.25;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;
                        
                        for(int var42 = 0; var42 < 4; ++var42) {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;
                            var43 -= var44;
                            double var45 = 0.25;
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;
                            
                            for(int var51 = 0; var51 < 4; ++var51) {
                                if ((var47 += var49) > 0.0) {
                                    par3ArrayOfByte[var43 += var44] = (byte)Block.stone.blockID;
                                } else if (var12 * 8 + var31 < var6) {
                                    par3ArrayOfByte[var43 += var44] = (byte)Block.waterStill.blockID;
                                } else {
                                    par3ArrayOfByte[var43 += var44] = 0;
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
    
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        byte var5 = 63;
        double var6 = 0.03125;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);
        
        for(int var8 = 0; var8 < 16; ++var8) {
            for(int var9 = 0; var9 < 16; ++var9) {
                BiomeGenBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);
                int var13 = -1;
                byte var14 = var10.topBlock;
                byte var15 = var10.fillerBlock;
                
                for(int var16 = 127; var16 >= 0; --var16) {
                    int var17 = (var9 * 16 + var8) * 128 + var16;
                    if (var16 <= 0 + this.rand.nextInt(5)) {
                        par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
                    } else {
                        byte var18 = par3ArrayOfByte[var17];
                        if (var18 == 0) {
                            var13 = -1;
                        } else if (var18 == Block.stone.blockID) {
                            if (var13 == -1) {
                                if (var12 <= 0) {
                                    var14 = 0;
                                    var15 = (byte)Block.stone.blockID;
                                } else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
                                    var14 = var10.topBlock;
                                    var15 = var10.fillerBlock;
                                }
                                
                                if (var16 < var5 && var14 == 0) {
                                    if (var11 < 0.15F) {
                                        var14 = (byte)Block.ice.blockID;
                                    } else {
                                        var14 = (byte)Block.waterStill.blockID;
                                    }
                                }
                                
                                var13 = var12;
                                if (var16 >= var5 - 1) {
                                    par3ArrayOfByte[var17] = var14;
                                } else {
                                    par3ArrayOfByte[var17] = var15;
                                }
                            } else if (var13 > 0) {
                                --var13;
                                par3ArrayOfByte[var17] = var15;
                                if (var13 == 0 && var15 == Block.sand.blockID) {
                                    var13 = this.rand.nextInt(4);
                                    var15 = (byte)Block.sandStone.blockID;
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
        byte[] var3 = new byte['耀'];
        this.generateTerrain(par1, par2, var3);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
        this.ravineGenerator.generate(this, this.worldObj, par1, par2, var3);
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, var3);
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, var3);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, var3);
        }
        
        Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
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
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121, 1.121, 0.5);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0, 200.0, 0.5);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / 80.0, var45 / 160.0, var44 / 80.0);
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
                BiomeGenBase var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];
                
                for(int var21 = -var19; var21 <= var19; ++var21) {
                    for(int var22 = -var19; var22 <= var19; ++var22) {
                        BiomeGenBase var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];
                        float var24 = this.parabolicField[var21 + 2 + (var22 + 2) * 5] / (((BiomeInterface) var23).getHeightData().get(BetterThanWorldGen.V1_0_0).height() + 2.0F);
                        if (((BiomeInterface) var23).getHeightData().get(BetterThanWorldGen.V1_0_0).height() > ((BiomeInterface) var20).getHeightData().get(BetterThanWorldGen.V1_0_0).height()) {
                            var24 /= 2.0F;
                        }
                        
                        var16 += ((BiomeInterface) var23).getHeightData().get(BetterThanWorldGen.V1_0_0).variance() * var24;
                        var17 += ((BiomeInterface) var23).getHeightData().get(BetterThanWorldGen.V1_0_0).height() * var24;
                        var18 += var24;
                    }
                }
                
                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var13] / 8000.0;
                if (var47 < 0.0) {
                    var47 = -var47 * 0.3;
                }
                
                var47 = var47 * 3.0 - 2.0;
                if (var47 < 0.0) {
                    var47 /= 2.0;
                    if (var47 < -1.0) {
                        var47 = -1.0;
                    }
                    
                    var47 /= 1.4;
                    var47 /= 2.0;
                } else {
                    if (var47 > 1.0) {
                        var47 = 1.0;
                    }
                    
                    var47 /= 8.0;
                }
                
                ++var13;
                
                for(int var46 = 0; var46 < par6; ++var46) {
                    double var48 = (double)var17;
                    double var26 = (double)var16;
                    var48 += var47 * 0.2;
                    var48 = var48 * (double)par6 / 16.0;
                    double var28 = (double)par6 / 2.0 + var48 * 4.0;
                    double var30 = 0.0;
                    double var32 = ((double)var46 - var28) * 12.0 * 128.0 / 128.0 / var26;
                    if (var32 < 0.0) {
                        var32 *= 4.0;
                    }
                    
                    double var34 = this.noise1[var12] / 512.0;
                    double var36 = this.noise2[var12] / 512.0;
                    double var38 = (this.noise3[var12] / 10.0 + 1.0) / 2.0;
                    if (var38 < 0.0) {
                        var30 = var34;
                    } else if (var38 > 1.0) {
                        var30 = var36;
                    } else {
                        var30 = var34 + (var36 - var34) * var38;
                    }
                    
                    var30 -= var32;
                    if (var46 > par6 - 4) {
                        double var40 = (double)((float)(var46 - (par6 - 4)) / 3.0F);
                        var30 = var30 * (1.0 - var40) + -10.0 * var40;
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
        
        int var12;
        int var13;
        int var14;
        if (var6 != BiomeGenBase.desert && var6 != BiomeGenBase.desertHills && !var11 && this.rand.nextInt(4) == 0) {
            var12 = var4 + this.rand.nextInt(16) + 8;
            var13 = this.rand.nextInt(128);
            var14 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
        }
        
        if (!var11 && this.rand.nextInt(8) == 0) {
            var12 = var4 + this.rand.nextInt(16) + 8;
            var13 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            var14 = var5 + this.rand.nextInt(16) + 8;
            if (var13 < 63 || this.rand.nextInt(10) == 0) {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
            }
        }
        
        for(var12 = 0; var12 < 8; ++var12) {
            var13 = var4 + this.rand.nextInt(16) + 8;
            var14 = this.rand.nextInt(128);
            int var15 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15);
        }
        
        var6.decorate(this.worldObj, this.rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        var4 += 8;
        var5 += 8;
        
        for(var12 = 0; var12 < 16; ++var12) {
            for(var13 = 0; var13 < 16; ++var13) {
                var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);
                if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5)) {
                    this.worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID, 0, 2);
                }
                
                if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5)) {
                    this.worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID, 0, 2);
                } else if (this.worldObj.canSnowAt(var12 + var4, var14 + 1, var13 + var5)) {
                    this.worldObj.setBlock(var12 + var4, var14 + 1, var13 + var5, Block.snow.blockID, 0, 2);
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
            this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.villageGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
            this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
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
                
                int iStrataHeight;
                int iTempBlockID;
                for(iStrataHeight = 24 + world.rand.nextInt(2); iTempJ <= iStrataHeight; ++iTempJ) {
                    iTempBlockID = chunk.getBlockID(iTempI, iTempJ, iTempK);
                    if (iTempBlockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(iTempI, iTempJ, iTempK, 2);
                    }
                }
                
                for(iStrataHeight = 48 + world.rand.nextInt(2); iTempJ <= iStrataHeight; ++iTempJ) {
                    iTempBlockID = chunk.getBlockID(iTempI, iTempJ, iTempK);
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
