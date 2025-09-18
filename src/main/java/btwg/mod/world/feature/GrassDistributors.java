package btwg.mod.world.feature;

import btwg.api.world.feature.PlantDistributor;
import btwg.mod.block.BTWGBlocks;
import btwg.mod.block.blocks.TallPlantBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public abstract class GrassDistributors {
    public static final PlantDistributor DRY_GRASS = new PlantDistributor(2, 32, 8) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(8);

            int blockID;
            int meta;

            if (r == 0) {
                blockID = Block.deadBush.blockID;
                meta = 0;
            }
            else if (r <= 2) {
                blockID = BTWGBlocks.dryGrass.blockID;
                meta = 0;
            }
            else if (r < 7) {
                blockID = BTWGBlocks.shortDryGrass.blockID;
                meta = 0;
            }
            else {
                if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z)
                        && ((TallPlantBlock) BTWGBlocks.tallDryGrass).canGrowOnBlock(world, x, y - 1, z))
                {
                    world.setBlockAndMetadata(x, y, z, BTWGBlocks.tallDryGrass.blockID, 0);
                    world.setBlockAndMetadata(x, y + 1, z, BTWGBlocks.tallDryGrass.blockID, ((TallPlantBlock) BTWGBlocks.tallDryGrass).setTopBlock(0, true));
                }

                return;
            }

            if (world.isAirBlock(x, y, z) && Block.blocksList[blockID].canBlockStay(world, x, y, z)) {
                world.setBlockAndMetadata(x, y, z, blockID, meta);
            }
        }
    };

    public static final PlantDistributor TROPICAL_GRASS = new PlantDistributor(10) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(3);

            int blockID;
            int meta;

            if (r == 0) {
                blockID = BTWGBlocks.bush.blockID;
                meta = 0;
            }
            else if (r == 1) {
                blockID = Block.tallGrass.blockID;
                meta = 1;
            }
            else {
                blockID = Block.tallGrass.blockID;
                meta = 2;
            }

            if (world.isAirBlock(x, y, z) && Block.blocksList[blockID].canBlockStay(world, x, y, z)) {
                world.setBlockAndMetadata(x, y, z, blockID, meta);
            }
        }
    };
}
