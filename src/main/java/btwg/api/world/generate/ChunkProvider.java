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

    private final Random rand = new Random();

    private final NoiseGeneratorOctaves legacySoilDepthNoiseGenerator;
    private double[] legacySoilDepthNoise = new double[256];

    public ChunkProvider(World world, T noiseProvider, boolean mapFeaturesEnabled) {
        this.noiseProvider = noiseProvider;

        this.world = world;
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

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                for (int j = 0; j < terrainNoise.length / 256; j++) {
                    int index = (i * 16 + k) * terrainNoise.length / 256 + j;

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

        // TODO: structure generation

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

                    for (int j = 127; j >= 0; --j) {
                        int index = (i * 16 + k) * 128 + j;
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
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
        // TODO: implement
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
    public List getPossibleCreatures(EnumCreatureType enumCreatureType, int x, int y, int z) {
        // TODO: implement
        return List.of();
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String string, int x, int y, int z) {
        // TODO: implement
        return null;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {
        // TODO: implement
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void saveExtraData() {}
}
