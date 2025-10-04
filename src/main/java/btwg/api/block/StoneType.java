package btwg.api.block;

import btw.block.BTWBlocks;
import btw.block.blocks.SandAndGravelSlabBlock;
import btw.item.BTWItems;
import btwg.api.block.blocks.BTWGStoneSlabBlock;
import btwg.mod.block.BTWGBlockIDs;
import btwg.mod.item.BTWGItemIDs;
import net.minecraft.src.Block;

public record StoneType(
        String name,

        int stoneID, int stoneMetadata,
        int stoneSlabID, int stoneSlabMetadata,
        int stoneStairsID,
        
        int cobblestoneID, int cobblestoneMetadata,
        int cobblestoneSlabID, int cobblestoneSlabMetadata,
        int cobblestoneStairsID,
        int mossyCobblestoneID, int mossyCobblestoneMetadata,
        int looseCobblestoneID, int looseCobblestoneMetadata,
        int looseCobblestoneSlabID, int looseCobblestoneSlabMetadata,
        int looseCobblestoneStairsID,
        
        int stoneBrickID, int stoneBrickMetadata,
        int stoneBrickSlabID, int stoneBrickSlabMetadata,
        int stoneBrickStairsID,
        int mossyStoneBrickID, int mossyStoneBrickMetadata,
        int crackedStoneBrickID, int crackedStoneBrickMetadata,
        int chiseledStoneBrickID, int chiseledStoneBrickMetadata,
        int looseStoneBrickID, int looseStoneBrickMetadata,
        int looseStoneBrickSlabID, int looseStoneBrickSlabMetadata,
        int looseStoneBrickStairsID,
        
        int infestedStoneID,
        int roughStoneID,

        int gravelID, int gravelMetadata,
        int gravelSlabID, int gravelSlabMetadata,
        int dirtID, int dirtMetadata,
        int dirtSlabID, int dirtSlabMetadata,
        int grassID, int grassMetadata,
        int grassSlabID, int grassSlabMetadata,
        int looseSparseGrassID, int looseSparseGrassMetadata,
        int looseSparseGrassSlabID, int looseSparseGrassSlabMetadata,
        int looseDirtID, int looseDirtMetadata,
        int looseDirtSlabID, int looseDirtSlabMetadata,
        int farmlandID, int farmlandMetadata,
        
        int rockItemID, int rockItemMetadata,
        int stoneBrickItemID, int stoneBrickItemMetadata,
        int gravelPileID, int gravelPileMetadata,
        int dirtPileID, int dirtPileMetadata,
        
        int strata
) {
    public static final int COBBLESTONE_TYPE = 0;
    public static final int STONE_BRICK_TYPE = 1;
    public static final int MOSSY_STONE_BRICK_TYPE = 2;
    public static final int CRACKED_STONE_BRICK_TYPE = 3;
    public static final int CHISELED_STONE_BRICK_TYPE = 4;
    public static final int MOSSY_COBBLESTONE_TYPE = 5;

    public static final StoneType STONE = new StoneType(
            "stone",

            Block.stone.blockID, 0,
            BTWBlocks.stoneSlab.blockID, 0,
            BTWBlocks.stoneStairs.blockID,

            Block.cobblestone.blockID, 0,
            BTWBlocks.cobblestoneSlab.blockID, 0,
            Block.stairsCobblestone.blockID,
            Block.cobblestoneMossy.blockID, 0,
            BTWBlocks.looseCobblestone.blockID, 0,
            BTWBlocks.looseCobblestoneSlab.blockID, 0,
            BTWBlocks.looseCobblestoneStairs.blockID,

            Block.stoneBrick.blockID, 0,
            BTWBlocks.stoneBrickSlab.blockID, 5,
            Block.stairsStoneBrick.blockID,
            Block.stoneBrick.blockID, 1,
            Block.stoneBrick.blockID, 2,
            Block.stoneBrick.blockID, 3,
            BTWBlocks.looseStoneBrick.blockID, 0,
            BTWBlocks.looseStoneBrickSlab.blockID, 0,
            BTWBlocks.looseStoneBrickStairs.blockID,

            BTWBlocks.infestedStone.blockID,
            BTWBlocks.upperStrataRoughStone.blockID,

            Block.gravel.blockID, 0,
            BTWBlocks.sandAndGravelSlab.blockID, SandAndGravelSlabBlock.SUBTYPE_GRAVEL,
            Block.dirt.blockID, 0,
            BTWBlocks.dirtSlab.blockID, 0,
            Block.grass.blockID, 0,
            BTWBlocks.grassSlab.blockID, 0,
            BTWBlocks.looseSparseGrass.blockID, 0,
            BTWBlocks.looseSparseGrassSlab.blockID, 0,
            BTWBlocks.looseDirt.blockID, 0,
            BTWBlocks.looseDirtSlab.blockID, 0,
            BTWBlocks.farmland.blockID, 0,

            BTWItems.stone.itemID, 0,
            BTWItems.stoneBrick.itemID, 0,
            BTWItems.gravelPile.itemID, 0,
            BTWItems.dirtPile.itemID, 0,

            0
    );
    
    public static final StoneType DEEPSLATE = new StoneType(
            "deepslate",

            Block.stone.blockID, 1,
            BTWBlocks.stoneSlab.blockID, 1,
            BTWBlocks.midStrataStoneStairs.blockID,

            Block.cobblestone.blockID, 1,
            BTWBlocks.cobblestoneSlab.blockID, 4,
            BTWBlocks.midStrataCobblestoneStairs.blockID,
            Block.cobblestoneMossy.blockID, 1,
            BTWBlocks.looseCobblestone.blockID, 4,
            BTWBlocks.looseCobblestoneSlab.blockID, 1,
            BTWBlocks.looseCobbledDeepslateStairs.blockID,

            Block.stoneBrick.blockID, 4,
            BTWBlocks.stoneBrickSlab.blockID, 4,
            BTWBlocks.midStrataStoneBrickStairs.blockID,
            Block.stoneBrick.blockID, 5,
            Block.stoneBrick.blockID, 6,
            Block.stoneBrick.blockID, 7,
            BTWBlocks.looseStoneBrick.blockID, 4,
            BTWBlocks.looseStoneBrickSlab.blockID, 1,
            BTWBlocks.looseDeepslateBrickStairs.blockID,

            BTWBlocks.infestedMidStrataStone.blockID,
            BTWBlocks.midStrataRoughStone.blockID,

            Block.gravel.blockID, 0,
            BTWBlocks.sandAndGravelSlab.blockID, SandAndGravelSlabBlock.SUBTYPE_GRAVEL,
            Block.dirt.blockID, 0,
            BTWBlocks.dirtSlab.blockID, 0,
            Block.grass.blockID, 0,
            BTWBlocks.grassSlab.blockID, 0,
            BTWBlocks.looseSparseGrass.blockID, 0,
            BTWBlocks.looseSparseGrassSlab.blockID, 0,
            BTWBlocks.looseDirt.blockID, 0,
            BTWBlocks.looseDirtSlab.blockID, 0,
            BTWBlocks.farmland.blockID, 0,

            BTWItems.stone.itemID, 1,
            BTWItems.stoneBrick.itemID, 1,
            BTWItems.gravelPile.itemID, 1,
            BTWItems.dirtPile.itemID, 0,

            1
    );
    
    public static final StoneType BLACK_STONE = new StoneType(
            "blackstone",

            Block.stone.blockID, 2,
            BTWBlocks.stoneSlab.blockID, 2,
            BTWBlocks.deepStrataStoneStairs.blockID,

            Block.cobblestone.blockID, 2,
            BTWBlocks.cobblestoneSlab.blockID, 8,
            BTWBlocks.deepStrataCobblestoneStairs.blockID,
            Block.cobblestoneMossy.blockID, 2,
            BTWBlocks.looseCobblestone.blockID, 8,
            BTWBlocks.looseCobblestoneSlab.blockID, 2,
            BTWBlocks.looseCobbledBlackstoneStairs.blockID,

            Block.stoneBrick.blockID, 8,
            BTWBlocks.stoneBrickSlab.blockID, 8,
            BTWBlocks.deepStrataStoneBrickStairs.blockID,
            Block.stoneBrick.blockID, 9,
            Block.stoneBrick.blockID, 10,
            Block.stoneBrick.blockID, 11,
            BTWBlocks.looseStoneBrick.blockID, 8,
            BTWBlocks.looseStoneBrickSlab.blockID, 2,
            BTWBlocks.looseBlackstoneBrickStairs.blockID,

            BTWBlocks.infestedDeepStrataStone.blockID,
            BTWBlocks.deepStrataRoughStone.blockID,

            Block.gravel.blockID, 0,
            BTWBlocks.sandAndGravelSlab.blockID, SandAndGravelSlabBlock.SUBTYPE_GRAVEL,
            Block.dirt.blockID, 0,
            BTWBlocks.dirtSlab.blockID, 0,
            Block.grass.blockID, 0,
            BTWBlocks.grassSlab.blockID, 0,
            BTWBlocks.looseSparseGrass.blockID, 0,
            BTWBlocks.looseSparseGrassSlab.blockID, 0,
            BTWBlocks.looseDirt.blockID, 0,
            BTWBlocks.looseDirtSlab.blockID, 0,
            BTWBlocks.farmland.blockID, 0,

            BTWItems.stone.itemID, 2,
            BTWItems.stoneBrick.itemID, 2,
            BTWItems.gravelPile.itemID, 2,
            BTWItems.dirtPile.itemID, 0,

            2
    );

    public static final StoneType LIMESTONE = new StoneType(
            "limestone",

            BTWGBlockIDs.LIMESTONE_ID, 0,
            BTWGBlockIDs.LIMESTONE_SLAB_ID, 0,
            BTWGBlockIDs.LIMESTONE_STAIRS_ID,

            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LIMESTONE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_LIMESTONE_STAIRS_ID,
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_LIMESTONE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_LIMESTONE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_LIMESTONE_STAIRS_ID,

            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.LIMESTONE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.LIMESTONE_BRICK_STAIRS_ID,
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_LIMESTONE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_LIMESTONE_SLAB_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_LIMESTONE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_LIMESTONE_ID,

            BTWGBlockIDs.LIMESTONE_GRAVEL_ID, 0,
            BTWGBlockIDs.LIMESTONE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.LIMESTONE_REGOLITH_ID, 0,
            BTWGBlockIDs.LIMESTONE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_LIMESTONE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_LIMESTONE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_LIMESTONE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_LIMESTONE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_LIMESTONE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_LIMESTONE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LIMESTONE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );
}
