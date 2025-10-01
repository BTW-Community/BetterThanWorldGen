package btwg.mod.world.feature;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import btwg.api.world.feature.TreeDistributor;
import btwg.mod.block.BTWGBlocks;
import btwg.mod.world.feature.tree.BTWGTreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public abstract class TreeDistributors {
    public static final TreeDistributor FOREST = new TreeDistributor(10) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t == 0) {
                return BTWGTreeGrowers.OAK_BUSH;
            }
            else if (t == 1) {
                return TreeGrowers.BIG_OAK_TREE;
            }
            else if (t == 2) {
                return TreeGrowers.BIRCH_TREE;
            }
            else if (t <= 5) {
                return BTWGTreeGrowers.TALL_OAK_TREE;
            }
            else {
                return BTWGTreeGrowers.TALL_OAK_TREE;
            }
        }
    };

    public static final TreeDistributor DARK_FOREST = new TreeDistributor(20) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t <= 3) {
                return BTWGTreeGrowers.DARK_OAK_TREE;
            }

            return BTWGTreeGrowers.HUGE_DARK_OAK_TREE;
        }
    };

    public static final TreeDistributor CHERRY = new TreeDistributor(5) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            return BTWGTreeGrowers.CHERRY_TREE;
        }
    };

    public static final TreeDistributor PALE_GARDEN = new TreeDistributor(20) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t <= 3) {
                return BTWGTreeGrowers.PALE_OAK_TREE;
            }

            return BTWGTreeGrowers.HUGE_PALE_OAK_TREE;
        }
    };

    public static final TreeDistributor MEADOW = new TreeDistributor(-10) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            return TreeGrowers.BIRCH_TREE;
        }
    };

    public static final TreeDistributor RAINFOREST = new TreeDistributor(10) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t <= 1) {
                return BTWGTreeGrowers.OAK_BUSH;
            }
            else if (t <= 4) {
                return TreeGrowers.BIG_OAK_TREE;
            }
            else if (t <= 6) {
                return TreeGrowers.JUNGLE_TREE;
            }
            else {
                return BTWGTreeGrowers.TALL_OAK_TREE;
            }
        }
    };

    public static final TreeDistributor SCRUBLAND = new TreeDistributor(5) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            if (rand.nextInt(500) == 0) {
                return BTWGTreeGrowers.HUGE_DEAD_TREE;
            }

            if (rand.nextInt(200) == 0) {
                return BTWGTreeGrowers.DEAD_TREE;
            }

            if (world.getBlockId(x, y - 1, z) == BTWGBlocks.sandyDirt.blockID) {
                return BTWGTreeGrowers.OAK_BUSH;
            }

            if (rand.nextInt(20) == 0) {
                return BTWGTreeGrowers.CACTUS;
            }
            else {
                return BTWGTreeGrowers.EMPTY;
            }
        }
    };

    public static final TreeDistributor HIGHLANDS = new TreeDistributor(10) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t == 0) {
                return TreeGrowers.BIG_OAK_TREE;
            }
            else if (t <= 2) {
                return TreeGrowers.OAK_TREE;
            }
            else if (t <= 4) {
                return BTWGTreeGrowers.SPRUCE_BUSH;
            }
            else {
                return BTWGTreeGrowers.OAK_BUSH;
            }
        }
    };

    public static final TreeDistributor SAVANNA = new TreeDistributor(1) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t == 0) {
                return BTWGTreeGrowers.SPLIT_SAVANNA_TREE;
            }
            else if (t <= 2) {
                return BTWGTreeGrowers.SAVANNA_TREE;
            }
            else {
                return BTWGTreeGrowers.SAVANNA_BUSH;
            }
        }
    };

    public static final TreeDistributor WINDSWEPT_HILLS = new TreeDistributor(-10) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t < 5) {
                return TreeGrowers.SPRUCE_TREE;
            }
            else {
                return TreeGrowers.PINE_TREE;
            }
        }
    };

    public static final TreeDistributor TAIGA = new TreeDistributor(20) {
        @Override
        public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
            int t = rand.nextInt(10);

            if (t < 5) {
                return TreeGrowers.SPRUCE_TREE;
            }
            else {
                return TreeGrowers.PINE_TREE;
            }
        }
    };
}
