package btwg.mod.world.surface;

import btwg.api.world.generate.noise.OpenSimplexOctavesFast;
import btwg.api.world.surface.Surfacer;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.BiomeGenBase;

public class ScrublandSurfacer extends Surfacer {
    private OpenSimplexOctavesFast sandyDirtNoise;

    @Override
    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        short blockID = blockIDs[index(i, j, k)];

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        double sandHeightScale = 1/40D;
        double sandyDirtNoiseValue = this.sandyDirtNoise.noise2(x, z, sandHeightScale);

        if (sandyDirtNoiseValue > 0.75) {
            if (depth < this.getSoilDepth(x, z)){
                blockIDs[index(i, j, k)] = (short) BTWGBlocks.sandyDirt.blockID;
                metadata[index(i, j, k)] = 0;
            }
        }
        else {
            super.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);
        }
    }

    @Override
    protected void initNoise(long seed) {
        this.sandyDirtNoise = new OpenSimplexOctavesFast(seed + 250L, 6);
    }
}
