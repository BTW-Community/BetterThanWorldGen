package btwg.mod.world.surface;

import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.BiomeGenBase;

public class ScrublandShoreSurfacer extends ScrublandSurfacer {

    @Override
    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        int height = blockIDs.length / 256;

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        if (lastSurface < 63 && depth < this.getSoilDepth(x, z)) {
            blockIDs[index(i, j, k, height)] = (short) BTWGBlocks.earthenClay.blockID;
            metadata[index(i, j, k, height)] = 0;
        }
        else {
            super.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);
        }
    }
}
