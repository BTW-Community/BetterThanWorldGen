package btwg.api.world.generate;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.CaveBiome;
import btwg.api.biome.data.BiomeData;
import btwg.api.block.StoneType;
import btwg.api.block.blocks.RegolithBlock;
import btwg.api.world.generate.noise.NoiseProvider;
import btwg.api.world.surfacer.Surfacer;
import btwg.api.world.surfacer.cave.CaveSurfacer;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public final class ChunkProvider implements IChunkProvider {
    private final NoiseProvider noiseProvider;

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

    public ChunkProvider(World world, NoiseProvider noiseProvider, boolean mapFeaturesEnabled) {
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

        double[] terrainNoise = this.noiseProvider.generateTerrainNoise(chunkX, chunkZ);
        double[] caveNoise = this.noiseProvider.generateCaveNoise(chunkX, chunkZ);
        StoneType[] stoneTypeNoise = this.noiseProvider.generateStoneTypes(chunkX, chunkZ);

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
                        blockIDs[index] = (short) stoneTypeNoise[index].stoneID();
                        metadata[index] = (byte) stoneTypeNoise[index].stoneMetadata();
                    }
                    else if (j < this.noiseProvider.getSeaLevel()) {
                        blockIDs[index] = (short) Block.waterStill.blockID;
                    }
                }
            }
        }

        BiomeGenBase[] biomes = this.noiseProvider.generateBiomes(chunkX, chunkZ);
        this.replaceBlocksForBiome(chunkX, chunkZ, blockIDs, metadata, biomes);

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                for (int j = 0; j < height; j++) {
                    int index = (i * 16 + k) * height + j;

                    if (caveNoise[index] < NoiseProvider.CAVE_THRESHOLD
                            && blockIDs[index] != Block.waterStill.blockID
                            && !isAdjacentToWater(chunkX, chunkZ, i, j, k, height, blockIDs))
                    {
                        if (j < 16) {
                            blockIDs[index] = (short) Block.lavaMoving.blockID;
                            metadata[index] = 0;
                        }
                        else {
                            blockIDs[index] = 0;
                        }

                        if (j < height - 1 && blockIDs[index + 1] == Block.sand.blockID) {
                            blockIDs[index + 1] = (short) Block.sandStone.blockID;
                            metadata[index] = 0;
                        }


                        if (j > 0) {
                            int blockBelowID = blockIDs[index - 1];
                            Block blockBelow = Block.blocksList[blockBelowID];

                            if (blockBelowID == biomes[i * 16 + k].fillerBlock) {
                                blockIDs[index - 1] = biomes[i * 16 + k].topBlock;
                                metadata[index - 1] = biomes[i * 16 + k].topBlockMetadata;
                            }
                            else if (blockBelow instanceof RegolithBlock regolithBlock) {
                                blockIDs[index - 1] = (short) regolithBlock.getStoneType().grassID();
                                metadata[index - 1] = (byte) regolithBlock.getStoneType().grassMetadata();
                            }
                        }
                    }
                }
            }
        }

        CaveBiome[][] caveBiomes = this.noiseProvider.generateCaveBiomes(chunkX, chunkZ);
        this.replaceBlocksForCaveBiome(chunkX, chunkZ, blockIDs, metadata, caveBiomes);
        
        if (this.mapFeaturesEnabled) {
            this.mineshaftGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.villageGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.strongholdGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
            this.scatteredFeatureGenerator.generate(this, this.world, chunkX, chunkZ, blockIDs, metadata);
        }

        Chunk chunk = new Chunk(this.world, blockIDs, metadata, chunkX, chunkZ);
        byte[] chunkBiomes = chunk.getBiomeArray();

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                // Chunk biomes uses inverse indexing from everything else :/
                chunkBiomes[k * 16 + i] = (byte) biomes[i * 16 + k].biomeID;
            }
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void replaceBlocksForBiome(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata, BiomeGenBase[] biomes) {
        var defaultSurfacer = new BiomeData<>(Surfacer.DEFAULT);
        int height = blockIDs.length / 256;

        for(int k = 0; k < 16; ++k) {
            for(int i = 0; i < 16; ++i) {
                BiomeGenBase biome = biomes[i * 16 + k];

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
                    surfacer.get(BetterThanWorldGen.V1_0_0).replaceBlockForLocation(this.world, chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata, this.noiseProvider);

                    depth++;
                }
            }
        }
    }

    private void replaceBlocksForCaveBiome(int chunkX, int chunkZ, short[] blockIDs, byte[] metadata, CaveBiome[][] biomes) {
        double[] heightmap = this.noiseProvider.getHeightmap(chunkX, chunkZ);

        for(int k = 0; k < 16; ++k) {
            for(int i = 0; i < 16; ++i) {
                CaveBiome shallowCaveBiome = biomes[i * 16 + k][0];
                CaveBiome deepCaveBiome = biomes[i * 16 + k][1];

                double surfaceHeight = heightmap[i * 16 + k] * NoiseProvider.TERRAIN_SCALE;
                double deepCaveThreshold = surfaceHeight / 2;

                this.replaceCaveBiomeBlocksForColumn(world, chunkX, chunkZ, i, k, CaveSurfacer.Direction.DOWN, shallowCaveBiome, deepCaveBiome, deepCaveThreshold, blockIDs, metadata);
                this.replaceCaveBiomeBlocksForColumn(world, chunkX, chunkZ, i, k, CaveSurfacer.Direction.UP, shallowCaveBiome, deepCaveBiome, deepCaveThreshold, blockIDs, metadata);
            }
        }
    }

    private void replaceCaveBiomeBlocksForColumn(
            World world,
            int chunkX, int chunkZ,
            int i, int k,
            CaveSurfacer.Direction direction,
            CaveBiome shallowCaveBiome, CaveBiome deepCaveBiome,
            double deepCaveThreshold,
            short[] blockIDs, byte[] metadata
    ) {
        var defaultSurfacer = new BiomeData<>(CaveSurfacer.DEFAULT);
        int maxHeight = blockIDs.length / 256;

        int depth = -1;
        int lastSurface = -1;

        for (int j = maxHeight - 1; j >= 0; --j) {
            int index = (i * 16 + k) * maxHeight + j;

            int blockID = blockIDs[index];

            if (depth == 0) {
                lastSurface = j;
            }

            if (blockID == 0 || blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID || blockID == Block.ice.blockID) {
                depth = -1;
            }

            CaveBiome caveBiome = j < deepCaveThreshold ? shallowCaveBiome : deepCaveBiome;

            var surfacer = caveBiome.getSurfacer().orElse(defaultSurfacer);

            // TODO: Update with real version handling
            surfacer.get(BetterThanWorldGen.V1_0_0).replaceBlockForLocation(this.world, chunkX, chunkZ, i, j, k, depth, lastSurface, direction, caveBiome, blockIDs, metadata, this.noiseProvider);

            depth++;
        }

    }

    private boolean isAdjacentToWater(int chunkX, int chunkZ, int i, int j, int k, int height, short[] blockIDs) {
        int worldX = chunkX * 16 + i;
        int worldZ = chunkZ * 16 + k;

        // Check block above
        if (j < height - 1) {
            int aboveIndex = (i * 16 + k) * height + (j + 1);
            if (blockIDs[aboveIndex] == Block.waterStill.blockID || blockIDs[aboveIndex] == Block.waterMoving.blockID) {
                return true;
            }
        }

        // Check horizontal neighbors
        // For edge blocks, use world.getBlockId() which handles chunk boundaries

        // Check -X direction
        if (i > 0) {
            int neighborIndex = ((i - 1) * 16 + k) * height + j;
            if (blockIDs[neighborIndex] == Block.waterStill.blockID || blockIDs[neighborIndex] == Block.waterMoving.blockID) {
                return true;
            }
        }

        // Check +X direction
        if (i < 15) {
            int neighborIndex = ((i + 1) * 16 + k) * height + j;
            if (blockIDs[neighborIndex] == Block.waterStill.blockID || blockIDs[neighborIndex] == Block.waterMoving.blockID) {
                return true;
            }
        }

        // Check -Z direction
        if (k > 0) {
            int neighborIndex = (i * 16 + (k - 1)) * height + j;
            if (blockIDs[neighborIndex] == Block.waterStill.blockID || blockIDs[neighborIndex] == Block.waterMoving.blockID) {
                return true;
            }
        }

        // Check +Z direction
        if (k < 15) {
            int neighborIndex = (i * 16 + (k + 1)) * height + j;
            if (blockIDs[neighborIndex] == Block.waterStill.blockID || blockIDs[neighborIndex] == Block.waterMoving.blockID) {
                return true;
            }
        }

        return false;
    }

    public NoiseProvider getNoiseProvider() {
        return noiseProvider;
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
            //this.generateStrata(worldObj, chunkX, chunkZ);
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

                for (int strataHeight = 33 + world.rand.nextInt(2); localY <= strataHeight; ++localY) {
                    int blockID = chunk.getBlockID(localX, localY, localZ);
                    if (blockID == Block.stone.blockID) {
                        chunk.setBlockMetadata(localX, localY, localZ, 2);
                    }
                }

                for (int strataHeight2 = 66 + world.rand.nextInt(2); localY <= strataHeight2; ++localY) {
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
