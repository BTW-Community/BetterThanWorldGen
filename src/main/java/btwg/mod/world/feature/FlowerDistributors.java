package btwg.mod.world.feature;

import btwg.api.block.PlantType;
import btwg.api.world.feature.PlantDistributor;

public class FlowerDistributors {
    public static final PlantDistributor FLOWER_FOREST_FLOWERS = new PlantDistributor(5)
            .selectPerPatch()
            .addPlant(PlantType.BLUE_GINGER, 6)
            .addPlant(PlantType.ALLIUM, 6)
            .addPlant(PlantType.NASTURTIUM, 3)
            .addPlant(PlantType.HYACINTH, 10)
            .addPlant(PlantType.ORANGE_TULIP, 10)
            .addPlant(PlantType.RED_TULIP, 10)
            .addPlant(PlantType.PINK_TULIP, 10)
            .addPlant(PlantType.WHITE_TULIP, 10)
            .addPlant(PlantType.PEONY, 1)
            .addPlant(PlantType.LILAC, 1)
            .addPlant(PlantType.ROSE_BUSH, 1);

    public static final PlantDistributor MEADOW_FLOWERS = new PlantDistributor(3, 64, 8)
            .selectPerPatch()
            .addPlant(PlantType.BLUE_GINGER, 2)
            .addPlant(PlantType.ALLIUM, 1)
            .addPlant(PlantType.NASTURTIUM, 1)
            .addPlant(PlantType.HYACINTH, 2)
            .addPlant(PlantType.ORANGE_TULIP, 2)
            .addPlant(PlantType.RED_TULIP, 2)
            .addPlant(PlantType.PINK_TULIP, 2)
            .addPlant(PlantType.WHITE_TULIP, 2);

    public static final PlantDistributor PLAINS_FLOWERS = new PlantDistributor(1, 32, 8)
            .selectPerPatch()
            .addPlant(PlantType.DANDELION, 1)
            .addPlant(PlantType.POPPY, 1)
            .addPlant(PlantType.CORNFLOWER, 1)
            .addPlant(PlantType.OXEYE_DAISY, 1)
            .addPlant(PlantType.GOLDENROD, 1)
            .addPlant(PlantType.AZURE_BLUET, 1);

    public static final PlantDistributor BIRCH_FOREST_FLOWERS = new PlantDistributor(3, 64, 8)
            .selectPerPatch()
            .addPlant(PlantType.HYACINTH, 10)
            .addPlant(PlantType.PRIMROSE, 10)
            .addPlant(PlantType.RED_SNAPDRAGON, 10)
            .addPlant(PlantType.PINK_SNAPDRAGON, 10)
            .addPlant(PlantType.WHITE_SNAPDRAGON, 10)
            .addPlant(PlantType.YELLOW_SNAPDRAGON, 10)
            .addPlant(PlantType.FOXGLOVE, 1);

    public static final PlantDistributor FOREST_FLOWERS = new PlantDistributor(1, 32, 8)
            .selectPerPatch()
            .addPlant(PlantType.DANDELION, 10)
            .addPlant(PlantType.POPPY, 10)
            .addPlant(PlantType.GOLDENROD, 10)
            .addPlant(PlantType.PEONY, 1)
            .addPlant(PlantType.LILAC, 1)
            .addPlant(PlantType.ROSE_BUSH, 1);

    public static final PlantDistributor SWAMP_FLOWERS = new PlantDistributor(1, 32, 8)
            .selectPerPatch()
            .addPlant(PlantType.BLUE_ORCHID, 10)
            .addPlant(PlantType.HELICONIA, 10)
            .addPlant(PlantType.SWAMP_AZALEA, 1);

    public static final PlantDistributor TAIGA_FLOWERS = new PlantDistributor(2, 64, 8)
            .selectPerPatch()
            .addPlant(PlantType.HYACINTH, 1)
            .addPlant(PlantType.BLUE_GINGER, 1)
            .addPlant(PlantType.LILY_OF_THE_VALLEY, 1);

    public static final PlantDistributor TROPICAL_FLOWERS = new PlantDistributor(1, 32, 8)
            .selectPerPatch()
            .addPlant(PlantType.GUZMANIA, 10)
            .addPlant(PlantType.HELICONIA, 10)
            .addPlant(PlantType.MARIGOLD, 1);

    public static final PlantDistributor DARK_FOREST_FLOWERS = new PlantDistributor(2, 64, 8)
            .selectPerPatch()
            .addPlant(PlantType.DANDELION, 10)
            .addPlant(PlantType.POPPY, 10)
            .addPlant(PlantType.LILY_OF_THE_VALLEY, 10)
            .addPlant(PlantType.BLACK_THISTLE, 10)
            .addPlant(PlantType.ROSE_BUSH, 10);

    public static final PlantDistributor PALE_GARDEN_FLOWERS = new PlantDistributor(2, 64, 8)
            .selectPerPatch()
            .addPlant(PlantType.EYEBLOSSOM, 1);
}
