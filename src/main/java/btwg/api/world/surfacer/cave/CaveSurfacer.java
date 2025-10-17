package btwg.api.world.surfacer.cave;

import btwg.api.biome.CaveBiome;
import btwg.api.world.generate.noise.NoiseProvider;
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
        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;
    }

    protected void initNoise(long seed) {}

    public enum Direction {
        UP, DOWN
    }
}
