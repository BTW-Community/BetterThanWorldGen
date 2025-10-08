package btwg.api.block;

import btw.block.BTWBlocks;
import btw.block.blocks.SandAndGravelSlabBlock;
import btw.item.BTWItems;
import btwg.api.block.blocks.BTWGStoneSlabBlock;
import btwg.api.block.blocks.LooseStoneDecorativeBlock;
import btwg.mod.block.BTWGBlockIDs;
import btwg.mod.item.BTWGItemIDs;
import net.minecraft.src.Block;

import java.util.*;

public record StoneType(
        String name,

        int stoneID, int stoneMetadata,
        int polishedID, int polishedMetadata,
        int stoneSlabID, int stoneSlabMetadata,
        int stoneStairsID,

        int coalOreID, int coalOreMetadata,
        int ironOreID, int ironOreMetadata,
        int goldOreID, int goldOreMetadata,
        int diamondOreID, int diamondOreMetadata,
        int emeraldOreID, int emeraldOreMetadata,
        int redstoneOreID, int redstoneOreMetadata,
        int lapisOreID, int lapisOreMetadata,
        
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
        
        int strata,
        int index
) {
    public StoneType {
        STONE_TYPES_BY_STRATA.get(strata)[index] = this;
        STONE_TYPES[this.getTotalIndex(index, strata)] = this;
    }

    public static Optional<StoneType> getStoneType(int stoneID, int stoneMetadata) {
        for (StoneType stoneType : STONE_TYPES) {
            if (stoneType.stoneID() == stoneID && stoneType.stoneMetadata() == stoneMetadata) {
                return Optional.of(stoneType);
            }
        }
        return Optional.empty();
    }

    public static final int COBBLESTONE_TYPE = 0;
    public static final int POLISHED_TYPE = 1;
    public static final int STONE_BRICK_TYPE = 2;
    public static final int MOSSY_STONE_BRICK_TYPE = 3;
    public static final int CRACKED_STONE_BRICK_TYPE = 4;
    public static final int CHISELED_STONE_BRICK_TYPE = 5;
    public static final int MOSSY_COBBLESTONE_TYPE = 6;

    //------ Stone Type Indices ------//

    public static final Map<Integer, StoneType[]> STONE_TYPES_BY_STRATA = new HashMap<>();
    public static final int NUM_FIRST_STRATA = 6;
    public static final int NUM_SECOND_STRATA = 5;
    public static final int NUM_THIRD_STRATA = 2;

    public static final StoneType[] STONE_TYPES = new StoneType[NUM_FIRST_STRATA + NUM_SECOND_STRATA + NUM_THIRD_STRATA];

    static {
        STONE_TYPES_BY_STRATA.put(0, new StoneType[NUM_FIRST_STRATA]);
        STONE_TYPES_BY_STRATA.put(1, new StoneType[NUM_SECOND_STRATA]);
        STONE_TYPES_BY_STRATA.put(2, new StoneType[NUM_THIRD_STRATA]);
    }

    // First Strata
    public static final int RHYOLITE_INDEX = 0;
    public static final int GRANITE_INDEX = 1;
    public static final int DIORITE_INDEX = 2;
    public static final int LIMESTONE_INDEX = 3;
    public static final int CALCITE_INDEX = 4;
    public static final int SHALE_INDEX = 5;

    // Second Strata
    public static final int SLATE_INDEX = 0;
    public static final int TUFF_INDEX = 1;
    public static final int ANDESITE_INDEX = 2;
    public static final int PHYLLITE_INDEX = 3;
    public static final int SCHIST_INDEX = 4;

    // Third Strata
    public static final int GABBRO_INDEX = 0;
    public static final int BASALT_INDEX = 1;

    public static int getTotalIndex(int index, int strata) {
        int idx;
        if (strata == 0) {
            idx = index;
        }
        else if (strata == 1) {
            idx = NUM_FIRST_STRATA + index;
        }
        else {
            idx = NUM_FIRST_STRATA + NUM_SECOND_STRATA + index;
        }
        return idx;
    }

    public static final int NON_VANILLA_OFFSET = 1;
    public static final int NON_BTW_STRATA_1_OFFSET = 1;
    public static final int NON_BTW_STRATA_2_OFFSET = 2;
    public static final int NON_BTW_STRATA_3_OFFSET = 3;

    //------ Igneous Extrusive ------//

    // Vanilla stone
    public static final StoneType RHYOLITE = new StoneType(
            "rhyolite",

            Block.stone.blockID, 0,
            // TODO: implement
            0, 0,
            BTWBlocks.stoneSlab.blockID, 0,
            BTWBlocks.stoneStairs.blockID,

            Block.oreCoal.blockID, 0,
            Block.oreIron.blockID, 0,
            Block.oreGold.blockID, 0,
            Block.oreDiamond.blockID, 0,
            Block.oreEmerald.blockID, 0,
            Block.oreRedstone.blockID, 0,
            Block.oreLapis.blockID, 0,

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

            0,
            RHYOLITE_INDEX
    );

    public static final StoneType ANDESITE = new StoneType(
            "andesite",

            BTWGBlockIDs.ANDESITE_ID, 0,
            BTWGBlockIDs.ANDESITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.ANDESITE_SLAB_ID, 0,
            BTWGBlockIDs.ANDESITE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(ANDESITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(ANDESITE_INDEX, 1) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(ANDESITE_INDEX, 1) - NON_VANILLA_OFFSET,

            1,
            ANDESITE_INDEX
    );

    public static final StoneType GRANITE = new StoneType(
            "granite",

            BTWGBlockIDs.GRANITE_ID, 0,
            BTWGBlockIDs.GRANITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.GRANITE_SLAB_ID, 0,
            BTWGBlockIDs.GRANITE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(GRANITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(GRANITE_INDEX, 0) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(GRANITE_INDEX, 0) - NON_VANILLA_OFFSET,

            0,
            GRANITE_INDEX
    );

    public static final StoneType BASALT = new StoneType(
            "basalt",

            BTWGBlockIDs.BASALT_ID, 0,
            BTWGBlockIDs.BASALT_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.BASALT_SLAB_ID, 0,
            BTWGBlockIDs.BASALT_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(BASALT_INDEX, 2) - NON_BTW_STRATA_3_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(BASALT_INDEX, 2) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(BASALT_INDEX, 2) - NON_VANILLA_OFFSET,

            2,
            BASALT_INDEX
    );

    //------ Igneous Intrusive ------//

    public static final StoneType DIORITE = new StoneType(
            "diorite",

            BTWGBlockIDs.DIORITE_ID, 0,
            BTWGBlockIDs.DIORITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.DIORITE_SLAB_ID, 0,
            BTWGBlockIDs.DIORITE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(DIORITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(DIORITE_INDEX, 0) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(DIORITE_INDEX, 0) - NON_VANILLA_OFFSET,

            0,
            DIORITE_INDEX
    );

    // Vanilla blackstone
    public static final StoneType GABBRO = new StoneType(
            "gabbro",

            Block.stone.blockID, 2,
            // TODO: implement
            0, 0,
            BTWBlocks.stoneSlab.blockID, 2,
            BTWBlocks.deepStrataStoneStairs.blockID,

            Block.oreCoal.blockID, 2,
            Block.oreIron.blockID, 2,
            Block.oreGold.blockID, 2,
            Block.oreDiamond.blockID, 2,
            Block.oreEmerald.blockID, 2,
            Block.oreRedstone.blockID, 2,
            Block.oreLapis.blockID, 2,

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

            BTWGBlockIDs.GABBRO_GRAVEL_ID, 0,
            BTWGBlockIDs.GABBRO_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.GABBRO_REGOLITH_ID, 0,
            BTWGBlockIDs.GABBRO_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_GABBRO_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_GABBRO_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GABBRO_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_GABBRO_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_GABBRO_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_GABBRO_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GABBRO_REGOLITH_FARMLAND_ID, 0,

            BTWItems.stone.itemID, 2,
            BTWItems.stoneBrick.itemID, 2,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(GABBRO_INDEX, 2) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(GABBRO_INDEX, 2) - NON_VANILLA_OFFSET,

            2,
            GABBRO_INDEX
    );

    //------ Sedimentary ------//

    public static final StoneType LIMESTONE = new StoneType(
            "limestone",

            BTWGBlockIDs.LIMESTONE_ID, 0,
            BTWGBlockIDs.LIMESTONE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.LIMESTONE_SLAB_ID, 0,
            BTWGBlockIDs.LIMESTONE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(LIMESTONE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(LIMESTONE_INDEX, 0) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(LIMESTONE_INDEX, 0) - NON_VANILLA_OFFSET,

            0,
            LIMESTONE_INDEX
    );

    public static final StoneType CALCITE = new StoneType(
            "calcite",

            BTWGBlockIDs.CALCITE_ID, 0,
            BTWGBlockIDs.CALCITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.CALCITE_SLAB_ID, 0,
            BTWGBlockIDs.CALCITE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(CALCITE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(CALCITE_INDEX, 0) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(CALCITE_INDEX, 0) - NON_VANILLA_OFFSET,

            0,
            CALCITE_INDEX
    );

    public static final StoneType TUFF = new StoneType(
            "tuff",

            BTWGBlockIDs.TUFF_ID, 0,
            BTWGBlockIDs.TUFF_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.TUFF_SLAB_ID, 0,
            BTWGBlockIDs.TUFF_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(TUFF_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(TUFF_INDEX, 1) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(TUFF_INDEX, 1) - NON_VANILLA_OFFSET,

            1,
            TUFF_INDEX
    );

    //------ Metamorphic ------//

    public static final StoneType SHALE = new StoneType(
            "shale",

            BTWGBlockIDs.SHALE_ID, 0,
            BTWGBlockIDs.SHALE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.SHALE_SLAB_ID, 0,
            BTWGBlockIDs.SHALE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(SHALE_INDEX, 0) - NON_BTW_STRATA_1_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(SHALE_INDEX, 0) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(SHALE_INDEX, 0) - NON_VANILLA_OFFSET,

            0,
            SHALE_INDEX
    );

    // Vanilla deepslate
    public static final StoneType SLATE = new StoneType(
            "slate",

            Block.stone.blockID, 1,
            // TODO: implement
            0, 0,
            BTWBlocks.stoneSlab.blockID, 1,
            BTWBlocks.midStrataStoneStairs.blockID,

            Block.oreCoal.blockID, 1,
            Block.oreIron.blockID, 1,
            Block.oreGold.blockID, 1,
            Block.oreDiamond.blockID, 1,
            Block.oreEmerald.blockID, 1,
            Block.oreRedstone.blockID, 1,
            Block.oreLapis.blockID, 1,

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

            BTWGBlockIDs.SLATE_GRAVEL_ID, 0,
            BTWGBlockIDs.SLATE_GRAVEL_SLAB_ID, 0,
            BTWGBlockIDs.SLATE_REGOLITH_ID, 0,
            BTWGBlockIDs.SLATE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.GRASSY_SLATE_REGOLITH_ID, 0,
            BTWGBlockIDs.GRASSY_SLATE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SLATE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SPARSE_GRASSY_SLATE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.LOOSE_SLATE_REGOLITH_ID, 0,
            BTWGBlockIDs.LOOSE_SLATE_REGOLITH_SLAB_ID, 0,
            BTWGBlockIDs.SLATE_REGOLITH_FARMLAND_ID, 0,

            BTWItems.stone.itemID, 1,
            BTWItems.stoneBrick.itemID, 1,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(SLATE_INDEX, 1) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(SLATE_INDEX, 1) - NON_VANILLA_OFFSET,

            1,
            SLATE_INDEX
    );

    public static final StoneType PHYLLITE = new StoneType(
            "phyllite",

            BTWGBlockIDs.PHYLLITE_ID, 0,
            BTWGBlockIDs.PHYLLITE_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.PHYLLITE_SLAB_ID, 0,
            BTWGBlockIDs.PHYLLITE_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(PHYLLITE_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(PHYLLITE_INDEX, 1) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(PHYLLITE_INDEX, 1) - NON_VANILLA_OFFSET,

            1,
            PHYLLITE_INDEX
    );

    public static final StoneType SCHIST = new StoneType(
            "schist",

            BTWGBlockIDs.SCHIST_ID, 0,
            BTWGBlockIDs.SCHIST_DECORATIVE_ID, POLISHED_TYPE,
            BTWGBlockIDs.SCHIST_SLAB_ID, 0,
            BTWGBlockIDs.SCHIST_STAIRS_ID,

            BTWGBlockIDs.COAL_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.IRON_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.GOLD_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.DIAMOND_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.EMERALD_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.REDSTONE_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGBlockIDs.LAPIS_ORE_ID, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,

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

            BTWGItemIDs.ROCK_ID + 256, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.STONE_BRICK_ID + 256, getTotalIndex(SCHIST_INDEX, 1) - NON_BTW_STRATA_2_OFFSET,
            BTWGItemIDs.GRAVEL_PILE_ID + 256, getTotalIndex(SCHIST_INDEX, 1) - NON_VANILLA_OFFSET,
            BTWGItemIDs.DIRT_PILE_ID + 256, getTotalIndex(SCHIST_INDEX, 1) - NON_VANILLA_OFFSET,

            1,
            SCHIST_INDEX
    );
}
