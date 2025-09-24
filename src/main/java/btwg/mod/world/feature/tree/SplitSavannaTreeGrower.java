package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.TreeGrowers;
import btw.world.util.BlockPos;
import net.minecraft.src.Block;
import net.minecraft.src.Facing;
import net.minecraft.src.World;

import java.util.Random;

public class SplitSavannaTreeGrower extends SavannaTreeGrower {
    protected SplitSavannaTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        int blockIDBelow = world.getBlockId(x, y - 1, z);
        Block blockBelow = Block.blocksList[blockIDBelow];

        if (blockBelow == null || !blockBelow.canWildVegetationGrowOnBlock(world, x, y - 1, z)) {
            return false;
        }

        int height = this.minTreeHeight + rand.nextInt(this.maxTreeHeight - this.minTreeHeight);
        int splitHeight = 3 + rand.nextInt(height / 4);

        int splitFacing = rand.nextInt(4) + 2;

        // Check trunk placement
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos pos2 = new BlockPos(x, y, z);

        for (int j = 0; j <= height; j++) {
            if (j == splitHeight) {
                pos2.addFacingAsOffset(Facing.oppositeSide[splitFacing]);
            }

            if (j > splitHeight) {
                pos.addFacingAsOffset(splitFacing);
                pos2.addFacingAsOffset(Facing.oppositeSide[splitFacing]);

                int blockID = world.getBlockId(pos2.x, pos2.y, pos2.z);
                Block block = Block.blocksList[blockID];

                if (block != null && !block.isReplaceableVegetation(world, pos2.x, pos2.y, pos2.z)) {
                    return false;
                }
            }

            int blockID = world.getBlockId(pos.x, pos.y, pos.z);
            Block block = Block.blocksList[blockID];

            if (block != null && !block.isReplaceableVegetation(world, pos.x, pos.y, pos.z)) {
                return false;
            }

            pos.addFacingAsOffset(1);
            pos2.addFacingAsOffset(1);
        }

        // Place trunk
        pos = new BlockPos(x, y, z);
        pos2 = new BlockPos(x, y, z);

        for (int j = 0; j <= height; j++) {
            if (j == 0) {
                this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
            }

            if (j == height - splitHeight) {
                pos2.addFacingAsOffset(Facing.oppositeSide[splitFacing]);
            }

            if (j > height - splitHeight) {
                if (j < height) {
                    this.setBlockAndMetadata(world, pos2.x, pos2.y, pos2.z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
                    pos2.addFacingAsOffset(Facing.oppositeSide[splitFacing]);
                }
                pos.addFacingAsOffset(splitFacing);
            }

            pos.addFacingAsOffset(1);

            if (j < height) {
                pos2.addFacingAsOffset(1);
            }
        }

        // Place leaves
        int leafRadius = 3;

        this.generateSavannaLeaves(world, rand, pos.x, pos.y, pos.z, leafRadius, isWorldGen);
        this.generateSavannaLeaves(world, rand, pos2.x, pos2.y, pos2.z, leafRadius, isWorldGen);

        return true;
    }
}
