package btwg.mod;

import btwg.api.biome.BTWGBiome;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData.HeightData;
import btwg.api.world.feature.TreeDistributor;
import btwg.mod.world.feature.TreeDistributors;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ResourceLocation;

import java.util.ArrayList;

public abstract class BiomeConfiguration {
    public static final ArrayList<BiomeGenBase> biomeList = new ArrayList<>();
    
    public static final float COMMON_WEIGHT = 1F;
    public static final float UNCOMMON_WEIGHT = 0.5F;
    public static final float RARE_WEIGHT = 0.2F;
    public static final float VERY_RARE_WEIGHT = 0.1F;
    
    public static final int RAINFOREST_ID = 100;
    public static final int PLAINS_ID = 101;
    
    public static final int RAINFOREST_VALLEY_ID = 200;
    public static final int PLAINS_RIVER_SHORE_ID = 201;
    public static final int TROPICAL_RIVER_ID = 202;
    
    public static final HeightData MOUNTAIN_HEIGHT = new HeightData(1.0F, 2.0F);
    public static final HeightData PLAINS_HEIGHT = new HeightData(0.1F, 0.3F);
    public static final HeightData RIVER_HEIGHT = new HeightData(-0.5F, 0.0F);
    
    //------ Rivers ------//
    
    public static final BTWGBiome TROPICAL_RIVER = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(TROPICAL_RIVER_ID, loc("river")))
            .setHeightData(RIVER_HEIGHT)
            .setRiver())
            .setTemperatureAndRainfall(1F, 1F);
    
    //------ Beaches ------//

    //------ Sub Biomes ------//
    
    public static final BTWGBiome RAINFOREST_VALLEY = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_VALLEY_ID, loc("rainforest_valley")))
            .setHeightData(PLAINS_HEIGHT)
            .setRiverBiomeData(TROPICAL_RIVER))
            .setTemperatureAndRainfall(1F, 1F)
            .setTreeDistributor(TreeDistributors.RAINFOREST_TREES);
    
    public static final BTWGBiome PLAINS_RIVER_SHORE = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_RIVER_SHORE_ID, loc("plains_river_shore")))
            .setHeightData(new HeightData(0.0F, 0.0F)))
            .setTemperatureAndRainfall(0.8F, 0.4F)
            .setTreeDistributor(new TreeDistributor(5) {});
    
    //------ Primary Biomes ------//
    
    public static final BTWGBiome RAINFOREST = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_ID, loc("rainforest")))
            .setHeightData(MOUNTAIN_HEIGHT)
            .setRiverBiomeData(TROPICAL_RIVER)
            .setEdgeData(RAINFOREST_VALLEY)
            .setRiverShoreBiomeData(RAINFOREST_VALLEY)
            .setSubBiomeData(RAINFOREST_VALLEY))
            .setTemperatureAndRainfall(1F, 1F)
            .setTreeDistributor(TreeDistributors.RAINFOREST_TREES);
    
    public static final BTWGBiome PLAINS = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_ID, loc("plains")))
            .setHeightData(PLAINS_HEIGHT)
            .setRiverShoreBiomeData(PLAINS_RIVER_SHORE))
            .setTemperatureAndRainfall(0.8F, 0.4F)
            .setTreeDistributor(new TreeDistributor(0) {});
    
    public static void initBiomes() {
        ((BiomeInterface) BiomeGenBase.river).setRiver();
        ((BiomeInterface) BiomeGenBase.ocean).setMakesBeaches();
        
        biomeList.add(RAINFOREST);
        //biomeList.add(PLAINS);
    }
    
    public static ResourceLocation loc(String name) {
        return new ResourceLocation(BetterThanWorldGen.getInstance().getModID(), name);
    }
}
