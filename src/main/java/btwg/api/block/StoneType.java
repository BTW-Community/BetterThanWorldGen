package btwg.api.block;

import btw.block.BTWBlocks;
import btw.item.BTWItems;
import net.minecraft.src.Block;

public record StoneType(
        int stoneID, int stoneMetadata,
        
        int cobblestoneID, int cobblestoneMetadata,
        int mossyCobblestoneID, int mossyCobblestoneMetadata,
        int looseCobblestoneID, int looseCobblestoneMetadata,
        
        int stoneBrickID, int stoneBrickMetadata,
        int mossyStoneBrickID, int mossyStoneBrickMetadata,
        int crackedStoneBrickID, int crackedStoneBrickMetadata,
        int chiseledStoneBrickID, int chiseledStoneBrickMetadata,
        int looseStoneBrickID, int looseStoneBrickMetadata,
        
        int infestedStoneID,
        int roughStoneID,
        
        int rockItemID, int rockItemMetadata,
        int stoneBrickItemID, int stoneBrickItemMetadata,
        int gravelPileID, int gravelPileMetadata,
        
        int strata
) {
    public static final StoneType VANILLA_STONE = new StoneType(
            Block.stone.blockID, 0,
            Block.cobblestone.blockID, 0,
            Block.cobblestoneMossy.blockID, 0,
            BTWBlocks.looseCobblestone.blockID, 0,
            Block.stoneBrick.blockID, 0,
            Block.stoneBrick.blockID, 1,
            Block.stoneBrick.blockID, 2,
            Block.stoneBrick.blockID, 3,
            BTWBlocks.looseStoneBrick.blockID, 0,
            BTWBlocks.infestedStone.blockID,
            BTWBlocks.upperStrataRoughStone.blockID,
            BTWItems.stone.itemID, 0,
            BTWItems.stoneBrick.itemID, 0,
            BTWItems.gravelPile.itemID, 0,
            0
    );
    
    public static final StoneType DEEPSLATE = new StoneType(
            Block.stone.blockID, 1,
            Block.cobblestone.blockID, 1,
            Block.cobblestoneMossy.blockID, 1,
            BTWBlocks.looseCobblestone.blockID, 4,
            Block.stoneBrick.blockID, 4,
            Block.stoneBrick.blockID, 5,
            Block.stoneBrick.blockID, 6,
            Block.stoneBrick.blockID, 7,
            BTWBlocks.looseStoneBrick.blockID, 4,
            BTWBlocks.infestedMidStrataStone.blockID,
            BTWBlocks.midStrataRoughStone.blockID,
            BTWItems.stone.itemID, 1,
            BTWItems.stoneBrick.itemID, 1,
            BTWItems.gravelPile.itemID, 1,
            1
    );
    
    public static final StoneType BLACK_STONE = new StoneType(
            Block.stone.blockID, 2,
            Block.cobblestone.blockID, 2,
            Block.cobblestoneMossy.blockID, 2,
            BTWBlocks.looseCobblestone.blockID, 8,
            Block.stoneBrick.blockID, 8,
            Block.stoneBrick.blockID, 9,
            Block.stoneBrick.blockID, 10,
            Block.stoneBrick.blockID, 11,
            BTWBlocks.looseStoneBrick.blockID, 8,
            BTWBlocks.infestedDeepStrataStone.blockID,
            BTWBlocks.deepStrataRoughStone.blockID,
            BTWItems.stone.itemID, 2,
            BTWItems.stoneBrick.itemID, 2,
            BTWItems.gravelPile.itemID, 2,
            2
    );
    
    public static final StoneType LIMESTONE = new StoneType(
            Block.stone.blockID, 0,
            Block.cobblestone.blockID, 0,
            Block.cobblestoneMossy.blockID, 0,
            BTWBlocks.looseCobblestone.blockID, 0,
            Block.stoneBrick.blockID, 0,
            Block.stoneBrick.blockID, 1,
            Block.stoneBrick.blockID, 2,
            Block.stoneBrick.blockID, 3,
            BTWBlocks.looseStoneBrick.blockID, 0,
            BTWBlocks.infestedStone.blockID,
            BTWBlocks.upperStrataRoughStone.blockID,
            BTWItems.stone.itemID, 0,
            BTWItems.stoneBrick.itemID, 0,
            BTWItems.gravelPile.itemID, 0,
            0
    );
}
