package btwg.api.world.surface;

import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;

public class SandySurfacer extends Surfacer {
    private OpenSimplexOctavesFast sandHeightNoise;

    @Override
    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata) {
        short blockID = blockIDs[index(i, j, k)];

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        double sandHeightScale = 1/64D;
        int sandHeight = (int) Math.round(this.sandHeightNoise.noise2(x, z, sandHeightScale) + 1);

        // TODO: Dynamic sea level
        if (depth >= 0 && lastSurface < 64 + sandHeight) {
            int maxSoilDepth = this.getSoilDepth(x, z);
            int maxSandstoneDepth = this.getSandstoneDepth(x, z);

            if (depth < maxSoilDepth) {
                blockID = (short) Block.sand.blockID;
            }
            else if (depth < maxSoilDepth + maxSandstoneDepth) {
                blockID = (short) Block.sandStone.blockID;
            }

            blockIDs[index(i, j, k)] = blockID;
            metadata[index(i, j, k)] = 0;
        }
        else {
            super.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata);
        }
    }

    @Override
    protected void initNoise(long seed) {
        this.sandHeightNoise = new OpenSimplexOctavesFast(seed + 250L, 6);
    }
}
