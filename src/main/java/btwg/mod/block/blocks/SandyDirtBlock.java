package btwg.mod.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.FullBlock;
import btw.item.BTWItems;
import btw.world.util.difficulty.DifficultyParam;
import btwg.mod.block.BTWGBlocks;
import net.minecraft.src.*;

import java.util.Random;

public class SandyDirtBlock extends FullBlock {
    public SandyDirtBlock(int id) {
        super(id, Material.ground);
        this.setHardness(0.5f);
        this.setShovelsEffectiveOn();
        this.setStepSound(BTWBlocks.dirtStepSound);
        this.setUnlocalizedName("btwg.sandy_dirt");
        this.setTextureName("btwg:sandy_dirt");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean canEndermenPickUpBlock(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public int idDropped(int iMetadata, Random rand, int iFortuneModifier) {
        return BTWGBlocks.looseSandyDirt.blockID;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, BTWItems.dirtPile.itemID, 3, 0, fChanceOfDrop);
        this.dropItemsIndividually(world, i, j, k, BTWItems.sandPile.itemID, 3, 0, fChanceOfDrop);
        return true;
    }

    @Override
    public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int i, int j, int k, int iMetadata) {
        super.onBlockDestroyedWithImproperTool(world, player, i, j, k, iMetadata);
        this.onDirtDugWithImproperTool(world, i, j, k);
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion) {
        super.onBlockDestroyedByExplosion(world, i, j, k, explosion);
        this.onDirtDugWithImproperTool(world, i, j, k);
    }

    @Override
    public boolean canBePistonShoveled(World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean canReedsGrowOnBlock(World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean canSaplingsGrowOnBlock(World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean canWildVegetationGrowOnBlock(World world, int i, int j, int k) {
        return true;
    }

    @Override
    public boolean isBreakableBarricade(World world, int i, int j, int k) {
        return world.getDifficultyParameter(DifficultyParam.CanZombiesBreakBlocks.class);
    }
}
