package btwg.mod;

import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.Climate;
import btwg.api.biome.data.BiomeData.HeightData;
import btwg.api.world.feature.PlantDistributor;
import btwg.api.world.feature.TreeDistributor;
import btwg.api.world.surface.SandySurfacer;
import btwg.mod.block.BTWGBlocks;
import btwg.mod.world.feature.GrassDistributors;
import btwg.mod.world.feature.TreeDistributors;
import btwg.mod.world.surface.ScrublandShoreSurfacer;
import btwg.mod.world.surface.ScrublandSurfacer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ResourceLocation;

import java.util.*;

public abstract class BiomeConfiguration {
    public static final Set<BiomeGenBase> biomeList = new HashSet<>();

    public static final Climate SNOWY = new Climate(1, true);
    public static final Climate COLD = new Climate(2, true);
    public static final Climate TEMPERATE = new Climate(3, true);
    public static final Climate ARID = new Climate(4, true);
    public static final Climate TROPICAL = new Climate(5, true);

    public static final Climate NETHER = new Climate(10, false);

    public static final float UNCOMMON_WEIGHT = 0.5F;
    public static final float RARE_WEIGHT = 0.2F;
    public static final float VERY_RARE_WEIGHT = 0.1F;
    
    public static final int RAINFOREST_ID = 100;
    public static final int PLAINS_ID = 101;
    public static final int SCRUBLAND_ID = 102;
    public static final int SAVANNA_ID = 103;
    public static final int DUNES_ID = 104;
    
    public static final int RAINFOREST_VALLEY_ID = 200;
    public static final int PLAINS_RIVER_SHORE_ID = 201;
    public static final int TROPICAL_RIVER_ID = 202;
    public static final int SCRUBLAND_RIVER_SHORE_ID = 203;
    public static final int SCRUBLAND_RIVER_ID = 204;
    public static final int SAVANNA_PLATEAU_ID = 205;
    public static final int DESERT_RIVER_ID = 206;
    public static final int DESERT_RIVER_SHORE_ID = 207;
    
    public static final HeightData MOUNTAIN_HEIGHT = new HeightData(1.0F, 2.0F);
    public static final HeightData PLAINS_HEIGHT = new HeightData(0.1F, 0.3F);
    public static final HeightData RIVER_HEIGHT = new HeightData(-0.75F, 0.0F);
    public static final HeightData PLATEAU_HEIGHT = new HeightData(1.5F, 0.0F);
    public static final HeightData ROLLING_HILLS_HEIGHT = new HeightData(0.75F, 0.75F);
    public static final HeightData FLAT_HEIGHT = new HeightData(0.0F, 0.0F);

    public static final int DESERT_WATER_COLOR = 0x43D5EE;
    
    //------ Rivers ------//
    
    public static final BTWGBiome TROPICAL_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(TROPICAL_RIVER_ID, loc("tropical_river")))
            .setHeightData(RIVER_HEIGHT)
            .setRiver()
            .setSurfacer(new SandySurfacer()))
            .setTemperatureAndRainfall(1F, 1F);

    public static final BTWGBiome SCRUBLAND_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SCRUBLAND_RIVER_ID, loc("scrubland_river")))
            .setHeightData(RIVER_HEIGHT)
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
            .setHeightData(RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(1F, 0F)
            .setNoRain()
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);
    
    //------ Beaches ------//

    //------ Sub Biomes ------//
    
    public static final BTWGBiome RAINFOREST_VALLEY = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_VALLEY_ID, loc("rainforest_valley")))
            .setHeightData(PLAINS_HEIGHT)
            .setRiverBiomeData(TROPICAL_RIVER)
            .setSurfacer(new SandySurfacer()))
            .setTemperatureAndRainfall(1F, 1F)
            .setTreeDistributor(TreeDistributors.RAINFOREST_TREES)
            .setGrassDistributor(GrassDistributors.TROPICAL_GRASS);

    public static final BTWGBiome SAVANNA_PLATEAU = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SAVANNA_PLATEAU_ID, loc("savanna_plateau")))
            .setHeightData(PLATEAU_HEIGHT))
            .setTemperatureAndRainfall(1F, 0F)
            .setNoRain()
            .setGrassDistributor(new PlantDistributor(10))
            .setHasHorses();

    //------ River Shores ------//
    
    public static final BTWGBiome PLAINS_RIVER_SHORE = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_RIVER_SHORE_ID, loc("plains_river_shore")))
            .setHeightData(FLAT_HEIGHT))
            .setTemperatureAndRainfall(0.8F, 0.4F)
            .setTreeDistributor(new TreeDistributor(5) {})
            .setHasHorses();

    public static final BTWGBiome SCRUBLAND_RIVER_SHORE = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SCRUBLAND_RIVER_SHORE_ID, loc("scrubland_river_shore")))
            .setRiverBiomeData(SCRUBLAND_RIVER)
            .setHeightData(FLAT_HEIGHT)
            .setSurfacer(new ScrublandShoreSurfacer()))
            .setTemperatureAndRainfall(1F, 0.0F)
            .setNoRain()
            .setTreeDistributor(TreeDistributors.SCRUBLAND)
            .setGrassDistributor(GrassDistributors.SCRUBLAND_GRASS)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome DESERT_RIVER_SHORE = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(DESERT_RIVER_SHORE_ID, loc("desert_river_shore")))
            .setRiverBiomeData(DESERT_RIVER)
            .setHeightData(FLAT_HEIGHT))
            .setTemperatureAndRainfall(1F, 0.0F)
            .setNoRain()
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);
    
    //------ Primary Biomes ------//
    
    public static final BTWGBiome RAINFOREST = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_ID, loc("rainforest")))
            .setHeightData(MOUNTAIN_HEIGHT)
            .setRiverBiomeData(TROPICAL_RIVER)
            .setEdgeData(RAINFOREST_VALLEY)
            .setRiverShoreBiomeData(RAINFOREST_VALLEY)
            .setSubBiomeData(RAINFOREST_VALLEY)
            .setSurfacer(new SandySurfacer()))
            .setTemperatureAndRainfall(1F, 1F)
            .addClimate(TROPICAL)
            .setTreeDistributor(TreeDistributors.RAINFOREST_TREES)
            .setGrassDistributor(GrassDistributors.TROPICAL_GRASS);
    
    public static final BTWGBiome PLAINS = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_ID, loc("plains")))
            .setHeightData(PLAINS_HEIGHT)
            .setRiverShoreBiomeData(PLAINS_RIVER_SHORE))
            .setTemperatureAndRainfall(0.8F, 0.4F)
            .addClimate(TEMPERATE)
            .addClimate(ARID, UNCOMMON_WEIGHT)
            .setTreeDistributor(new TreeDistributor(0) {})
            .setGrassDistributor(new PlantDistributor(10))
            .setHasHorses();

    public static final BTWGBiome SCRUBLAND = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SCRUBLAND_ID, loc("scrubland")))
            .setHeightData(PLAINS_HEIGHT)
            .setRiverBiomeData(SCRUBLAND_RIVER)
            .setRiverShoreBiomeData(SCRUBLAND_RIVER_SHORE)
            .setSurfacer(new ScrublandSurfacer()))
            .setTemperatureAndRainfall(1.0F, 0.0F)
            .addClimate(ARID)
            .setNoRain()
            .setTreeDistributor(TreeDistributors.SCRUBLAND)
            .setGrassDistributor(GrassDistributors.SCRUBLAND_GRASS)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    public static final BTWGBiome SAVANNA = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(SAVANNA_ID, loc("savanna")))
            .setHeightData(PLAINS_HEIGHT)
            .setSubBiomeData(SAVANNA_PLATEAU))
            .setTemperatureAndRainfall(1.0F, 0.0F)
            .addClimate(ARID)
            .setNoRain()
            .setHasHorses()
            .setGrassDistributor(new PlantDistributor(10));

    public static final BTWGBiome DUNES = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(DUNES_ID, loc("dunes")))
            .setHeightData(ROLLING_HILLS_HEIGHT)
            .setRiverBiomeData(DESERT_RIVER)
            .setRiverShoreBiomeData(DESERT_RIVER_SHORE))
            .setTemperatureAndRainfall(1.0F, 0.0F)
            .addClimate(ARID, UNCOMMON_WEIGHT)
            .setNoRain()
            .setGrassDistributor(GrassDistributors.DESERT_GRASS)
            .setNoLakes()
            .setNoAnimals()
            .setTopBlock(Block.sand.blockID)
            .setFillerBlock(Block.sand.blockID)
            .setWaterColor(DESERT_WATER_COLOR);

    static { ((BiomeInterface) SAVANNA_PLATEAU).setRiverShoreBiomeData(SAVANNA); }
    
    public static void initBiomes() {
        ((BiomeInterface) BiomeGenBase.river).setRiver();
        ((BiomeInterface) BiomeGenBase.ocean).setMakesBeaches();
        
        biomeList.add(RAINFOREST);
        biomeList.add(PLAINS);
        biomeList.add(SAVANNA);
        biomeList.add(SCRUBLAND);
        biomeList.add(DUNES);
    }
    
    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.MODID, name);
    }
}
