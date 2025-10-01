package btwg.mod.world.feature;

import btwg.api.world.feature.PlantDistributor;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public abstract class GrassDistributors {
    public static final PlantDistributor SCRUBLAND_GRASS = new PlantDistributor(2, 32, 8) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(8);

            if (r == 0) {
                this.placeSinglePlant(world, rand, x, y, z, Block.deadBush.blockID, 0);
            }
            else if (r <= 2) {
                this.placeSinglePlant(world, rand, x, y, z, BTWGBlocks.dryGrass.blockID, 0);
            }
            else if (r < 7) {
                this.placeDoublePlant(world, rand, x, y, z, BTWGBlocks.shortDryGrass.blockID, 0);
            }
            else {
                this.placeDoublePlant(world, rand, x, y, z, BTWGBlocks.tallDryGrass.blockID, 0);
            }
        }
    };
    public static final PlantDistributor DESERT_GRASS = new PlantDistributor(3, 32, 8) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(7);

            if (r <= 1) {
                this.placeSinglePlant(world, rand, x, y, z, BTWGBlocks.dryGrass.blockID, 0);
            }
            else if (r < 6) {
                this.placeDoublePlant(world, rand, x, y, z, BTWGBlocks.shortDryGrass.blockID, 0);
            }
            else {
                this.placeDoublePlant(world, rand, x, y, z, BTWGBlocks.tallDryGrass.blockID, 0);
            }
        }
    };

    public static final PlantDistributor TROPICAL_GRASS = new PlantDistributor(10) {
        @Override
        public void setPlant(World world, Random rand, int x, int y, int z) {
            int r = rand.nextInt(3);

            if (r == 0) {
                this.placeSinglePlant(world, rand, x, y, z, BTWGBlocks.bush.blockID, 0);
            }
            else if (r == 1) {
                this.placeSinglePlant(world, rand, x, y, z, Block.tallGrass.blockID, 1);
            }
            else {
                this.placeSinglePlant(world, rand, x, y, z, Block.tallGrass.blockID, 2);
            }
        }
    };
}
