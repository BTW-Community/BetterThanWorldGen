package btwg.api.world.surfacer;

import btwg.api.block.StoneType;
import btwg.api.world.generate.noise.NoiseProvider;
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

    public final void replaceBlockForLocation(World world, int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata, NoiseProvider noiseProvider) {
        if (depth == -1) {
            return;
        }

        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoiseDefault(world.getSeed() + 500L);
            this.initNoise(world.getSeed() + 1000L);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        this.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata, noiseProvider);
    }

    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface,  BiomeGenBase biome, short[] blockIDs, byte[] metadata, NoiseProvider noiseProvider) {
        int height = blockIDs.length / 256;

        short blockID = blockIDs[index(i, j, k, height)];
        byte meta = metadata[index(i, j, k, height)];

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        int maxSoilDepth = this.getSoilDepth(x, z);
        int maxSandstoneDepth = this.getSandstoneDepth(x, z);

        StoneType stoneType = noiseProvider.getStoneType(chunkX, chunkZ)[index(i, j, k, height)];

        short topBlock = replaceBlockForStoneType(biome.topBlock, biome.topBlockMetadata, stoneType);
        byte topBlockMetadata = replaceMetadataForStoneType(biome.topBlock, biome.topBlockMetadata, stoneType);

        short fillerBlock = replaceBlockForStoneType(biome.fillerBlock, biome.fillerBlockMetadata, stoneType);
        byte fillerBlockMetadata = replaceMetadataForStoneType(biome.fillerBlock, biome.fillerBlockMetadata, stoneType);

        if (depth == 0) {
            if (blockIDs[index(i, j + 1, k, height)] == 0) {
                blockID = topBlock;
                meta = topBlockMetadata;
            }
            else {
                blockID = fillerBlock;
                meta = fillerBlockMetadata;
            }

            blockIDs[index(i, j, k, height)] = blockID;
            metadata[index(i, j, k, height)] = meta;
        }
        else if (depth > 0) {
            if (depth < maxSoilDepth) {
                blockID = fillerBlock;
                meta = fillerBlockMetadata;
            }
            else if (depth < maxSoilDepth + maxSandstoneDepth && biome.fillerBlock == Block.sand.blockID && biome.fillerBlockMetadata == 0) {
                blockID = (short) Block.sandStone.blockID;
                meta = 0;
            }

            blockIDs[index(i, j, k, height)] = blockID;
            metadata[index(i, j, k, height)] = meta;
        }
    }

    // TODO: replace this with delegation to block
    protected short replaceBlockForStoneType(short id, byte meta, StoneType stoneType) {
        if (id == Block.stone.blockID) {
            return (short) stoneType.stoneID();
        }

        if (id == Block.grass.blockID) {
            return (short) stoneType.grassID();
        }

        if (id == Block.dirt.blockID) {
            return (short) stoneType.dirtID();
        }

        if (id == Block.gravel.blockID) {
            return (short) stoneType.gravelID();
        }

        return id;
    }

    protected byte replaceMetadataForStoneType(short id, byte meta, StoneType stoneType) {
        if (id == Block.stone.blockID) {
            return (byte) stoneType.stoneMetadata();
        }

        if (id == Block.grass.blockID) {
            return (byte) stoneType.grassMetadata();
        }

        if (id == Block.dirt.blockID) {
            return (byte) stoneType.dirtMetadata();
        }

        if (id == Block.gravel.blockID) {
            return (byte) stoneType.gravelMetadata();
        }

        return meta;
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
        double sandstoneDepthScale = 1/256D;
        return (int) (this.sandstoneDepthNoise.noise2(x, z, sandstoneDepthScale) * 4) + 9;
    }

    public static int index(int x, int y, int z, int yHeight) {
        return y + yHeight * (z + 16 * x);
    }
}
