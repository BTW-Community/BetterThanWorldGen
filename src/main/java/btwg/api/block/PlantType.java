package btwg.api.block;

import btwg.mod.block.BTWGBlockIDs;
import net.minecraft.src.Block;

public record PlantType(int id, int metadata, boolean isTall) {
    public PlantType(int id, int metadata) {
        this(id, metadata, false);
    }

    //------ Grasses ------//

    public static final PlantType DRY_GRASS = new PlantType(BTWGBlockIDs.DRY_GRASS_ID, 0);
    public static final PlantType SHORT_DRY_GRASS = new PlantType(BTWGBlockIDs.DRY_GRASS_ID, 1);
    public static final PlantType TALL_DRY_GRASS = new PlantType(BTWGBlockIDs.TALL_DRY_GRASS_ID, 0, true);

    public static final PlantType DEAD_BUSH = new PlantType(Block.deadBush.blockID, 0);

    public static final PlantType SHORT_GRASS = new PlantType(Block.tallGrass.blockID, 1);
    public static final PlantType FERN = new PlantType(Block.tallGrass.blockID, 2);

    public static final PlantType BUSH = new PlantType(BTWGBlockIDs.GRASS_ID, 0);
    public static final PlantType SCUTCH_GRASS = new PlantType(BTWGBlockIDs.GRASS_ID, 1);
    public static final PlantType TIMOTHY_GRASS = new PlantType(BTWGBlockIDs.GRASS_ID, 2);

    public static final PlantType BLUEGRASS = new PlantType(BTWGBlockIDs.GRASS_WITH_OVERLAY_ID, 0);
    public static final PlantType BROMEGRASS = new PlantType(BTWGBlockIDs.GRASS_WITH_OVERLAY_ID, 1);
    public static final PlantType FOUNTAIN_GRASS = new PlantType(BTWGBlockIDs.GRASS_WITH_OVERLAY_ID, 2);

    public static final PlantType TALL_GRASS = new PlantType(BTWGBlockIDs.TALL_GRASS_ID, 0, true);
    public static final PlantType LARGE_FERN = new PlantType(BTWGBlockIDs.TALL_GRASS_ID, 1, true);

    public static final PlantType PHRAGMITE = new PlantType(BTWGBlockIDs.TALL_GRASS_WITH_OVERLAY_ID, 0);

    //------ Flowers ------//

    public static final PlantType ALLIUM = new PlantType(BTWGBlockIDs.FLOWER_ID, 0);
    public static final PlantType AZURE_BLUET = new PlantType(BTWGBlockIDs.FLOWER_ID, 1);
    public static final PlantType BLUE_GINGER = new PlantType(BTWGBlockIDs.FLOWER_ID, 2);
    public static final PlantType BLUE_ORCHID = new PlantType(BTWGBlockIDs.FLOWER_ID, 3);
    public static final PlantType CORNFLOWER = new PlantType(BTWGBlockIDs.FLOWER_ID, 4);
    public static final PlantType GOLDENROD = new PlantType(BTWGBlockIDs.FLOWER_ID, 5);
    public static final PlantType GUZMANIA = new PlantType(BTWGBlockIDs.FLOWER_ID, 6);
    public static final PlantType HELICONIA = new PlantType(BTWGBlockIDs.FLOWER_ID, 7);
    public static final PlantType HYACINTH = new PlantType(BTWGBlockIDs.FLOWER_ID, 8);
    public static final PlantType LILY_OF_THE_VALLEY = new PlantType(BTWGBlockIDs.FLOWER_ID, 9);
    public static final PlantType NASTURTIUM = new PlantType(BTWGBlockIDs.FLOWER_ID, 10);
    public static final PlantType OXEYE_DAISY = new PlantType(BTWGBlockIDs.FLOWER_ID, 11);
    public static final PlantType PRIMROSE = new PlantType(BTWGBlockIDs.FLOWER_ID, 12);
    public static final PlantType TORCHFLOWER = new PlantType(BTWGBlockIDs.FLOWER_ID, 13);

    public static final PlantType ORANGE_TULIP = new PlantType(BTWGBlockIDs.FLOWER_ID, 0);
    public static final PlantType PINK_TULIP = new PlantType(BTWGBlockIDs.FLOWER_ID, 1);
    public static final PlantType RED_TULIP = new PlantType(BTWGBlockIDs.FLOWER_ID, 2);
    public static final PlantType WHITE_TULIP = new PlantType(BTWGBlockIDs.FLOWER_ID, 3);
    public static final PlantType PINK_SNAPDRAGON = new PlantType(BTWGBlockIDs.FLOWER_ID, 4);
    public static final PlantType RED_SNAPDRAGON = new PlantType(BTWGBlockIDs.FLOWER_ID, 5);
    public static final PlantType WHITE_SNAPDRAGON = new PlantType(BTWGBlockIDs.FLOWER_ID, 6);
    public static final PlantType YELLOW_SNAPDRAGON = new PlantType(BTWGBlockIDs.FLOWER_ID, 7);

    public static final PlantType FOXGLOVE = new PlantType(BTWGBlockIDs.TALL_FLOWER_ID, 0, true);
    public static final PlantType LILAC = new PlantType(BTWGBlockIDs.TALL_FLOWER_ID, 1, true);
    public static final PlantType PEONY = new PlantType(BTWGBlockIDs.TALL_FLOWER_ID, 2, true);
    public static final PlantType ROSE_BUSH = new PlantType(BTWGBlockIDs.TALL_FLOWER_ID, 3, true);
}
