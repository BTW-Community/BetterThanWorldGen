package btwg.mod.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import net.minecraft.src.*;

public class BTWGWoodSidingAndCornerBlock extends SidingAndCornerAndDecorativeBlock {
    public BTWGWoodSidingAndCornerBlock(int id, String texture, String name) {
        super(id, BTWBlocks.plankMaterial, texture, 2.0f, 5.0f, Block.soundWoodFootstep, name);
        this.setAxesEffectiveOn(true);
        this.setBuoyancy(1.0f);
        this.setFireProperties(5, 20);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int getHarvestToolLevel(IBlockAccess blockAccess, int i, int j, int k) {
        return 2;
    }

    @Override
    public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int i, int j, int k, int metadata) {
        world.playAuxSFX(BTWEffectManager.WOOD_BLOCK_DESTROYED_EFFECT_ID, i, j, k, 0);
        int numDropped = this.getNumSawDustDroppedForType(metadata);
        this.dropItemsIndividually(world, i, j, k, BTWItems.sawDust.itemID, numDropped, 0, 1.0f);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion) {
        float chance = 1.0f;
        if (explosion != null) {
            chance = 1.0f / explosion.explosionSize;
        }
        int numDropped = this.getNumSawDustDroppedForType(world.getBlockMetadata(i, j, k));
        this.dropItemsIndividually(world, i, j, k, BTWItems.sawDust.itemID, numDropped, 0, chance);
    }

    //------ Class Specific Functionality ------//

    public int getNumSawDustDroppedForType(int metadata) {
        if (SidingAndCornerAndDecorativeBlock.isDecorativeFromMetadata(metadata) || !this.getIsCorner(metadata)) {
            return 2;
        }
        return 1;
    }
}
