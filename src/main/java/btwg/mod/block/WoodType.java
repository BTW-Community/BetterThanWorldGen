package btwg.mod.block;

import btw.crafting.util.FurnaceBurnTime;
import btwg.mod.item.BTWGItemIDs;

public record WoodType(
        String name,

        int logID,
        int chewedLogID,
        int logSpikeID,
        int stumpID, int stumpMetadata,
        int workStumpID, int workStumpMetadata,
        int plankID, int plankMetadata,

        int barkID, int barkMetadata,

        int burnTime
) {
    public static final WoodType ACACIA = new WoodType(
            "acacia",
            BTWGBlockIDs.ACACIA_LOG_ID,
            BTWGBlockIDs.CHEWED_ACACIA_LOG_ID,
            BTWGBlockIDs.ACACIA_LOG_SPIKE_ID,
            BTWGBlockIDs.STUMP_ID, 0,
            BTWGBlockIDs.WORK_STUMP_ID, 0,
            BTWGBlockIDs.PLANKS_ID, 0,
            BTWGItemIDs.BARK_ID + 256, 0,
            FurnaceBurnTime.PLANKS_BIRCH.burnTime
    );
}
