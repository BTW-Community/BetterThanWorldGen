package btwg.api.world.surface;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;

public final class DefaultSurfacer extends Surfacer {
    public static final DefaultSurfacer INSTANCE = new DefaultSurfacer();

    private DefaultSurfacer() {}

    @Override
    public void replaceBlock(int x, int y, int z, int depth, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        short blockID = blockIDs[index(x, y, z)];
        byte meta = metadata[index(x, y, z)];

        if (depth == 0) {
            blockID = biome.topBlock;
            meta = biome.topBlockMetadata;

            blockIDs[index(x, y, z)] = blockID;
            metadata[index(x, y, z)] = meta;
        }
        else if (depth > 0) {
            // TODO: Vary soil depth
            if (depth < 4) {
                blockID = biome.fillerBlock;
                meta = biome.fillerBlockMetadata;
            }
            else if (depth < 7 && biome.fillerBlock == Block.sand.blockID && biome.fillerBlockMetadata == 0) {
                blockID = (short) Block.sandStone.blockID;
                meta = 0;
            }

            blockIDs[index(x, y, z)] = blockID;
            metadata[index(x, y, z)] = meta;
        }
    }
}
