package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.BigTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;

public class BranchingTreeGrower extends BigTreeGrower {
    protected BranchingTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
        this.setScale(1.5, 1, 0.25);
    }

    @Override
    public void generateLeafNode(int x, int y, int z, boolean isWorldGen) {
        int height = y;

        for (int maxHeight = y + this.leafDistanceLimit - 2; height < maxHeight; ++height) {
            float leafSize = this.leafSize(height - y);
            this.genTreeLayer(x, height, z, leafSize, (byte) 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        }
    }

    @Override
    public float leafSize(int height) {
        return height >= 0 && height < this.leafDistanceLimit - 2 ? (height != 0 && height != this.leafDistanceLimit - 3 ? 4.0F : 2.75F) : -1.0F;
    }

    @Override
    public boolean leafNodeNeedsBase(int height) {
        return true;
    }

    @Override
    protected boolean canTreeGrowHere() {
        int[] var1 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] var2 = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};

        int blockIDBelow = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        Block blockBelow = Block.blocksList[blockIDBelow];

        if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2])) {
            return false;
        }

        int var4 = this.checkBlockLine(var1, var2);
        if (var4 == -1) {
            return true;
        }
        if (var4 < 6) {
            return false;
        }
        this.heightLimit = var4;
        return true;
    }
}