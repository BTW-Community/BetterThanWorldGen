package btwg.api.world.surfacer.cave;

import btwg.api.biome.CaveBiome;
import btwg.api.world.generate.noise.NoiseProvider;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class CaveSurfacer {
    private boolean hasInitNoise;
    private World lastWorld;

    public static final CaveSurfacer DEFAULT = new CaveSurfacer();

    public final void replaceBlockForLocation(World world, int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, Direction direction, CaveBiome biome, short[] blockIDs, byte[] metadata, NoiseProvider noiseProvider) {
        if (depth == -1) {
            return;
        }

        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoise(world.getSeed() + 1000L);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        this.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, direction, biome, blockIDs, metadata, noiseProvider);
    }

    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface,  Direction direction, CaveBiome biome, short[] blockIDs, byte[] metadata, NoiseProvider noiseProvider) {
        int height = blockIDs.length / 256;

        short blockID = blockIDs[index(i, j, k, height)];
        byte meta = metadata[index(i, j, k, height)];

        short topBlock = biome.topBlockID;
        byte topBlockMetadata = biome.topBlockMetadata;

        short fillerBlock = biome.fillerBlockID;
        byte fillerBlockMetadata = biome.fillerBlockMetadata;
        
        if (topBlock == 0) return;

        int maxSoilDepth = 10;

        if (j < noiseProvider.getHeightmap(chunkX, chunkZ)[i * 16 + k] * NoiseProvider.TOTAL_HEIGHT - 15) {
            if (depth == 0 && direction == Direction.DOWN) {
                if (blockIDs[index(i, j + 1, k, height)] == 0) {
                    blockID = topBlock;
                    meta = topBlockMetadata;
                } else {
                    blockID = fillerBlock;
                    meta = fillerBlockMetadata;
                }

                blockIDs[index(i, j, k, height)] = blockID;
                metadata[index(i, j, k, height)] = meta;
            } else if (depth > 0) {
                if (depth < maxSoilDepth) {
                    blockID = fillerBlock;
                    meta = fillerBlockMetadata;

                    blockIDs[index(i, j, k, height)] = blockID;
                    metadata[index(i, j, k, height)] = meta;
                }
            }
        }
    }

    protected void initNoise(long seed) {}

    public enum Direction {
        UP, DOWN
    }

    public static int index(int x, int y, int z, int yHeight) {
        return y + yHeight * (z + 16 * x);
    }
}
