package btwg.mod.block;

import btw.block.BTWBlocks;
import btw.crafting.util.FurnaceBurnTime;
import btw.item.BTWItems;
import btwg.mod.item.BTWGItemIDs;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public record WoodType(
        String name,

        int logID, int logMetadata,
        int chewedLogID,
        int logSpikeID,
        int stumpID, int stumpMetadata,
        int workStumpID, int workStumpMetadata,

        int leavesID, int leavesMetadata,
        int saplingID,

        int plankID, int plankMetadata,
        int stairsID,
        int slabID, int slabMetadata,
        int sidingAndCornerID,
        int mouldingID,
        int doorBlockID,
        int trapdoorID,

        int doorItemID, int doorItemMetadata,
        int barkID, int barkMetadata,

        int burnTime
) {
    private static final int WOOD_ID = 17;
    private static final int PLANKS_ID = 5;
    private static final int LEAVES_ID = 18;
    private static final int WOODEN_DOOR_ID = 64;
    private static final int TRAPDOOR_ID = 96;
    private static final int WOOD_SLAB_ID = 126;
    private static final int WOODEN_DOOR_ITEM_ID = 324;

    public static final WoodType OAK = new WoodType(
            "oak",
            WOOD_ID, 0,
            0,
            0,
            WOOD_ID, 12,
            0, 0,

            LEAVES_ID, 0,
            0,

            PLANKS_ID, 0,
            Block.stairsWoodOak.blockID,
            WOOD_SLAB_ID, 0,
            0,
            0,
            WOODEN_DOOR_ID,
            TRAPDOOR_ID,

            WOODEN_DOOR_ITEM_ID, 0,
            0, 0,

            FurnaceBurnTime.PLANKS_OAK.burnTime
    );

    public static final WoodType SPRUCE = new WoodType(
            "spruce",
            WOOD_ID, 1,
            0,
            0,
            WOOD_ID, 13,
            0, 0,

            LEAVES_ID, 1,
            BTWBlocks.spruceSapling.blockID,

            PLANKS_ID, 1,
            Block.stairsWoodSpruce.blockID,
            Block.woodSingleSlab.blockID, 1,
            BTWBlocks.spruceWoodSidingAndCorner.blockID,
            BTWBlocks.spruceWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.SPRUCE_DOOR_ID,
            BTWGBlockIDs.SPRUCE_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 0,
            0, 1,

            FurnaceBurnTime.PLANKS_SPRUCE.burnTime
    );

    public static final WoodType BIRCH = new WoodType(
            "birch",
            WOOD_ID, 2,
            0,
            0,
            WOOD_ID, 14,
            0, 0,

            LEAVES_ID, 2,
            BTWBlocks.birchSapling.blockID,

            PLANKS_ID, 2,
            Block.stairsWoodBirch.blockID,
            Block.woodSingleSlab.blockID, 2,
            BTWBlocks.birchWoodSidingAndCorner.blockID,
            BTWBlocks.birchWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.BIRCH_DOOR_ID,
            BTWGBlockIDs.BIRCH_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 1,
            0, 2,

            FurnaceBurnTime.PLANKS_BIRCH.burnTime
    );

    public static final WoodType JUNGLE = new WoodType(
            "jungle",
            WOOD_ID, 3,
            0,
            0,
            WOOD_ID, 15,
            0, 0,

            LEAVES_ID, 0,
            0,

            PLANKS_ID, 3,
            Block.stairsWoodJungle.blockID,
            Block.woodSingleSlab.blockID, 3,
            BTWBlocks.jungleWoodSidingAndCorner.blockID,
            BTWBlocks.jungleWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.JUNGLE_DOOR_ID,
            BTWGBlockIDs.JUNGLE_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 2,
            0, 3,

            FurnaceBurnTime.PLANKS_JUNGLE.burnTime
    );

    public static final WoodType BLOOD_WOOD = new WoodType(
            "blood_wood",
            WOOD_ID, 4,
            0,
            0,
            0, 0,
            0, 0,

            0, 0,
            0,

            PLANKS_ID, 3,
            0,
            Block.woodSingleSlab.blockID, 3,
            0,
            0,
            BTWGBlockIDs.BLOOD_WOOD_DOOR_ID,
            BTWGBlockIDs.BLOOD_WOOD_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 3,
            0, 3,

            FurnaceBurnTime.PLANKS_JUNGLE.burnTime
    );

    public static final WoodType ACACIA = new WoodType(
            "acacia",
            BTWGBlockIDs.ACACIA_LOG_ID, 0,
            BTWGBlockIDs.CHEWED_ACACIA_LOG_ID,
            BTWGBlockIDs.ACACIA_LOG_SPIKE_ID,
            BTWGBlockIDs.STUMP_ID, 0,
            BTWGBlockIDs.WORK_STUMP_ID, 0,

            BTWGBlockIDs.LEAVES_ID, 0,
            BTWGBlockIDs.ACACIA_SAPLING_ID,

            BTWGBlockIDs.PLANKS_ID, 0,
            BTWGBlockIDs.ACACIA_STAIRS_ID,
            BTWGBlockIDs.WOOD_SLAB_ID, 0,
            BTWGBlockIDs.ACACIA_SIDING_ID,
            BTWGBlockIDs.ACACIA_MOULDING_ID,
            BTWGBlockIDs.ACACIA_DOOR_ID,
            BTWGBlockIDs.ACACIA_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 4,
            BTWGItemIDs.BARK_ID + 256, 0,

            FurnaceBurnTime.PLANKS_BIRCH.burnTime
    );

    public static final WoodType DARK_OAK = new WoodType(
            "dark_oak",
            BTWGBlockIDs.DARK_OAK_LOG_ID, 0,
            BTWGBlockIDs.CHEWED_DARK_OAK_LOG_ID,
            BTWGBlockIDs.DARK_OAK_LOG_SPIKE_ID,
            BTWGBlockIDs.STUMP_ID, 1,
            BTWGBlockIDs.WORK_STUMP_ID, 1,

            BTWGBlockIDs.LEAVES_ID, 1,
            BTWGBlockIDs.DARK_OAK_SAPLING_ID,

            BTWGBlockIDs.PLANKS_ID, 1,
            BTWGBlockIDs.DARK_OAK_STAIRS_ID,
            BTWGBlockIDs.WOOD_SLAB_ID, 1,
            BTWGBlockIDs.DARK_OAK_SIDING_ID,
            BTWGBlockIDs.DARK_OAK_MOULDING_ID,
            BTWGBlockIDs.DARK_OAK_DOOR_ID,
            BTWGBlockIDs.DARK_OAK_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 5,
            BTWGItemIDs.BARK_ID + 256, 1,

            FurnaceBurnTime.PLANKS_BIRCH.burnTime
    );
}
