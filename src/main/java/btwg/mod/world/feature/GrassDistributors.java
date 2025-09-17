package btwg.mod.world.feature;

import btwg.api.world.feature.PlantDistributor;
import btwg.mod.block.BTWGBlockIDs;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public abstract class GrassDistributors {
    public static final PlantDistributor DRY_GRASS = new PlantDistributor(2, 32, 8) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(7);

            int blockID;
            int meta;

            if (r == 0) {
                blockID = Block.deadBush.blockID;
                meta = 0;
            }
            else if (r <= 2) {
                blockID = BTWGBlocks.tallDryGrass.blockID;
                meta = 0;
            }
            else {
                blockID = BTWGBlocks.shortDryGrass.blockID;
                meta = 0;
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
