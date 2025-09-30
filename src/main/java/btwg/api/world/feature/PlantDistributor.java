package btwg.api.world.feature;

import btwg.mod.block.BTWGBlocks;
import btwg.api.block.blocks.TallPlantBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class PlantDistributor {
    protected int patchesPerChunk;
    protected int patchQuantity;
    protected int patchSize;

    private boolean hasInitNoise;
    private World lastWorld;

    public PlantDistributor(int patchesPerChunk, int patchQuantity, int patchSize) {
        this.patchesPerChunk = patchesPerChunk;
        this.patchQuantity = patchQuantity;
        this.patchSize = patchSize;
    }

    public PlantDistributor(int patchesPerChunk) {
        this(patchesPerChunk, 128, 8);
    }

    public PlantDistributor() {
        this(1);
    }

    public void setPlant(World world, Random rand, int x, int y, int z) {
        if (rand.nextInt(10) == 0) {
            this.placeDoublePlant(world, rand, x, y, z, BTWGBlocks.tallGrass.blockID, 0);
        }
        else {
            this.placeSinglePlant(world, rand, x, y, z, Block.tallGrass.blockID, 1);
        }
    }

    protected void placeSinglePlant(World world, Random rand, int x, int y, int z, int blockID, int metadata) {
        if (world.isAirBlock(x, y, z) && Block.blocksList[blockID].canBlockStay(world, x, y, z)) {
            world.setBlockAndMetadata(x, y, z, blockID, metadata);
        }
    }

    protected void placeDoublePlant(World world, Random rand, int x, int y, int z, int blockID, int metadata) {
        if (Block.blocksList[blockID] instanceof TallPlantBlock tallPlantBlock) {
            if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z)
                    && tallPlantBlock.canGrowOnBlock(world, x, y - 1, z))
            {
                world.setBlockAndMetadata(x, y, z, blockID, metadata);
                world.setBlockAndMetadata(x, y + 1, z, blockID, tallPlantBlock.setTopBlock(metadata, true));
            }
        }
    }

    public final void generatePlantsForChunk(World world, Random rand, int x, int z) {
        if (!this.hasInitNoise || world != this.lastWorld) {
            this.initNoise(world, rand);
            this.hasInitNoise = true;
            this.lastWorld = world;
        }

        int patchesPerChunk = this.getPatchesPerChunk(world, rand, x, z);

        if (patchesPerChunk < 0) {
            if (rand.nextInt(-patchesPerChunk) == 0) {
                generatePatch(world, rand, x, z);
            }
        }
        else {
            for (int n = 0; n < patchesPerChunk; n++) {
                generatePatch(world, rand, x, z);
            }
        }
    }

    protected void generatePatch(World world, Random rand, int x, int z) {
        int patchX = x + rand.nextInt(16) + 8;
        int patchZ = z + rand.nextInt(16) + 8;
        int patchY = world.getHeightValue(patchX, patchZ);

        for (int count = 0; count < this.patchQuantity; count++) {
            int i = patchX + rand.nextInt(this.patchSize) - rand.nextInt(this.patchSize);
            int k = patchZ + rand.nextInt(this.patchSize) - rand.nextInt(this.patchSize);
            int j = patchY + rand.nextInt(this.patchSize / 2) - rand.nextInt(this.patchSize / 2);

            this.setPlant(world, rand, i, j, k);
        }
    }

    /**
     * @return Number of patches per chunk. Negative numbers mean fractional patches e.g. -10 means 1/10 of chunks will have a patches
     */
    public int getPatchesPerChunk(World world, Random rand, int x, int z) {
        return this.patchesPerChunk;
    }

    protected void initNoise(World world, Random rand) {}
}
