package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btwg.mod.block.WoodType;
import net.minecraft.src.*;

import java.util.Arrays;

public class BTWGPlanksBlock extends MultiTextureBlock {
    private final WoodType[] woodTypes;

    public BTWGPlanksBlock(int id, WoodType[] woodTypes) {
        super(id, BTWBlocks.plankMaterial, "planks",
                Arrays.stream(woodTypes)
                        .map(WoodType::name)
                        .toArray(String[]::new)
        );

        this.woodTypes = woodTypes;

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setAxesEffectiveOn();
        this.setHardness(1.0f);
        this.setResistance(5.0f);
        this.setFireProperties(Flammability.PLANKS);
        this.setBuoyant();
        this.setStepSound(soundWoodFootstep);

        this.setUnlocalizedName("btwg.planks");
    }

    @Override
    public int damageDropped(int iMetadata) {
        return iMetadata;
    }

    @Override
    public int getHarvestToolLevel(IBlockAccess blockAccess, int i, int j, int k) {
        return 2;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, BTWItems.sawDust.itemID, 2, 0, fChanceOfDrop);
        return true;
    }

    @Override
    public int getFurnaceBurnTime(int metadata) {
        return this.woodTypes[MathHelper.clamp_int(metadata, 0, this.woodTypes.length - 1)].burnTime();
    }
}
