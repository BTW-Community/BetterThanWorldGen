package btwg.mod.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import net.minecraft.src.*;

import java.util.Random;

public class ShrubBlock extends BlockFlower {
    private boolean isReplaceable;
    private boolean canStayOnSand;

    public ShrubBlock(int id) {
        super(id, Material.vine);
        this.initBlockBounds(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
        this.setStepSound(BTWBlocks.plantsStepSound);
        this.setHardness(0.0f);
        this.setBuoyant();
        this.setFireProperties(Flammability.GRASS);
    }

    protected boolean canGrowOnBlock(World world, int x, int y, int z) {
        Block blockOn = Block.blocksList[world.getBlockId(x, y, z)];
        return blockOn != null && (blockOn.canWildVegetationGrowOnBlock(world, x, y, z) || (this.canStayOnSand && blockOn.blockMaterial == Material.sand && blockOn.hasLargeCenterHardPointToFacing(world, x, y, z, EnumFacing.UP.ordinal())));
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canGrowOnBlock(world, x, y - 1, z);
    }

    @Override
    public int idDropped(int iMetadata, Random rand, int iFortuneModifier) {
        return -1;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int par6) {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemShears) {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this, 1, par6));
        } else {
            super.harvestBlock(world, player, x, y, z, par6);
        }
    }

    @Override
    public boolean canSpitWebReplaceBlock(World world, int x, int y, int z) {
        return this.isReplaceable;
    }

    @Override
    public boolean isReplaceableVegetation(World world, int x, int y, int z) {
        return this.isReplaceable;
    }

    @Override
    public ItemStack getStackRetrievedByBlockDispenser(World world, int i, int j, int k) {
        return this.createStackedBlock(world.getBlockMetadata(i, j, k));
    }

    public ShrubBlock setReplaceable() {
        this.isReplaceable = true;
        return this;
    }

    public ShrubBlock setCanStayOnSand() {
        this.canStayOnSand = true;
        return this;
    }
}
