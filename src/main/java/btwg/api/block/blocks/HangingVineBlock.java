package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class HangingVineBlock extends BlockFlower {
    private boolean isReplaceable;

    public HangingVineBlock(int id) {
        super(id, Material.plants);
        this.initBlockBounds(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
        this.setStepSound(BTWBlocks.plantsStepSound);
        this.setHardness(0.0f);
        this.setBuoyant();
        this.setFireProperties(Flammability.GRASS);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        int blockIDAbove = world.getBlockId(x, y + 1, z);
        Block blockAbove = Block.blocksList[blockIDAbove];

        return blockIDAbove == this.blockID
                || (blockAbove != null && blockAbove.hasCenterHardPointToFacing(world, x, y + 1, z, EnumFacing.DOWN.ordinal()));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z);
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

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int iFacing, float fXClick, float fYClick, float fZClick) {
        return false;
    }

    //------ Class Specific Functionality ------//

    public HangingVineBlock setReplaceable(boolean isReplaceable) {
        this.isReplaceable = isReplaceable;
        return this;
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    protected Icon topIcon;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.topIcon = iconRegister.registerIcon(this.textureName);
        this.blockIcon = iconRegister.registerIcon(this.textureName + "_end");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (y > 0 && blockAccess.getBlockId(x, y - 1, z) == this.blockID) {
            return this.topIcon;
        }
        else {
            return this.blockIcon;
        }
    }
}
