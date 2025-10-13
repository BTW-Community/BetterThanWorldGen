package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.FallingFullBlock;
import btw.world.util.difficulty.DifficultyParam;
import btwg.api.block.StoneType;
import btwg.mod.BetterThanWorldGen;
import net.minecraft.src.*;

import java.util.Random;

public class BTWGGravelBlock extends FallingFullBlock {
    protected final StoneType type;

    public BTWGGravelBlock(int blockID, StoneType type) {
        super(blockID, Material.ground);

        this.type = type;

        this.setHardness(0.6f);
        this.setShovelsEffectiveOn();
        this.setFilterableProperties(8);
        this.setStepSound(BTWBlocks.dirtStepSound);
        this.setTextureName(BetterThanWorldGen.MODID + ":stone/" + type.name()  + "/gravel");
        this.setUnlocalizedName(BetterThanWorldGen.MODID + "." + type.name()  + "_gravel");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean canEndermenPickUpBlock(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public int idDropped(int iMetadata, Random rand, int iFortuneModifier) {
        if (iFortuneModifier > 0 || rand.nextInt(10) != 0) {
            return this.blockID;
        }
        return Item.flint.itemID;
    }

    @Override
    public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int i, int j, int k, int metadata) {
        if (world.rand.nextInt(10) == 0) {
            this.dropItemsIndividually(world, i, j, k, Item.flint.itemID, 1, 0, 1.0f);
        }
        else {
            super.onBlockDestroyedWithImproperTool(world, player, i, j, k, metadata);
        }
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int metadata, float chanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, this.type.gravelPileID(), 6, this.type.gravelPileMetadata(), chanceOfDrop);
        return true;
    }

    @Override
    public boolean canBePistonShoveled(World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean isBreakableBarricade(World world, int i, int j, int k) {
        return world.getDifficultyParameter(DifficultyParam.CanZombiesBreakBlocks.class);
    }
}
