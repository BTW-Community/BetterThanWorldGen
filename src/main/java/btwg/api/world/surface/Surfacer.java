package btwg.api.world.surface;

import btwg.api.world.generate.ChunkData;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

import java.util.Random;

public abstract class Surfacer {
    private boolean hasInitNoise;
    private World lastWorld;

    public final void replaceBlockForLocation(World world, int x, int y, int z, int depth, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoise(world, world.rand);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        this.replaceBlock(x, y, z, depth, biome, blockIDs, metadata);
    }

    protected abstract void replaceBlock(int x, int y, int z, int depth, BiomeGenBase biome, short[] blockIDs, byte[] metadata);

    protected void initNoise(World world, Random rand) {}

    public static int index(int x, int y, int z) {
        return y + 128 * (z + 16 * x);
    }
}
