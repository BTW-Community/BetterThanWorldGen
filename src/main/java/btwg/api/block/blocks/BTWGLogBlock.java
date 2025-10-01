package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import btwg.api.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class BTWGLogBlock extends BlockRotatedPillar {
    private final WoodType woodType;

    public BTWGLogBlock(int id, WoodType woodType) {
        super(id, BTWBlocks.logMaterial);

        this.woodType = woodType;

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.25f);
        this.setResistance(3.33f);
        this.setAxesEffectiveOn();
        this.setChiselsEffectiveOn();
        this.setBuoyant();
        this.setFireProperties(Flammability.LOGS);
        this.setStepSound(soundWoodFootstep);

        this.setUnlocalizedName("btwg." + this.woodType.name() + "_log");
        this.setTextureName("btwg:" + this.woodType.name() + "_log");
    }

    @Override
    public boolean canEndermenPickUpBlock(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int oldMetadata = world.getBlockMetadata(x, y, z);

        int orientation = oldMetadata >> 2 & 3;
        int newMetadata = BTWBlocks.oakChewedLog.setOrientation(oldMetadata & 0xC, orientation);

        if (!world.isRemote) {
            world.playAuxSFX(2290, x, y, z, 0);
        }

        world.setBlockAndMetadataWithNotify(x, y, z, this.woodType.chewedLogID(), newMetadata);

        if (!world.isRemote) {
            ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.woodType.barkID(), 1, this.woodType.barkMetadata()), side);
        }

        return true;
    }

    @Override
    public boolean shouldPlayStandardConvertSound(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
        int leafCheckRange = 4;
        int chunkCheckRange = leafCheckRange + 1;
        if (world.checkChunksExist(x - chunkCheckRange, y - chunkCheckRange, z - chunkCheckRange, x + chunkCheckRange, y + chunkCheckRange, z + chunkCheckRange)) {
            for (int i = -leafCheckRange; i <= leafCheckRange; ++i) {
                for (int j = -leafCheckRange; j <= leafCheckRange; ++j) {
                    for (int k = -leafCheckRange; k <= leafCheckRange; ++k) {
                        int oldMetadata;
                        int offsetBlockID = world.getBlockId(x + i, y + j, z + k);
                        Block offsetBlock = Block.blocksList[offsetBlockID];
                        if (offsetBlock == null || !offsetBlock.isLeafBlock(world, x, y, z) || ((oldMetadata = world.getBlockMetadata(x + i, y + j, z + k)) & 8) != 0) continue;
                        world.setBlockMetadataWithNotify(x + i, y + j, z + k, oldMetadata | 8, 4);
                    }
                }
            }
        }
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, BTWItems.sawDust.itemID, 6, 0, fChanceOfDrop);
        this.dropItemsIndividually(world, i, j, k, this.woodType.barkID(), 1, this.woodType.barkMetadata(), fChanceOfDrop);
        return true;
    }

    @Override
    public void onDestroyedByFire(World world, int i, int j, int k, int iFireAge, boolean bForcedFireSpread) {
        this.convertToSmouldering(world, i, j, k);
    }

    @Override
    public int rotateMetadataAroundYAxis(int iMetadata, boolean bReverse) {
        int iAxisAlignment = iMetadata & 0xC;
        if (iAxisAlignment != 0) {
            if (iAxisAlignment == 4) {
                iAxisAlignment = 8;
            } else if (iAxisAlignment == 8) {
                iAxisAlignment = 4;
            }
            iMetadata = iMetadata & 0xFFFFFFF3 | iAxisAlignment;
        }
        return iMetadata;
    }

    @Override
    public int getFurnaceBurnTime(int metadata) {
        return this.woodType.burnTime() * 4;
    }

    @Override
    public boolean isLog(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    //------ Class Specific Functionality ------//

    public void convertToSmouldering(World world, int i, int j, int k) {
        world.setBlockWithNotify(i, j, k, BTWBlocks.smolderingLog.blockID);
    }

    //------ Client Side Functionality ------//

    @Environment(EnvType.CLIENT)
    private Icon topIcon;

    @Environment(EnvType.CLIENT)
    @Override
    protected Icon getSideIcon(int metadata) {
        return this.blockIcon;
    }

    @Environment(value=EnvType.CLIENT)
    protected Icon getEndIcon(int metadata) {
        return this.topIcon;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        super.registerIcons(register);
        this.topIcon = register.registerIcon(this.getTextureName() + "_top");
    }
}
