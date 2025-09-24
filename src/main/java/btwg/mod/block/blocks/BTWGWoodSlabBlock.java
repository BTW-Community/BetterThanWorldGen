package btwg.mod.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.SlabBlock;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btwg.mod.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class BTWGWoodSlabBlock extends SlabBlock {
    private final WoodType[] woodTypes;

    public BTWGWoodSlabBlock(int id, WoodType[] woodTypes) {
        super(id, BTWBlocks.plankMaterial);

        this.woodTypes = woodTypes;

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setAxesEffectiveOn();
        this.setHardness(1.0f);
        this.setResistance(5.0f);
        this.setFireProperties(Flammability.PLANKS);
        this.setBuoyant();
        this.setStepSound(soundWoodFootstep);

        this.setUnlocalizedName("btwg.wood_slab");
    }

    @Override
    public int getCombinedBlockID(int metadata) {
        metadata = this.setIsUpsideDown(MathHelper.clamp_int(metadata, 0, this.woodTypes.length - 1), false);
        return this.woodTypes[metadata].plankID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        metadata = this.setIsUpsideDown(MathHelper.clamp_int(metadata, 0, this.woodTypes.length - 1), false);
        return this.woodTypes[metadata].plankMetadata();
    }

    @Override
    public int getHarvestToolLevel(IBlockAccess blockAccess, int i, int j, int k) {
        return 2;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float dropChance) {
        this.dropItemsIndividually(world, i, j, k, BTWItems.sawDust.itemID, 1, 0, dropChance);
        return true;
    }

    @Override
    public int getFurnaceBurnTime(int metadata) {
        return this.woodTypes[MathHelper.clamp_int(metadata, 0, this.woodTypes.length - 1)].burnTime() / 2;
    }

    //------ Client Side Functionality ------//

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        metadata = this.setIsUpsideDown(MathHelper.clamp_int(metadata, 0, this.woodTypes.length - 1), false);
        return Block.blocksList[this.woodTypes[metadata].plankID()].getIcon(side, this.woodTypes[metadata].plankMetadata());
    }
}
