package btwg.api.world.surface;

import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class Surfacer {
    private boolean hasInitNoise;
    private World lastWorld;

    private OpenSimplexOctavesFast soilDepthNoise;
    private OpenSimplexOctavesFast sandstoneDepthNoise;

    public static final Surfacer DEFAULT = new Surfacer();

    public final void replaceBlockForLocation(World world, int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        if (depth == -1) {
            return;
        }

        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoiseDefault(world.getSeed() + 500L);
            this.initNoise(world.getSeed() + 1000L);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        this.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);
    }

    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface,  BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        short blockID = blockIDs[index(i, j, k)];
        byte meta = metadata[index(i, j, k)];

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        int maxSoilDepth = this.getSoilDepth(x, z);
        int maxSandstoneDepth = this.getSandstoneDepth(x, z);

        if (depth == 0) {
            if (blockIDs[index(i, j + 1, k)] == 0) {
                blockID = biome.topBlock;
                meta = biome.topBlockMetadata;
            }
            else {
                blockID = biome.fillerBlock;
                meta = biome.fillerBlockMetadata;
            }

            blockIDs[index(i, j, k)] = blockID;
            metadata[index(i, j, k)] = meta;
        }
        else if (depth > 0) {
            if (depth < maxSoilDepth) {
                blockID = biome.fillerBlock;
                meta = biome.fillerBlockMetadata;
            }
            else if (depth < maxSoilDepth + maxSandstoneDepth && biome.fillerBlock == Block.sand.blockID && biome.fillerBlockMetadata == 0) {
                blockID = (short) Block.sandStone.blockID;
                meta = 0;
            }

            blockIDs[index(i, j, k)] = blockID;
            metadata[index(i, j, k)] = meta;
        }
    }

    private void initNoiseDefault(long seed) {
        this.soilDepthNoise = new OpenSimplexOctavesFast(seed, 6);
        this.sandstoneDepthNoise = new OpenSimplexOctavesFast(seed + 1000L, 6);
    }

    protected void initNoise(long seed) {}

    public final int getSoilDepth(int x, int z) {
        double soilDepthScale = 1/256D;
        return (int) Math.round(this.soilDepthNoise.noise2(x, z, soilDepthScale) + 3);
    }

    public final int getSandstoneDepth(int x, int z) {
        double sandstoneDepthScale = 1/32D;
        return (int) (this.sandstoneDepthNoise.noise2(x, z, sandstoneDepthScale) * 2.5 + 2.5);
    }

    public static int index(int x, int y, int z) {
        return y + 128 * (z + 16 * x);
    }
}
