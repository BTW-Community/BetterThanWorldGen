package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.item.util.ItemUtils;
import btwg.api.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class WorkStumpBlock extends StumpBlock {
    public WorkStumpBlock(int id, WoodType[] woodTypes) {
        super(id, woodTypes);

        this.hideFromEMI();
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int oldMetadata = world.getBlockMetadata(x, y, z);
        WoodType woodType = woodTypes[oldMetadata];
        int chewedLogID = woodType.chewedLogID();

        if (this.isUnfinishedWorkStump(oldMetadata) && ((BlockLog) Block.wood).isWorkStumpItemConversionTool(stack, world, x, y, z)) {
            world.playAuxSFX(2268, x, y, z, 0);
            world.setBlockMetadataWithNotify(x, y, z, oldMetadata & 3);
            return true;
        }

        int newMetadata = BTWBlocks.oakChewedLog.setIsStump(0);

        world.setBlockAndMetadataWithNotify(x, y, z, chewedLogID, newMetadata);

        if (!world.isRemote) {
            ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(woodType.barkID(), 1, woodType.barkMetadata()), side);
        }

        return true;
    }

    public boolean isUnfinishedWorkStump(int metadata) {
        return (metadata & 8) != 0;
    }

    //------ Client Functionality ------//

    @Environment(value=EnvType.CLIENT)
    private Icon[] sideIcons;
    @Environment(value=EnvType.CLIENT)
    private Icon[] topIcons;
    @Environment(value=EnvType.CLIENT)
    private Icon[] craftingIcons;

    @Override
    @Environment(value= EnvType.CLIENT)
    public Icon getIcon(int iSide, int iMetadata) {
        if (iSide > 1) {
            return this.sideIcons[iMetadata];
        }
        if (iSide == 1) {
            if (!this.isUnfinishedWorkStump(iMetadata)) {
                return this.topIcons[iMetadata];
            }
            return this.craftingIcons[iMetadata];
        }
        return this.topIcons[iMetadata];
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.sideIcons = new Icon[this.woodTypes.length];
        this.topIcons = new Icon[this.woodTypes.length];
        this.craftingIcons = new Icon[this.woodTypes.length];
        for (int i = 0; i < this.sideIcons.length; i++) {
            this.sideIcons[i] = register.registerIcon("btwg:wood/" + this.woodTypes[i].name() + "_work_stump_side");
            this.topIcons[i] = register.registerIcon("btwg:wood/" + this.woodTypes[i].name() + "_stump_top");
            this.craftingIcons[i] = register.registerIcon("btwg:wood/" + this.woodTypes[i].name() + "_work_stump_top");
        }
        this.blockIcon = this.craftingIcons[0];
    }
}
