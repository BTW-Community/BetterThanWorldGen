package btwg.api.block.blocks;

import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.Random;

public class RegolithBlock extends BlockDirt {
    protected final StoneType type;

    public RegolithBlock(int id, StoneType type) {
        super(id);
        this.type = type;

        this.setTextureName(BetterThanWorldGen.MODID + ":" + type.name()  + "/regolith");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + type.name()  + "_regolith");
    }

    @Override
    public int idDropped(int metadata, Random rand, int fortuneModifier) {
        return this.type.looseDirtID();
    }

    @Override
    public int damageDropped(int metadata) {
        return this.type.looseDirtMetadata();
    }

    @Override
    protected void onNeighborDirtDugWithImproperTool(World world, int i, int j, int k, int iToFacing) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.type.looseDirtID(), this.type.looseDirtMetadata());
    }

    @Override
    public boolean getCanGrassSpreadToBlock(World world, int i, int j, int k) {
        return super.getCanGrassSpreadToBlock(world, i, j, k);
    }

    @Override
    public boolean spreadGrassToBlock(World world, int x, int y, int z) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.grassID(), this.type.grassMetadata());
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
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int fromSide) {
        world.setBlockAndMetadataWithNotify(x, y, z, this.type.looseDirtID(), this.type.looseDirtMetadata());

        if (!world.isRemote) {
            world.playAuxSFX(2291, x, y, z, 0);
        }

        return true;
    }
}
