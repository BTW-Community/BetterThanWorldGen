package btwg.mod.world.feature;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.World;

import java.util.Random;

public abstract class TreeDistributors {
    public static final TreeDistributor RAINFOREST_TREES = new TreeDistributor(10) {
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
}
