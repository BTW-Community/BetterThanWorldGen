package btwg.mod.world.feature;

import btwg.api.block.PlantType;
import btwg.api.world.feature.PlantDistributor;

public abstract class GrassDistributors {
    public static final PlantDistributor SCRUBLAND_GRASS = new PlantDistributor(2, 32, 8)
            .addPlant(PlantType.DEAD_BUSH, 1)
            .addPlant(PlantType.SHORT_DRY_GRASS, 2)
            .addPlant(PlantType.DRY_GRASS, 4)
            .addPlant(PlantType.TALL_DRY_GRASS, 4);

    public static final PlantDistributor OUTBACK = new PlantDistributor(2, 32, 8)
            .addPlant(PlantType.DEAD_BUSH, 1)
            .addPlant(PlantType.BUSH, 1)
            .addPlant(PlantType.SHORT_DRY_GRASS, 2)
            .addPlant(PlantType.DRY_GRASS, 4)
            .addPlant(PlantType.TALL_DRY_GRASS, 4);

    public static final PlantDistributor DESERT_GRASS = new PlantDistributor(3, 32, 8)
            .addPlant(PlantType.SHORT_DRY_GRASS, 5)
            .addPlant(PlantType.DRY_GRASS, 2)
            .addPlant(PlantType.TALL_DRY_GRASS, 1);

    public static final PlantDistributor TROPICAL_GRASS = new PlantDistributor(10)
            .addPlant(PlantType.BUSH, 10)
            .addPlant(PlantType.SHORT_GRASS, 10)
            .addPlant(PlantType.FERN, 10)
            .addPlant(PlantType.SCUTCH_GRASS, 10)
            .addPlant(PlantType.TALL_GRASS, 1)
            .addPlant(PlantType.LARGE_FERN, 1);

    public static final PlantDistributor MEADOW_GRASS = new PlantDistributor(10)
            .addPlant(PlantType.SHORT_GRASS, 40)
            .addPlant(PlantType.TIMOTHY_GRASS, 20)
            .addPlant(PlantType.BROMEGRASS, 10)
            .addPlant(PlantType.BLUEGRASS, 10)
            .addPlant(PlantType.TALL_GRASS, 4)
            .addPlant(PlantType.FOUNTAIN_GRASS, 2)
            .addPlant(PlantType.PHRAGMITE, 1);

    public static final PlantDistributor SAVANNA_GRASS = new PlantDistributor(10)
            .addPlant(PlantType.SHORT_GRASS, 10)
            .addPlant(PlantType.TIMOTHY_GRASS, 5)
            .addPlant(PlantType.BROMEGRASS, 2)
            .addPlant(PlantType.BLUEGRASS, 2)
            .addPlant(PlantType.TALL_GRASS, 1);

    public static final PlantDistributor MOUNTAIN_GRASS = new PlantDistributor(3)
            .addPlant(PlantType.SHORT_GRASS, 10)
            .addPlant(PlantType.TIMOTHY_GRASS, 5)
            .addPlant(PlantType.BROMEGRASS, 2)
            .addPlant(PlantType.BLUEGRASS, 2)
            .addPlant(PlantType.TALL_GRASS, 1);

    public static final PlantDistributor FOREST_GRASS = new PlantDistributor(6)
            .addPlant(PlantType.SHORT_GRASS, 10)
            .addPlant(PlantType.TIMOTHY_GRASS, 5)
            .addPlant(PlantType.BROMEGRASS, 2)
            .addPlant(PlantType.BLUEGRASS, 2)
            .addPlant(PlantType.TALL_GRASS, 1);

    public static final PlantDistributor BIRCH_FOREST_GRASS = new PlantDistributor(6)
            .addPlant(PlantType.SHORT_GRASS, 10)
            .addPlant(PlantType.TIMOTHY_GRASS, 5)
            .addPlant(PlantType.BUSH, 5)
            .addPlant(PlantType.TALL_GRASS, 1);
}
