package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LooseDirtBlock;
import btw.item.BTWItems;
import btw.item.items.HoeItem;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class LooseRegolithBlock extends LooseDirtBlock {
    protected final StoneType type;

    public LooseRegolithBlock(int id, StoneType type) {
        super(id);

        this.type = type;

        this.setUnlocalizedName(BetterThanWorldGen.MODID + ".loose_" + type.name() + "_regolith");
        this.setTextureName(BetterThanWorldGen.MODID + ":" + type.name() + "/loose_regolith");
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.dirtPileID(), 6, this.type.dirtPileMetadata(), fChanceOfDrop);
        return true;
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
    public boolean canConvertBlock(ItemStack stack, World world, int i, int j, int k) {
        // TODO: fix
        return false;
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int iFromSide) {
        // TODO: fix
        HoeItem hoe;
        int irrigationLevel = 0;
        Item item = stack.getItem();
        //if (item instanceof HoeItem && (hoe = (HoeItem)item).canHoeHydrateTilledSoil() && this.doesBlockConvertToHydratedFarmland(world, x, y, z)) {
        //    irrigationLevel = BTWBlocks.farmland.setFullyHydrated(irrigationLevel);
        //}
        world.setBlockAndMetadataWithNotify(x, y, z, BTWBlocks.farmland.blockID, irrigationLevel);
        if (!world.isRemote) {
            world.playAuxSFX(2291, x, y, z, 0);
        }
        return true;
    }
}
