package btwg.mod.world.feature;

import btw.world.feature.trees.grower.*;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public abstract class BTWGTreeGrowers {
    public static final AbstractTreeGrower OAK_BUSH = new BushGrower("btw:jungle_bush", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower TALL_OAK_TREE = new StandardTreeGrower("btw:oak", 5, 8, TreeGrowers.OAK_WOOD_TYPE);

    public static final TreeGrowers.TreeWoodType CACTUS_WOOD_TYPE = new TreeGrowers.TreeWoodType(Block.cactus.blockID, 0, Block.cactus.blockID, 0, 0, 0);
    public static final AbstractTreeGrower CACTUS = new AbstractTreeGrower("btwg:cactus", 1, 3, CACTUS_WOOD_TYPE) {
        @Override
        public boolean growTree(World world, Random rand, int x, int y, int z, boolean notify) {
            if (world.getBlockId(x, y - 1, z) != Block.sand.blockID) {
                return false;
            }

            for (int j = y; j < y + this.minTreeHeight + rand.nextInt(this.maxTreeHeight) && j < 256; j++) {
                if (!world.isAirBlock(x, j, z)) {
                    return false;
                }
            }

            for (int j = y; j <= y + this.minTreeHeight + rand.nextInt(this.maxTreeHeight) && j < 256; j++) {
                world.setBlockAndMetadata(x, j, z, this.woodType.woodBlockID, this.woodType.woodMetadata);
            }

            return true;
        }
    };

    public static final TreeGrowers.TreeWoodType DEAD_WOOD_TYPE = new TreeGrowers.TreeWoodType(Block.wood.blockID, 0, Block.wood.blockID, 12, 0, 0);
    public static final AbstractTreeGrower DEAD_TREE = new DeadTreeGrower( "btwg:dead_tree", 12, 16, DEAD_WOOD_TYPE);
    public static final AbstractTreeGrower HUGE_DEAD_TREE = new DeadTreeGrower( "btwg:huge_dead_tree", 20, 24, DEAD_WOOD_TYPE);
}
