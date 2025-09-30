package btwg.mod;

import btwg.api.biome.*;
import btwg.api.world.feature.PlantDistributor;
import btwg.api.world.feature.TreeDistributor;
import btwg.api.world.surface.SandySurfacer;
import btwg.mod.world.feature.GrassDistributors;
import btwg.mod.world.feature.TreeDistributors;
import btwg.mod.world.surface.ScrublandSurfacer;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;

import java.util.function.Predicate;

public abstract class BiomeConfiguration {
    public static final float UNCOMMON_WEIGHT = 0.5F;
    public static final float RARE_WEIGHT = 0.2F;
    public static final float VERY_RARE_WEIGHT = 0.1F;
    
    public static final int RAINFOREST_ID = 100;
    public static final int PLAINS_ID = 101;
    public static final int SCRUBLAND_ID = 102;
    public static final int SAVANNA_ID = 103;
    public static final int DUNES_ID = 104;
    public static final int ARID_HIGHLANDS_ID = 105;

    public static final int TROPICAL_RIVER_ID = 202;
    public static final int SCRUBLAND_RIVER_ID = 204;
    public static final int DESERT_RIVER_ID = 206;
    public static final int DESERT_RIVER_SHORE_ID = 207;
    public static final int SAVANNA_RIVER_ID = 210;

    public static final int DESERT_WATER_COLOR = 0x43D5EE;

    public static final Predicate<BiomeNoiseVector> IS_OCEAN = (vec) -> vec.continentalness() < 0;

    // Continentalness constants
    public static final double OCEAN = -0.5;
    public static final double BEACH = 0;
    public static final double LOWLANDS = 0.2;
    public static final double MID_CONTINENT = 0.5;
    public static final double HIGHLANDS = 0.75;
    public static final double MOUNTAINS = 0.95;

    // Erosion constants
    public static final double LOW_EROSION = -0.66;
    public static final double MEDIUM_EROSION = 0;
    public static final double HIGH_EROSION = 0.66;

    // Weirdness constants
    public static final double LOW_WEIRDNESS = -0.66;
    public static final double MEDIUM_WEIRDNESS = 0;
    public static final double HIGH_WEIRDNESS = 0.66;

    // Temperature constants
    public static final double FROZEN = 0.0;
    public static final double COLD = 0.33;
    public static final double TEMPERATE = 0.66;
    public static final double HOT = 1.0;

    // Humidity constants
    public static final double ARID = 0.0;
    public static final double SEMI_ARID = 0.2;
    public static final double SEMI_HUMID = 0.5;
    public static final double HUMID = 0.8;
    public static final double VERY_HUMID = 1.0;
    
    //------ Rivers ------//

    /*
    public static final BTWGBiome TROPICAL_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(
            TROPICAL_RIVER_ID,
            loc("tropical_river"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(

                    ),
                    (vec) -> true
            )))
            .setHeightData(DefaultBiomes.RIVER_HEIGHT)
            .setRiver()
            .setSurfacer(new SandySurfacer()))
            .setTemperatureAndRainfall(1F, 1F);

    public static final BTWGBiome SCRUBLAND_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SCRUBLAND_RIVER_ID, loc("scrubland_river")))
            .setHeightData(DefaultBiomes.RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(1F, 0F)
            .setNoRain()
            .setTreeDistributor(TreeDistributors.SCRUBLAND)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(BTWGBlocks.earthenClay.blockID)
            .setFillerBlock(BTWGBlocks.earthenClay.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome DESERT_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(DESERT_RIVER_ID, loc("desert_river")))
            .setHeightData(DefaultBiomes.RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(1F, 0F)
            .setNoRain()
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome SAVANNA_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SAVANNA_RIVER_ID, loc("savanna_river")))
            .setHeightData(DefaultBiomes.RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(1F, 0F)
            .setNoRain();
     */
    
    //------ Beaches ------//
    
    //------ Primary Biomes ------//
    
    public static final BTWGBiome RAINFOREST = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(
            RAINFOREST_ID,
            loc("rainforest"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            VERY_HUMID,
                            MID_CONTINENT,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            )))
            .setSurfacer(new SandySurfacer()))
            .setTreeDistributor(TreeDistributors.RAINFOREST)
            .setGrassDistributor(GrassDistributors.TROPICAL_GRASS);
    
    public static final BTWGBiome PLAINS = new BTWGBiome(
            PLAINS_ID,
            loc("plains"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            SEMI_HUMID,
                            LOWLANDS,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setTreeDistributor(new TreeDistributor(0) {})
            .setGrassDistributor(new PlantDistributor(10))
            .setHasHorses();

    public static final BTWGBiome SCRUBLAND = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(
            SCRUBLAND_ID,
            loc("scrubland"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            ARID,
                            LOWLANDS,
                            MEDIUM_EROSION,
                            LOW_WEIRDNESS
                    )
            )))
            .setSurfacer(new ScrublandSurfacer()))
            .setTreeDistributor(TreeDistributors.SCRUBLAND)
            .setGrassDistributor(GrassDistributors.SCRUBLAND_GRASS)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome SAVANNA = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(
            SAVANNA_ID,
            loc("savanna"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            ARID,
                            MID_CONTINENT,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            )))
            .setSurfacer(new ScrublandSurfacer()))
            .setTreeDistributor(TreeDistributors.SAVANNA)
            .setGrassDistributor(new PlantDistributor(10));

    public static final BTWGBiome DUNES = new BTWGBiome(
            DUNES_ID,
            loc("dunes"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            ARID,
                            HIGHLANDS,
                            HIGH_EROSION,
                            MEDIUM_WEIRDNESS
                    )
            ))
            .setGrassDistributor(GrassDistributors.DESERT_GRASS)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome ARID_HIGHLANDS = new BTWGBiome(
            ARID_HIGHLANDS_ID,
            loc("arid_highlands"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            SEMI_ARID,
                            HIGHLANDS,
                            MEDIUM_EROSION,
                            MEDIUM_WEIRDNESS
                    )
            ))
            .setGrassDistributor(new PlantDistributor(10))
            .setTreeDistributor(TreeDistributors.HIGHLANDS);
    
    public static void initBiomes() {}
    
    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.MODID, name);
    }
}
