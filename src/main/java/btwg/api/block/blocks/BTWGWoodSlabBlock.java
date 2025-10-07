package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.SlabBlock;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btwg.api.block.WoodType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

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
        int variant = this.setIsUpsideDown(metadata, false) >> 1;
        return this.woodTypes[MathHelper.clamp_int(variant, 0, this.woodTypes.length - 1)].plankID();
    }

    @Override
    public int getCombinedMetadata(int metadata) {
        int variant = this.setIsUpsideDown(metadata, false) >> 1;
        return this.woodTypes[MathHelper.clamp_int(variant, 0, this.woodTypes.length - 1)].plankMetadata();
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
        int variant = this.setIsUpsideDown(metadata, false) >> 1;
        return Block.blocksList[this.woodTypes[MathHelper.clamp_int(variant, 0, this.woodTypes.length - 1)].plankID()]
                .getIcon(side, this.woodTypes[MathHelper.clamp_int(variant, 0, this.woodTypes.length - 1)].plankMetadata());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {}

    @Override
    public void getSubBlocks(int iBlockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.woodTypes.length; i++) {
            list.add(new ItemStack(iBlockID, 1, i * 2));
        }
    }
}
