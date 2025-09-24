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
    public static final WoodType OAK = new WoodType(
            "oak",
            Block.wood.blockID, 0,
            BTWBlocks.oakChewedLog.blockID,
            BTWBlocks.oakLogSpike.blockID,
            Block.wood.blockID, 12,
            BTWBlocks.workStump.blockID, 0,

            Block.leaves.blockID, 0,
            BTWBlocks.oakSapling.blockID,

            Block.planks.blockID, 0,
            Block.stairsWoodOak.blockID,
            Block.woodSingleSlab.blockID, 0,
            BTWBlocks.oakWoodSidingAndCorner.blockID,
            BTWBlocks.oakWoodMouldingAndDecorative.blockID,
            Block.doorWood.blockID,
            Block.trapdoor.blockID,

            Item.doorWood.itemID + 256, 0,
            BTWItems.bark.itemID + 256, 0,

            FurnaceBurnTime.PLANKS_OAK.burnTime
    );

    public static final WoodType SPRUCE = new WoodType(
            "spruce",
            Block.wood.blockID, 1,
            BTWBlocks.spruceChewedLog.blockID,
            BTWBlocks.spruceLogSpike.blockID,
            Block.wood.blockID, 13,
            BTWBlocks.workStump.blockID, 1,

            Block.leaves.blockID, 1,
            BTWBlocks.spruceSapling.blockID,

            Block.planks.blockID, 1,
            Block.stairsWoodSpruce.blockID,
            Block.woodSingleSlab.blockID, 1,
            BTWBlocks.spruceWoodSidingAndCorner.blockID,
            BTWBlocks.spruceWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.SPRUCE_DOOR_ID,
            BTWGBlockIDs.SPRUCE_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 0,
            BTWItems.bark.itemID + 256, 1,

            FurnaceBurnTime.PLANKS_SPRUCE.burnTime
    );

    public static final WoodType BIRCH = new WoodType(
            "birch",
            Block.wood.blockID, 2,
            BTWBlocks.birchChewedLog.blockID,
            BTWBlocks.birchLogSpike.blockID,
            Block.wood.blockID, 14,
            BTWBlocks.workStump.blockID, 2,

            Block.leaves.blockID, 2,
            BTWBlocks.birchSapling.blockID,

            Block.planks.blockID, 2,
            Block.stairsWoodBirch.blockID,
            Block.woodSingleSlab.blockID, 2,
            BTWBlocks.birchWoodSidingAndCorner.blockID,
            BTWBlocks.birchWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.BIRCH_DOOR_ID,
            BTWGBlockIDs.BIRCH_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 1,
            BTWItems.bark.itemID + 256, 2,

            FurnaceBurnTime.PLANKS_BIRCH.burnTime
    );

    public static final WoodType JUNGLE = new WoodType(
            "jungle",
            Block.wood.blockID, 3,
            BTWBlocks.jungleChewedLog.blockID,
            BTWBlocks.jungleLogSpike.blockID,
            Block.wood.blockID, 15,
            BTWBlocks.workStump.blockID, 3,

            Block.leaves.blockID, 0,
            BTWBlocks.jungleSapling.blockID,

            Block.planks.blockID, 3,
            Block.stairsWoodJungle.blockID,
            Block.woodSingleSlab.blockID, 3,
            BTWBlocks.jungleWoodSidingAndCorner.blockID,
            BTWBlocks.jungleWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.JUNGLE_DOOR_ID,
            BTWGBlockIDs.JUNGLE_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 2,
            BTWItems.bark.itemID + 256, 3,

            FurnaceBurnTime.PLANKS_JUNGLE.burnTime
    );

    public static final WoodType BLOOD_WOOD = new WoodType(
            "blood_wood",
            Block.wood.blockID, 4,
            0,
            0,
            0, 0,
            0, 0,

            BTWBlocks.bloodWoodLeaves.blockID, 0,
            BTWBlocks.aestheticVegetation.blockID,

            Block.planks.blockID, 3,
            Block.stairsWoodJungle.blockID,
            Block.woodSingleSlab.blockID, 3,
            BTWBlocks.jungleWoodSidingAndCorner.blockID,
            BTWBlocks.jungleWoodMouldingAndDecorative.blockID,
            BTWGBlockIDs.JUNGLE_DOOR_ID,
            BTWGBlockIDs.JUNGLE_TRAPDOOR_ID,

            BTWGItemIDs.DOOR_ID + 256, 2,
            BTWItems.bark.itemID + 256, 3,

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
}
