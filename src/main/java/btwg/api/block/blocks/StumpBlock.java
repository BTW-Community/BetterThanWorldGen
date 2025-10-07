package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.util.ItemUtils;
import btwg.api.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class StumpBlock extends Block {
    protected final WoodType[] woodTypes;

    public StumpBlock(int id, WoodType[] woodTypes) {
        super(id, BTWBlocks.logMaterial);

        this.woodTypes = woodTypes;

        this.setHardness(3.75f);
        this.setResistance(3.33f);
        this.setAxesEffectiveOn();
        this.setChiselsEffectiveOn();
        this.setBuoyant();
        this.setFireProperties(Flammability.LOGS);
        this.setStepSound(soundWoodFootstep);
    }

    @Override
    public boolean canEndermenPickUpBlock(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public int idDropped(int metadata, Random par2Random, int par3) {
        return this.woodTypes[metadata].logID();
    }

    @Override
    public boolean getIsProblemToRemove(ItemStack toolStack, IBlockAccess blockAccess, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean getDoesStumpRemoverWorkOnBlock(IBlockAccess blockAccess, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int oldMetadata = world.getBlockMetadata(x, y, z);
        int chewedLogID = BlockLog.chewedLogArray[oldMetadata & 3].blockID;

        if (((BlockLog) Block.wood).isWorkStumpItemConversionTool(stack, world, x, y, z)) {
            if (!world.isRemote) {
                world.playAuxSFX(2268, x, y, z, 0);
            }

            world.setBlockAndMetadataWithNotify(x, y, z, this.woodTypes[oldMetadata].stumpID(), this.woodTypes[oldMetadata].stumpMetadata());
            return true;
        }

        if (!world.isRemote) {
            world.playAuxSFX(2290, x, y, z, 0);
        }

        int newMetadata = BTWBlocks.oakChewedLog.setIsStump(oldMetadata & 0xC);
        world.setBlockAndMetadataWithNotify(x, y, z, chewedLogID, newMetadata);

        if (!world.isRemote) {
            ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(this.woodTypes[oldMetadata].barkID(), 1, this.woodTypes[oldMetadata].barkMetadata()), side);
        }

        return true;
    }

    @Override
    public boolean isLog(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    public boolean canSupportLeaves(IBlockAccess blockAccess, int x, int y, int z) {
        return false;
    }

    //------ Client Functionality ------//

    @Environment(value= EnvType.CLIENT)
    private Icon[] sideIcons;
    @Environment(value=EnvType.CLIENT)
    private Icon[] topIcons;

    @Override
    @Environment(value= EnvType.CLIENT)
    public Icon getIcon(int iSide, int iMetadata) {
        if (iSide > 1) {
            return this.sideIcons[iMetadata];
        }
        if (iSide == 1) {
            return this.topIcons[iMetadata];
        }
        return this.topIcons[iMetadata];
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.sideIcons = new Icon[this.woodTypes.length];
        this.topIcons = new Icon[this.woodTypes.length];
        for (int i = 0; i < this.sideIcons.length; i++) {
            this.sideIcons[i] = register.registerIcon("btwg:wood/" + this.woodTypes[i].name() + "_stump_side");
            this.topIcons[i] = register.registerIcon("btwg:wood/" + this.woodTypes[i].name() + "_stump_top");
        }
        this.blockIcon = this.sideIcons[0];
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.woodTypes.length; i++) {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
