package btwg.api.world.surface;

import btwg.api.world.generate.ChunkData;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;

public class SandySurfacer extends Surfacer {
    @Override
    public void replaceBlock(int x, int y, int z, int depth, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        short blockID = blockIDs[index(x, y, z)];

        // TODO: Dynamic sea level
        if (depth >= 0 && y < 66) {
            // TODO: Vary soil depth
            if (depth < 4) {
                blockID = (short) Block.sand.blockID;
            }
            else if (depth < 7) {
                blockID = (short) Block.sandStone.blockID;
            }

            blockIDs[index(x, y, z)] = blockID;
            metadata[index(x, y, z)] = 0;
        }
        else {
            DefaultSurfacer.INSTANCE.replaceBlock(x, y, z, depth, biome, blockIDs, metadata);
        }
    }
}
