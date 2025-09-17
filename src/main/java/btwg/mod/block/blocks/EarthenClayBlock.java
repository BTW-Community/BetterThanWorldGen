package btwg.mod.block.blocks;

import btwg.mod.block.BTWGBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class EarthenClayBlock extends BlockDirt {
    private final boolean isGrassy;

    public EarthenClayBlock(int blockID, boolean isGrassy) {
        super(blockID);
        this.isGrassy = isGrassy;
        this.setHoesEffectiveOn(false);

        if (this.isGrassy) {
            this.setTextureName("btwg:grassy_earthen_clay");
            this.setUnlocalizedName("btwg.grassy_earthen_clay");
            this.setTickRandomly(true);
        }
        else {
            this.setTextureName("btwg:earthen_clay");
            this.setUnlocalizedName("btwg.earthen_clay");
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!BlockGrass.canGrassSurviveAtLocation(world, x, y, z)) {
            world.setBlockWithNotify(x, y, z, BTWGBlocks.earthenClay.blockID);
        }
        else if (BlockGrass.canGrassSpreadFromLocation(world, x, y, z)) {
            if (rand.nextFloat() <= 0.8f) {
                BlockGrass.checkForGrassSpreadFromLocation(world, x, y, z);
            }
        }
    }

    @Override
    public int idDropped(int iMetadata, Random rand, int iFortuneModifier) {
        return BTWGBlocks.looseEarthenClay.blockID;
    }

    @Override
    protected void onNeighborDirtDugWithImproperTool(World world, int i, int j, int k, int iToFacing) {}

    @Override
    public boolean getCanGrassSpreadToBlock(World world, int i, int j, int k) {
        return super.getCanGrassSpreadToBlock(world, i, j, k) && !this.isGrassy;
    }

    @Override
    public boolean spreadGrassToBlock(World world, int x, int y, int z) {
        world.setBlockWithNotify(x, y, z, BTWGBlocks.grassyEarthenClay.blockID);
        return true;
    }

    @Override
    public boolean getCanMyceliumSpreadToBlock(World world, int i, int j, int k) {
        return false;
    }

    @Override
    public boolean getCanBlightSpreadToBlock(World world, int i, int j, int k, int iBlightLevel) {
        return false;
    }

    @Override
    public void onVegetationAboveGrazed(World world, int i, int j, int k, EntityAnimal animal) {}

    @Override
    public boolean canBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
        return animal.isStarving();
    }

    @Override
    public void onGrazed(World world, int x, int y, int z, EntityAnimal animal) {
        world.setBlockWithNotify(x, y, z, BTWGBlocks.earthenClay.blockID);
        this.notifyNeighborsBlockDisrupted(world, x, y, z);
    }

    @Override
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        return false;
    }

    //------ Client Functionality ------//

    private Icon topIcon;
    private Icon bottomIcon;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        super.registerIcons(register);
        this.topIcon = register.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = register.registerIcon(this.getTextureName() + "_bottom");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata) {
        if (side == 1) {
            return this.topIcon;
        }
        else if (side == 0) {
            return this.bottomIcon;
        }
        else {
            return this.blockIcon;
        }
    }
}
