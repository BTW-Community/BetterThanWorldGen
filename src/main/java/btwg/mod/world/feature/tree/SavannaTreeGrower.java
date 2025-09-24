package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import btw.world.util.BlockPos;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class SavannaTreeGrower extends AbstractTreeGrower {
    protected SavannaTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
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
        int slantHeight = 2 + rand.nextInt(height / 3);

        int slantFacing = rand.nextInt(4) + 2;

        boolean hasSlant = rand.nextInt(4) > 0;

        // Check trunk placement
        for (int j = 0; j <= height; j++) {
            BlockPos pos = new BlockPos(x, y + j, z);

            if (j >= height - slantHeight && hasSlant) {
                pos.addFacingAsOffset(slantFacing);
            }

            int blockID = world.getBlockId(pos.x, pos.y, pos.z);
            Block block = Block.blocksList[blockID];

            if (block != null && !block.isReplaceableVegetation(world, pos.x, pos.y, pos.z)) {
                return false;
            }
        }

        // Place trunk
        for (int j = 0; j <= height; j++) {
            BlockPos pos = new BlockPos(x, y + j, z);

            if (j >= height - slantHeight && hasSlant) {
                pos.addFacingAsOffset(slantFacing);
            }

            if (j == 0) {
                this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
            }
        }

        // Place leaves
        int leafRadius = 3;
        BlockPos pos = new BlockPos(x, y + height, z);

        if (hasSlant) {
            pos.addFacingAsOffset(slantFacing);
        }

        this.generateSavannaLeaves(world, rand, pos.x, pos.y, pos.z, leafRadius, isWorldGen);

        return true;
    }

    protected void generateSavannaLeaves(World world, Random rand, int x, int y, int z, int leafRadius, boolean isWorldGen) {
        for (int j = 0; j <= 1; j++) {
            for (int i = -leafRadius; i <= leafRadius; i++) {
                for (int k = -leafRadius; k <= leafRadius; k++) {
                    float leafRadiusModifier = j == 0 ? 0.5F : -1;
                    float leafRadiusModified = leafRadius + leafRadiusModifier;

                    if (i * i + k * k <= leafRadiusModified * leafRadiusModified) {
                        BlockPos pos = new BlockPos(x + i, y + j, z + k);

                        int blockID = world.getBlockId(pos.x, pos.y, pos.z);
                        Block block = Block.blocksList[blockID];

                        if (block == null || block.isReplaceableVegetation(world, pos.x, pos.y, pos.z)) {
                            this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
                        }
                    }
                }
            }
        }
    }
}
