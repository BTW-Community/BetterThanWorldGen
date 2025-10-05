package btwg.api.block;

import btw.block.BTWBlocks;
import btw.block.blocks.SandAndGravelSlabBlock;
import btw.item.BTWItems;
import btwg.api.block.blocks.BTWGStoneSlabBlock;
import btwg.api.block.blocks.LooseStoneDecorativeBlock;
import btwg.mod.block.BTWGBlockIDs;
import btwg.mod.item.BTWGItemIDs;
import net.minecraft.src.Block;

public record StoneType(
        String name,

        int stoneID, int stoneMetadata,
        int polishedID, int polishedMetadata,
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
    public static final int POLISHED_TYPE = 1;
    public static final int STONE_BRICK_TYPE = 2;
    public static final int MOSSY_STONE_BRICK_TYPE = 3;
    public static final int CRACKED_STONE_BRICK_TYPE = 4;
    public static final int CHISELED_STONE_BRICK_TYPE = 5;
    public static final int MOSSY_COBBLESTONE_TYPE = 6;

    // Vanilla stone
    public static final StoneType RHYOLITE = new StoneType(
            "rhyolite",

            Block.stone.blockID, 0,
            // TODO: implement
            0, 0,
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

    // Vanilla deepslate
    public static final StoneType SLATE = new StoneType(
            "slate",

            Block.stone.blockID, 1,
            // TODO: implement
            0, 0,
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

    // Vanilla blackstone
    public static final StoneType GABBRO = new StoneType(
            "gabbro",

            Block.stone.blockID, 2,
            // TODO: implement
            0, 0,
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
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, POLISHED_TYPE,
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
            BTWGBlockIDs.LOOSE_LIMESTONE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
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

    public static final StoneType ANDESITE = new StoneType(
            "andesite",

            BTWGBlockIDs.ANDESITE_ID, 0,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.ANDESITE_SLAB_ID, 0,
            BTWGBlockIDs.ANDESITE_STAIRS_ID,

            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.ANDESITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_ANDESITE_STAIRS_ID,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_ANDESITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_ANDESITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_ANDESITE_STAIRS_ID,

            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.ANDESITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.ANDESITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_ANDESITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_ANDESITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_ANDESITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_ANDESITE_ID,

            BTWGBlockIDs.ANDESITE_GRAVEL_ID, 0,
            BTWGBlockIDs.ANDESITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.ANDESITE_REGOLITH_ID, 0,
            BTWGBlockIDs.ANDESITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_ANDESITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_ANDESITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_ANDESITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_ANDESITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_ANDESITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_ANDESITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.ANDESITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );
    
    public static final StoneType BASALT = new StoneType(
            "basalt",

            BTWGBlockIDs.BASALT_ID, 0,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.BASALT_SLAB_ID, 0,
            BTWGBlockIDs.BASALT_STAIRS_ID,

            BTWGBlockIDs.BASALT_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.BASALT_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_BASALT_STAIRS_ID,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_BASALT_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_BASALT_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_BASALT_STAIRS_ID,

            BTWGBlockIDs.BASALT_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.BASALT_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.BASALT_BRICK_STAIRS_ID,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_BASALT_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_BASALT_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_BASALT_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_BASALT_ID,

            BTWGBlockIDs.BASALT_GRAVEL_ID, 0,
            BTWGBlockIDs.BASALT_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.BASALT_REGOLITH_ID, 0,
            BTWGBlockIDs.BASALT_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_BASALT_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_BASALT_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_BASALT_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_BASALT_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_BASALT_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_BASALT_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.BASALT_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType CALCITE = new StoneType(
            "calcite",

            BTWGBlockIDs.CALCITE_ID, 0,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.CALCITE_SLAB_ID, 0,
            BTWGBlockIDs.CALCITE_STAIRS_ID,

            BTWGBlockIDs.CALCITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.CALCITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_CALCITE_STAIRS_ID,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_CALCITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_CALCITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_CALCITE_STAIRS_ID,

            BTWGBlockIDs.CALCITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.CALCITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.CALCITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_CALCITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_CALCITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_CALCITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_CALCITE_ID,

            BTWGBlockIDs.CALCITE_GRAVEL_ID, 0,
            BTWGBlockIDs.CALCITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.CALCITE_REGOLITH_ID, 0,
            BTWGBlockIDs.CALCITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_CALCITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_CALCITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_CALCITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_CALCITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_CALCITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_CALCITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.CALCITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType DIORITE = new StoneType(
            "diorite",

            BTWGBlockIDs.DIORITE_ID, 0,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.DIORITE_SLAB_ID, 0,
            BTWGBlockIDs.DIORITE_STAIRS_ID,

            BTWGBlockIDs.DIORITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.DIORITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_DIORITE_STAIRS_ID,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_DIORITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_DIORITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_DIORITE_STAIRS_ID,

            BTWGBlockIDs.DIORITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.DIORITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.DIORITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_DIORITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_DIORITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_DIORITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_DIORITE_ID,

            BTWGBlockIDs.DIORITE_GRAVEL_ID, 0,
            BTWGBlockIDs.DIORITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.DIORITE_REGOLITH_ID, 0,
            BTWGBlockIDs.DIORITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_DIORITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_DIORITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_DIORITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_DIORITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_DIORITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_DIORITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.DIORITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType GRANITE = new StoneType(
            "granite",

            BTWGBlockIDs.GRANITE_ID, 0,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.GRANITE_SLAB_ID, 0,
            BTWGBlockIDs.GRANITE_STAIRS_ID,

            BTWGBlockIDs.GRANITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.GRANITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_GRANITE_STAIRS_ID,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_GRANITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_GRANITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_GRANITE_STAIRS_ID,

            BTWGBlockIDs.GRANITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.GRANITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.GRANITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_GRANITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_GRANITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_GRANITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_GRANITE_ID,

            BTWGBlockIDs.GRANITE_GRAVEL_ID, 0,
            BTWGBlockIDs.GRANITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.GRANITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRANITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_GRANITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_GRANITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GRANITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GRANITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_GRANITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_GRANITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRANITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType KIMBERLITE = new StoneType(
            "kimberlite",

            BTWGBlockIDs.KIMBERLITE_ID, 0,
            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.KIMBERLITE_SLAB_ID, 0,
            BTWGBlockIDs.KIMBERLITE_STAIRS_ID,

            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.KIMBERLITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_KIMBERLITE_STAIRS_ID,
            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_KIMBERLITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_KIMBERLITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_KIMBERLITE_STAIRS_ID,

            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.KIMBERLITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.KIMBERLITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.KIMBERLITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_KIMBERLITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_KIMBERLITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_KIMBERLITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_KIMBERLITE_ID,

            BTWGBlockIDs.KIMBERLITE_GRAVEL_ID, 0,
            BTWGBlockIDs.KIMBERLITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.KIMBERLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.KIMBERLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_KIMBERLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_KIMBERLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_KIMBERLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_KIMBERLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_KIMBERLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_KIMBERLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.KIMBERLITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType PHYLLITE = new StoneType(
            "phyllite",

            BTWGBlockIDs.PHYLLITE_ID, 0,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.PHYLLITE_SLAB_ID, 0,
            BTWGBlockIDs.PHYLLITE_STAIRS_ID,

            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.PHYLLITE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_PHYLLITE_STAIRS_ID,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_PHYLLITE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_PHYLLITE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_PHYLLITE_STAIRS_ID,

            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.PHYLLITE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.PHYLLITE_BRICK_STAIRS_ID,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_PHYLLITE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_PHYLLITE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_PHYLLITE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_PHYLLITE_ID,

            BTWGBlockIDs.PHYLLITE_GRAVEL_ID, 0,
            BTWGBlockIDs.PHYLLITE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.PHYLLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.PHYLLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_PHYLLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_PHYLLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_PHYLLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_PHYLLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_PHYLLITE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_PHYLLITE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.PHYLLITE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType SCHIST = new StoneType(
            "schist",

            BTWGBlockIDs.SCHIST_ID, 0,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.SCHIST_SLAB_ID, 0,
            BTWGBlockIDs.SCHIST_STAIRS_ID,

            BTWGBlockIDs.SCHIST_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.SCHIST_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_SCHIST_STAIRS_ID,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_SCHIST_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_SCHIST_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_SCHIST_STAIRS_ID,

            BTWGBlockIDs.SCHIST_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.SCHIST_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.SCHIST_BRICK_STAIRS_ID,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_SCHIST_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_SCHIST_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_SCHIST_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_SCHIST_ID,

            BTWGBlockIDs.SCHIST_GRAVEL_ID, 0,
            BTWGBlockIDs.SCHIST_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.SCHIST_REGOLITH_ID, 0,
            BTWGBlockIDs.SCHIST_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_SCHIST_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_SCHIST_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SCHIST_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SCHIST_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SCHIST_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SCHIST_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.SCHIST_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType SHALE = new StoneType(
            "shale",

            BTWGBlockIDs.SHALE_ID, 0,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.SHALE_SLAB_ID, 0,
            BTWGBlockIDs.SHALE_STAIRS_ID,

            BTWGBlockIDs.SHALE_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.SHALE_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_SHALE_STAIRS_ID,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_SHALE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_SHALE_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_SHALE_STAIRS_ID,

            BTWGBlockIDs.SHALE_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.SHALE_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.SHALE_BRICK_STAIRS_ID,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_SHALE_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_SHALE_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_SHALE_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_SHALE_ID,

            BTWGBlockIDs.SHALE_GRAVEL_ID, 0,
            BTWGBlockIDs.SHALE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.SHALE_REGOLITH_ID, 0,
            BTWGBlockIDs.SHALE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_SHALE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_SHALE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SHALE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SHALE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SHALE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SHALE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.SHALE_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );

    public static final StoneType TUFF = new StoneType(
            "tuff",

            BTWGBlockIDs.TUFF_ID, 0,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.TUFF_SLAB_ID, 0,
            BTWGBlockIDs.TUFF_STAIRS_ID,

            BTWGBlockIDs.TUFF_DECORATIVE_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.TUFF_SLAB_ID, (COBBLESTONE_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.COBBLED_TUFF_STAIRS_ID,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, MOSSY_COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_TUFF_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_TUFF_SLAB_ID, COBBLESTONE_TYPE,
            BTWGBlockIDs.LOOSE_COBBLED_TUFF_STAIRS_ID,

            BTWGBlockIDs.TUFF_DECORATIVE_ID, STONE_BRICK_TYPE,
            BTWGBlockIDs.TUFF_SLAB_ID, (STONE_BRICK_TYPE + BTWGStoneSlabBlock.TYPE_OFFSET) << 1,
            BTWGBlockIDs.TUFF_BRICK_STAIRS_ID,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, MOSSY_STONE_BRICK_TYPE,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, CRACKED_STONE_BRICK_TYPE,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, CHISELED_STONE_BRICK_TYPE,
            BTWGBlockIDs.LOOSE_TUFF_ID, STONE_BRICK_TYPE << 1,
            BTWGBlockIDs.LOOSE_TUFF_SLAB_ID, STONE_BRICK_TYPE - LooseStoneDecorativeBlock.TYPE_OFFSET,
            BTWGBlockIDs.LOOSE_TUFF_BRICK_STAIRS_ID,

            0, // TODO: Infested Stone
            BTWGBlockIDs.ROUGH_TUFF_ID,

            BTWGBlockIDs.TUFF_GRAVEL_ID, 0,
            BTWGBlockIDs.TUFF_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.TUFF_REGOLITH_ID, 0,
            BTWGBlockIDs.TUFF_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_TUFF_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_TUFF_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_TUFF_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_TUFF_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_TUFF_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_TUFF_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.TUFF_REGOLITH_FARMLAND_ID, 0,

            BTWGItemIDs.ROCK_ID + 256, 0,
            BTWGItemIDs.STONE_BRICK_ID + 256, 0,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, 0,
            BTWGItemIDs.DIRT_PILE_ID + 256, 0,

            0
    );
}
