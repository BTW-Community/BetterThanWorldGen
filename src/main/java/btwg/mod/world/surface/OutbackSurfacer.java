package btwg.mod.world.surface;

import btwg.api.world.generate.noise.NoiseProvider;
import btwg.api.world.generate.noise.function.OpenSimplexOctavesFast;
import btwg.api.world.surface.Surfacer;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.BiomeGenBase;

public class OutbackSurfacer extends Surfacer {
    private OpenSimplexOctavesFast sandyDirtNoise;

    @Override
    public void replaceBlock(int chunkX, int chunkZ, int i, int j, int k, int depth, int lastSurface, BiomeGenBase biome, short[] blockIDs, byte[] metadata, NoiseProvider noiseProvider) {
        int height = blockIDs.length / 256;

        short blockID = blockIDs[index(i, j, k, height)];

        int x = chunkX * 16 + i;
        int z = chunkZ * 16 + k;

        double sandHeightScale = 1/20D;
        double sandyDirtNoiseValue = this.sandyDirtNoise.noise2(x, z, sandHeightScale);

        if (sandyDirtNoiseValue > -0.25 && sandyDirtNoiseValue < 0.25) {
            if (depth == 0) {
                if (blockIDs[index(i, j + 1, k, height)] == 0) {
                    blockID = (short) BTWGBlocks.grassyEarthenClay.blockID;
                }
                else {
                    blockID = (short) BTWGBlocks.earthenClay.blockID;
                }

                blockIDs[index(i, j, k, height)] = blockID;
                metadata[index(i, j, k, height)] = 0;
            }
            else if (depth > 0) {
                if (depth < super.getSoilDepth(x, z)) {
                    blockID = (short) BTWGBlocks.earthenClay.blockID;
                }

                blockIDs[index(i, j, k, height)] = blockID;
                metadata[index(i, j, k, height)] = 0;
            }
        }
        else {
            super.replaceBlock(chunkX, chunkZ, i, j, k, depth, lastSurface, biome, blockIDs, metadata, noiseProvider);
        }
    }

    @Override
    protected void initNoise(long seed) {
        this.sandyDirtNoise = new OpenSimplexOctavesFast(seed + 250L, 6);
    }
}
