package btwg.mod;

import btwg.api.biome.*;
import btwg.api.world.feature.PlantDistributor;
import btwg.api.world.feature.TreeDistributor;
import btwg.api.world.generate.noise.NoiseProvider;
import btwg.api.world.surface.SandySurfacer;
import btwg.mod.world.feature.GrassDistributors;
import btwg.mod.world.feature.TreeDistributors;
import btwg.mod.world.surface.ScrublandSurfacer;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class BiomeConfiguration {
    public static final int RAINFOREST_ID = 100;
    public static final int PLAINS_ID = 101;
    public static final int SCRUBLAND_ID = 102;
    public static final int SAVANNA_ID = 103;
    public static final int DUNES_ID = 104;
    public static final int ARID_HIGHLANDS_ID = 105;
    public static final int FOREST_ID = 106;
    public static final int TAIGA_ID = 107;
    public static final int SNOWY_TAIGA_ID = 108;
    public static final int STONY_PEAKS_ID = 109;
    public static final int FROZEN_PEAKS_ID = 110;
    public static final int SNOWY_SLOPES_ID = 111;
    public static final int WINDSWEPT_HILLS_ID = 112;
    public static final int WINDSWEPT_FOREST_ID = 113;
    public static final int DARK_FOREST_ID = 114;
    public static final int PALE_GARDEN_ID = 115;
    public static final int CHERRY_GROVE_ID = 116;
    public static final int MEADOW_ID = 117;
    public static final int SANDSTONE_PEAKS_ID = 118;
    public static final int JAGGED_PEAKS_ID = 119;

    public static final int TROPICAL_RIVER_ID = 202;
    public static final int SCRUBLAND_RIVER_ID = 204;
    public static final int DESERT_RIVER_ID = 206;
    public static final int DESERT_RIVER_SHORE_ID = 207;
    public static final int SAVANNA_RIVER_ID = 210;

    public static final int DESERT_WATER_COLOR = 0x43D5EE;

    // Continentalness constants
    public static final double OCEAN = -0.5;
    public static final double BEACH = 0;
    public static final double LOWLANDS = 0.2;
    public static final double MID_CONTINENT = 0.5;
    public static final double HIGHLANDS = 0.7;
    public static final double MOUNTAIN_PEAKS = 0.9;

    // Erosion constants
    public static final double LOW_EROSION = -0.6;
    public static final double MEDIUM_EROSION = 0;
    public static final double HIGH_EROSION = 0.6;

    // Weirdness constants
    public static final double LOW_WEIRDNESS = -0.6;
    public static final double MEDIUM_WEIRDNESS = 0;
    public static final double HIGH_WEIRDNESS = 0.6;

    // Temperature constants
    public static final double FROZEN = 0.1;
    public static final double COLD = 0.4;
    public static final double TEMPERATE = 0.6;
    public static final double HOT = 0.9;

    // Humidity constants
    public static final double ARID = 0.1;
    public static final double SEMI_ARID = 0.3;
    public static final double SEMI_HUMID = 0.5;
    public static final double HUMID = 0.7;
    public static final double VERY_HUMID = 0.9;

    public static final BiPredicate<BiomeNoiseVector, Integer> IS_OCEAN = (vec, height) -> vec.continentalness() < 0;
    public static final BiPredicate<BiomeNoiseVector, Integer> IS_MOUNTAIN_PEAK = (vec, height) -> height >= NoiseProvider.SEA_LEVEL * 2;
    public static final BiPredicate<BiomeNoiseVector, Integer> IS_SNOWY = (vec, height) -> vec.temperature() < 0.2;
    
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

    public static final BTWGBiome FOREST = new BTWGBiome(
            FOREST_ID,
            loc("forest"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            SEMI_HUMID,
                            MID_CONTINENT,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setGrassDistributor(new PlantDistributor(4))
            .setTreeDistributor(TreeDistributors.FOREST);

    public static final BTWGBiome TAIGA = new BTWGBiome(
            TAIGA_ID,
            loc("taiga"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            COLD,
                            HUMID,
                            LOWLANDS,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setTreeDistributor(TreeDistributors.TAIGA);

    public static final BTWGBiome SNOWY_TAIGA = new BTWGBiome(
            SNOWY_TAIGA_ID,
            loc("snowy_taiga"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            FROZEN,
                            HUMID,
                            LOWLANDS,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    ),
                    (v, h) -> BiomeNoiseParameterTarget.DEFAULT_PREDICATE.test(v, h) && IS_SNOWY.test(v, h)
            ))
            .setTreeDistributor(TreeDistributors.TAIGA);

    public static final BTWGBiome STONY_PEAKS = new BTWGBiome(
            STONY_PEAKS_ID,
            loc("stony_peaks"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            SEMI_ARID,
                            MOUNTAIN_PEAKS,
                            HIGH_EROSION,
                            LOW_WEIRDNESS
                    ),
                    IS_MOUNTAIN_PEAK
            ))
            .setNoAnimals()
            .setTopBlock(Block.stone.blockID)
            .setFillerBlock(Block.stone.blockID);

    public static final BTWGBiome JAGGED_PEAKS = new BTWGBiome(
            JAGGED_PEAKS_ID,
            loc("jagged_peaks"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            COLD,
                            SEMI_ARID,
                            MOUNTAIN_PEAKS,
                            HIGH_EROSION,
                            LOW_WEIRDNESS
                    ),
                    IS_MOUNTAIN_PEAK
            ))
            .setNoAnimals()
            .setTopBlock(Block.stone.blockID)
            .setFillerBlock(Block.stone.blockID);

    public static final BTWGBiome SANDSTONE_PEAKS = new BTWGBiome(
            SANDSTONE_PEAKS_ID,
            loc("sandstone_peaks"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            ARID,
                            MOUNTAIN_PEAKS,
                            LOW_EROSION,
                            MEDIUM_WEIRDNESS
                    ),
                    IS_MOUNTAIN_PEAK
            ))
            .setNoAnimals()
            .setTopBlock(Block.sandStone.blockID)
            .setFillerBlock(Block.sandStone.blockID);

    public static final BTWGBiome WINDSWEPT_HILLS = new BTWGBiome(
            WINDSWEPT_HILLS_ID,
            loc("windswept_hills"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            COLD,
                            SEMI_HUMID,
                            HIGHLANDS,
                            MEDIUM_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setTreeDistributor(TreeDistributors.WINDSWEPT_HILLS);

    public static final BTWGBiome WINDSWEPT_FOREST = new BTWGBiome(
            WINDSWEPT_FOREST_ID,
            loc("windswept_forest"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            COLD,
                            HUMID,
                            HIGHLANDS,
                            MEDIUM_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setTreeDistributor(TreeDistributors.TAIGA);

    public static final BTWGBiome FROZEN_PEAKS = new BTWGBiome(
            FROZEN_PEAKS_ID,
            loc("frozen_peaks"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            FROZEN,
                            SEMI_HUMID,
                            MOUNTAIN_PEAKS,
                            MEDIUM_EROSION,
                            LOW_WEIRDNESS
                    ),
                    (v, h) -> IS_MOUNTAIN_PEAK.test(v, h) && IS_SNOWY.test(v, h)
            ))
            .setNoAnimals()
            .setTopBlock(Block.blockSnow.blockID)
            .setFillerBlock(Block.blockSnow.blockID);

    public static final BTWGBiome SNOWY_SLOPES = new BTWGBiome(
            SNOWY_SLOPES_ID,
            loc("snowy_slopes"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            FROZEN,
                            SEMI_HUMID,
                            HIGHLANDS,
                            MEDIUM_EROSION,
                            LOW_WEIRDNESS
                    ),
                    (v, h) -> BiomeNoiseParameterTarget.DEFAULT_PREDICATE.test(v, h) && IS_SNOWY.test(v, h)
            ));

    public static final BTWGBiome DARK_FOREST = new BTWGBiome(
            DARK_FOREST_ID,
            loc("dark_forest"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            VERY_HUMID,
                            LOWLANDS,
                            LOW_EROSION,
                            MEDIUM_WEIRDNESS
                    )
            ))
            .setGrassDistributor(GrassDistributors.TROPICAL_GRASS)
            .setTreeDistributor(TreeDistributors.DARK_FOREST)
            .setGrassColor(0x507A32)
            .setFoliageColor(0x59AE30);

    public static final BTWGBiome PALE_GARDEN = new BTWGBiome(
            PALE_GARDEN_ID,
            loc("pale_garden"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            TEMPERATE,
                            VERY_HUMID,
                            HIGHLANDS,
                            LOW_EROSION,
                            HIGH_WEIRDNESS
                    )
            ))
            .setGrassDistributor(GrassDistributors.TROPICAL_GRASS)
            .setTreeDistributor(TreeDistributors.PALE_GARDEN)
            .setGrassColor(0x778272)
            .setFoliageColor(0x878D76)
            .setWaterColor(0x76889D);

    public static final BTWGBiome CHERRY_GROVE = new BTWGBiome(
            CHERRY_GROVE_ID,
            loc("cherry_grove"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            HOT,
                            HUMID,
                            HIGHLANDS,
                            LOW_EROSION,
                            MEDIUM_WEIRDNESS
                    )
            ))
            .setGrassDistributor(new PlantDistributor(6))
            .setTreeDistributor(TreeDistributors.CHERRY)
            .setGrassColor(0xB6DB61)
            .setFoliageColor(0xB6DB62)
            .setWaterColor(0x5DB7EF);

    public static final BTWGBiome MEADOW = new BTWGBiome(
            MEADOW_ID,
            loc("meadow"),
            new BiomeNoiseParameterTarget(
                    new BiomeNoiseVector(
                            COLD,
                            SEMI_HUMID,
                            HIGHLANDS,
                            LOW_EROSION,
                            LOW_WEIRDNESS
                    )
            ))
            .setGrassDistributor(new PlantDistributor(10))
            .setTreeDistributor(TreeDistributors.MEADOW);
    
    public static void initBiomes() {}
    
    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.MODID, name);
    }
}
