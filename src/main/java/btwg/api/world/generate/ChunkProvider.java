package btwg.api.world.generate;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData;
import btwg.api.world.generate.noise.NoiseProvider;
import btwg.api.world.surface.Surfacer;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public final class ChunkProvider<T extends NoiseProvider> implements IChunkProvider {
    private final T noiseProvider;

    private final World world;
    private final boolean mapFeaturesEnabled;

    private final Random rand;
    private final Random structureRand;

    private final MapGenBase caveGenerator = new MapGenCaves();
    private final MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private final MapGenVillage villageGenerator = new MapGenVillage();
    private final MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private final MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private final MapGenBase ravineGenerator = new MapGenRavine();

    private final NoiseGeneratorOctaves legacySoilDepthNoiseGenerator;
    private double[] legacySoilDepthNoise = new double[256];

    public ChunkProvider(World world, T noiseProvider, boolean mapFeaturesEnabled) {
        this.noiseProvider = noiseProvider;

        this.world = world;
        this.rand = new Random(this.getSeed());
        this.structureRand = new Random(this.getSeed());
        
        this.mapFeaturesEnabled = mapFeaturesEnabled;

        this.legacySoilDepthNoiseGenerator = new NoiseGeneratorOctaves(this.rand, 4);
    }

    public long getSeed() {
        return this.noiseProvider.seed;
    }

    //------ Terrain Generation ------//

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);

        double[] terrainNoise = this.noiseProvider.getTerrainNoise(chunkX, chunkZ);

        if (terrainNoise == null) {
            throw new IllegalStateException("No terrain noise provided!");
        }

        if (terrainNoise.length % 256 != 0) {
            throw new IllegalStateException("Terrain noise size must be a multiple of 256! (e.g. it must be a multiple of 16 * 16 blocks)");
        }

        short[] blockIDs = new short[terrainNoise.length];
        byte[] metadata = new byte[terrainNoise.length];

        int height = terrainNoise.length / 256;

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                for (int j = 0; j < height; j++) {
                    int index = (i * 16 + k) * height + j;

                    if (terrainNoise[index] > 0) {
                        blockIDs[index] = (short) Block.stone.blockID;
                    }
                    else if (j < this.noiseProvider.getSeaLevel()) {
                        blockIDs[index] = (short) Block.waterStill.blockID;
                    }
                }
            }
        }

        BiomeGenBase[] biomes = this.noiseProvider.getBiomes(chunkX, chunkZ);
        this.replaceBlocksForBiome(chunkX, chunkZ, blockIDs, metadata, biomes);

        //this.caveGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
        //this.ravineGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
        
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.villageGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.strongholdGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.scatteredFeatureGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
        }

        Chunk chunk = new Chunk(this.world, blockIDs, metadata, chunkX, chunkZ);
        byte[] chunkBiomes = chunk.getBiomeArray();

        for(int i = 0; i < chunkBiomes.length; ++i) {
            chunkBiomes[i] = (byte) biomes[i].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void replaceBlocksForBiome(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata, BiomeGenBase[] biomes) {
        byte seaLevel = this.noiseProvider.getSeaLevel();
        double var6 = 0.03125F;
        this.legacySoilDepthNoise = this.legacySoilDepthNoiseGenerator.generateNoiseOctaves(this.legacySoilDepthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, var6 * (double)2.0F, var6 * (double)2.0F, var6 * (double)2.0F);

        var defaultSurfacer = new BiomeData<>(Surfacer.DEFAULT);

        int height = blockIDs.length / 256;

        for(int k = 0; k < 16; ++k) {
            for(int i = 0; i < 16; ++i) {
                BiomeGenBase biome = biomes[i * 16 + k];

                if (!((BiomeInterface) biome).isVanilla()) {
                    int depth = -1;
                    int lastSurface = -1;

                    for (int j = height - 1; j >= 0; --j) {
                        int index = (i * 16 + k) * height + j;
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
                        surfacer.get(BetterThanWorldGen.V1_0_0).replaceBlockForLocation(this.world, chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);

                        depth++;
                    }
                }
                else {
                    float temperature = biome.getFloatTemperature();
                    int soilDepth = (int) (this.legacySoilDepthNoise[k + i * 16] / (double) 3.0F + (double) 3.0F + this.rand.nextDouble() * (double) 0.25F);
                    int var13 = -1;
                    short topBlock = biome.topBlock;
                    byte topBlockMetadata = biome.topBlockMetadata;
                    short fillerBlock = biome.fillerBlock;
                    byte fillerBlockMetadata = biome.fillerBlockMetadata;

                    for (int j = height - 1; j >= 0; --j) {
                        int index = (i * 16 + k) * height + j;
                        if (j <= this.rand.nextInt(5)) {
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
                                    } else if (j >= seaLevel - 4 && j <= seaLevel + 1) {
                                        topBlock = biome.topBlock;
                                        fillerBlock = biome.fillerBlock;
                                    }

                                    if (j < seaLevel && topBlock == 0) {
                                        if (temperature < 0.15F) {
                                            topBlock = (short) Block.ice.blockID;
                                        } else {
                                            topBlock = (short) Block.waterStill.blockID;
                                        }
                                    }

                                    var13 = soilDepth;
                                    if (j >= seaLevel - 1) {
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

    //------ Chunk Population ------//

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int chunkX, int chunkZ) {
        //if (1==1) return;

        int x = chunkX * 16;
        int z = chunkZ * 16;
        
        BiomeGenBase biome = this.world.getBiomeGenForCoords(x + 16, z + 16);
        this.rand.setSeed(this.world.getSeed());
        long seedModifier1 = this.rand.nextLong() / 2L * 2L + 1L;
        long seedModifier2 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * seedModifier1 + (long) chunkZ * seedModifier2 ^ this.world.getSeed());
        boolean hasVillage = false;
        
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generateStructuresInChunk(this.world, this.rand, chunkX, chunkZ);
            this.structureRand.setSeed((long) chunkX * seedModifier1 + (long) chunkZ * seedModifier2 ^ this.world.getSeed());
            hasVillage = this.villageGenerator.generateStructuresInChunk(this.world, this.structureRand, chunkX, chunkZ);
            this.strongholdGenerator.generateStructuresInChunk(this.world, this.structureRand, chunkX, chunkZ);
            this.scatteredFeatureGenerator.generateStructuresInChunk(this.world, this.structureRand, chunkX, chunkZ);
        }

        if (biome != BiomeGenBase.desert && biome != BiomeGenBase.desertHills && !hasVillage && this.rand.nextInt(4) == 0) {
            int lakeX = x + this.rand.nextInt(16) + 8;
            int lakeY = this.rand.nextInt(128);
            int lakeZ = z + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.world, this.rand, lakeX, lakeY, lakeZ);
        }

        if (!hasVillage && this.rand.nextInt(8) == 0) {
            int lakeX = x + this.rand.nextInt(16) + 8;
            int lakeY = this.rand.nextInt(this.rand.nextInt(120) + 8);
            int lakeZ = z + this.rand.nextInt(16) + 8;
            if (lakeY < 63 || this.rand.nextInt(10) == 0) {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(this.world, this.rand, lakeX, lakeY, lakeZ);
            }
        }

        for (int dungeonCount = 0; dungeonCount < 8; ++dungeonCount) {
            int dungeonX = x + this.rand.nextInt(16) + 8;
            int dungeonY = this.rand.nextInt(128);
            int dungeonZ = z + this.rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.world, this.rand, dungeonX, dungeonY, dungeonZ);
        }

        biome.decorate(this.world, this.rand, x, z);
        SpawnerAnimals.performWorldGenSpawning(this.world, biome, x + 8, z + 8, 16, 16, this.rand);
        
        x += 8;
        z += 8;

        for (int i = 0; i < 16; ++i) {
            for (int k = 0; k < 16; ++k) {
                int precipitationHeight = this.world.getPrecipitationHeight(x + i, z + k);
                if (this.world.isBlockFreezable(i + x, precipitationHeight - 1, k + z)) {
                    this.world.setBlock(i + x, precipitationHeight - 1, k + z, Block.ice.blockID, 0, 2);
                }

                if (this.world.canSnowAt(i + x, precipitationHeight, k + z)) {
                    this.world.setBlock(i + x, precipitationHeight, k + z, Block.snow.blockID, 0, 2);
                } else if (this.world.canSnowAt(i + x, precipitationHeight + 1, k + z)) {
                    this.world.setBlock(i + x, precipitationHeight + 1, k + z, Block.snow.blockID, 0, 2);
                }
            }
        }

        this.btwPostProcessChunk(this.world, x - 8, z - 8);
    }

    private void btwPostProcessChunk(World worldObj, int chunkX, int chunkZ) {
        if (worldObj.provider.dimensionId == 0) {
            this.generateStrata(worldObj, chunkX, chunkZ);
            this.generateAdditionalBrownMushrooms(worldObj, chunkX, chunkZ);
        }
    }

    private void generateAdditionalBrownMushrooms(World worldObj, int chunkX, int chunkZ) {
        if (worldObj.rand.nextInt(4) == 0) {
            WorldGenerator mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
            int mushroomX = chunkX + worldObj.rand.nextInt(16) + 8;
            int mushroomY = worldObj.rand.nextInt(25);
            int mushroomZ = chunkZ + worldObj.rand.nextInt(16) + 8;
            mushroomBrownGen.generate(worldObj, worldObj.rand, mushroomX, mushroomY, mushroomZ);
        }
    }

    private void generateStrata(World world, int chunkX, int chunkZ) {
        Chunk chunk = world.getChunkFromChunkCoords(chunkX >> 4, chunkZ >> 4);

        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                int localY = 0;

                for (int strataHeight = 24 + world.rand.nextInt(2); localY <= strataHeight; ++localY) {
                    int blockID = chunk.getBlockID(localX, localY, localZ);
                    if (blockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(localX, localY, localZ, 2);
                    }
                }

                for (int strataHeight2 = 48 + world.rand.nextInt(2); localY <= strataHeight2; ++localY) {
                    int blockID = chunk.getBlockID(localX, localY, localZ);
                    if (blockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(localX, localY, localZ, 1);
                    }
                }
            }
        }
    }

    //------ Additional Functionality -----//

    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
    }

    @Override
    public boolean saveChunks(boolean bl, IProgressUpdate progressUpdate) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "BTWGChunkProvider";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        BiomeGenBase biome = this.world.getBiomeGenForCoords(x, z);

        if (creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(x, y, z)) {
            return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
        }

        return biome.getSpawnableList(creatureType);
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String structureName, int x, int y, int z) {
        return "Stronghold".equals(structureName) ? this.strongholdGenerator.getNearestInstance(world, x, y, z) : null;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.world, chunkX, chunkZ, null, null);
            this.villageGenerator.generate(this, this.world, chunkX, chunkZ, null, null);
            this.strongholdGenerator.generate(this, this.world, chunkX, chunkZ, null, null);
            this.scatteredFeatureGenerator.generate(this, this.world, chunkX, chunkZ, null, null);
        }
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void saveExtraData() {}
}
