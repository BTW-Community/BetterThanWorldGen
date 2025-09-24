package btwg.mod.world.feature.features.trees;

import btw.world.feature.trees.grower.BigTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class DeadTreeGrower extends BigTreeGrower {
    public DeadTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        if(super.growTree(world, rand, x, y, z, isWorldGen)) {
            world.setBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2], Block.dirt.blockID);
            return true;
        }

        return false;
    }

    @Override
    protected boolean canTreeGrowHere() {
        int[] var1 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] var2 = new int[]{this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        int var3 = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        if (var3 != Block.sand.blockID) {
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
