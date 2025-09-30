package btwg.api.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import net.minecraft.src.*;

public class BTWGWoodMouldingBlock extends MouldingAndDecorativeBlock {
    public BTWGWoodMouldingBlock(int id, String textureName, String columnSideTextureName, int matchingCornerBlockID, String name) {
        super(id, BTWBlocks.plankMaterial, textureName, columnSideTextureName, matchingCornerBlockID, 2.0f, 5.0f, Block.soundWoodFootstep, name);
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
        if (this.isDecorative(metadata)) {
            return 2;
        }
        return 1;
    }
}
