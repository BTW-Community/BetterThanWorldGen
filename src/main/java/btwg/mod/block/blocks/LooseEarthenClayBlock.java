package btwg.mod.block.blocks;

import btw.block.blocks.LooseDirtBlock;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class LooseEarthenClayBlock extends LooseDirtBlock {
    public LooseEarthenClayBlock(int id) {
        super(id);
        this.setHoesEffectiveOn(false);
        this.setTextureName("btwg:loose_earthen_clay");
        this.setUnlocalizedName("btwg.loose_earthen_clay");
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
    public void onVegetationAboveGrazed(World world, int i, int j, int k, EntityAnimal animal) {}

    @Override
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        return false;
    }
}
