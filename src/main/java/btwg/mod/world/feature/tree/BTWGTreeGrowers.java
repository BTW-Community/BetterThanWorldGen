package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.*;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public abstract class BTWGTreeGrowers {
    public static final AbstractTreeGrower EMPTY = new AbstractTreeGrower("btwg:empty", 0, 0, null) {
        @Override
        public boolean growTree(World world, Random rand, int x, int y, int z, boolean notify) {
            return true;
        }
    };

    //------ Oak ------//

    public static final AbstractTreeGrower TINY_OAK_SHRUB = new TinyShrubGrower("btwg:tiny_oak_shrub", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower OAK_SHRUB = new SmallShrubGrower("btwg:oak_shrub", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower OAK_BUSH = new BushGrower("btwg:oak_bush", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower TALL_OAK_TREE = new StandardTreeGrower("btwg:tall_oak", 5, 8, TreeGrowers.OAK_WOOD_TYPE);

    public static final AbstractTreeGrower DEAD_TREE = new DeadTreeGrower( "btwg:dead_tree", 12, 16, BTWGTreeWoodTypes.DEAD);
    public static final AbstractTreeGrower HUGE_DEAD_TREE = new DeadTreeGrower( "btwg:huge_dead_tree", 20, 24, BTWGTreeWoodTypes.DEAD);

    //------ SPRUCE ------//

    public static final AbstractTreeGrower TINY_SPRUCE_SHRUB = new TinyShrubGrower("btwg:tiny_spruce_shrub", TreeGrowers.SPRUCE_WOOD_TYPE);
    public static final AbstractTreeGrower SPRUCE_SHRUB = new SmallShrubGrower("btwg:spruce_shrub", TreeGrowers.SPRUCE_WOOD_TYPE);
    public static final AbstractTreeGrower SPRUCE_BUSH = new BushGrower("btwg:spruce_bush", TreeGrowers.SPRUCE_WOOD_TYPE);

    public static final AbstractTreeGrower HUGE_SPRUCE_TREE = new HugeTaigaTreeGrower("btwg:huge_spruce", 15, 30, TreeGrowers.SPRUCE_WOOD_TYPE);

    //------ BIRCH ------//

    public static final AbstractTreeGrower TALL_BIRCH_TREE = new StandardTreeGrower("btwg:tall_birch", 7, 10, TreeGrowers.BIRCH_WOOD_TYPE);
    public static final AbstractTreeGrower LARGE_BIRCH_TREE = new BranchingTreeGrower("btwg:large_birch", 10, 16, TreeGrowers.BIRCH_WOOD_TYPE);

    //------ ACACIA------//

    public static final AbstractTreeGrower SAVANNA_TREE = new SavannaTreeGrower("btwg:savanna", 4, 8, BTWGTreeWoodTypes.ACACIA);
    public static final AbstractTreeGrower SPLIT_SAVANNA_TREE = new SplitSavannaTreeGrower("btwg:savanna", 5, 9, BTWGTreeWoodTypes.ACACIA);
    public static final AbstractTreeGrower SAVANNA_BUSH = new BushGrower("btwg:savanna_bush", BTWGTreeWoodTypes.ACACIA);

    //------ DARK OAK ------//

    public static final AbstractTreeGrower DARK_OAK_TREE = new BranchingTreeGrower("btwg:dark_oak", 9, 16, BTWGTreeWoodTypes.DARK_OAK);
    public static final AbstractTreeGrower HUGE_DARK_OAK_TREE = new DarkOakTreeGrower("deco:huge_dark_oak", 4, 10, BTWGTreeWoodTypes.DARK_OAK);

    //------ MANGROVE ------//

    //------ CHERRY ------//

    public static final AbstractTreeGrower CHERRY_TREE = new BranchingTreeGrower("btwg:cherry", 10, 16, BTWGTreeWoodTypes.CHERRY);

    //------ PALE OAK ------//

    public static final AbstractTreeGrower PALE_OAK_TREE = new BranchingTreeGrower("btwg:pale_oak", 9, 16, BTWGTreeWoodTypes.PALE_OAK);
    public static final AbstractTreeGrower HUGE_PALE_OAK_TREE = new DarkOakTreeGrower("deco:huge_pale_oak", 4, 10, BTWGTreeWoodTypes.PALE_OAK);

    //------ Other ------//

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
}
