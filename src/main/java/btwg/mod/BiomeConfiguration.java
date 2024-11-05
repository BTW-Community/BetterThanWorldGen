package btwg.mod;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import btwg.api.biome.BTWGBiome;
import btwg.api.biome.data.BiomeData;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData.HeightData;
import btwg.api.world.feature.BTWGTreeGrowers;
import btwg.api.world.feature.TreeDistributor;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

import java.util.ArrayList;
import java.util.Random;

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
    
    //------ Sub Biomes ------//
    
    public static final BTWGBiome RAINFOREST_VALLEY = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_VALLEY_ID))
            .setHeightData(plainsHeight()))
            .setTemperatureAndRainfall(1, 1)
            .setTreeDistributor(new TreeDistributor(10) {
                @Override
                public AbstractTreeGrower getTree(World world, Random rand, int x, int y, int z) {
                    int t = rand.nextInt(10);
    
                    if (t <= 1) {
                        return BTWGTreeGrowers.OAK_BUSH;
                    }
                    else if (t <= 4) {
                        return TreeGrowers.BIG_OAK_TREE;
                    }
                    else if (t <= 6) {
                        return TreeGrowers.JUNGLE_TREE;
                    }
                    else {
                        return BTWGTreeGrowers.TALL_OAK_TREE;
                    }
                }
            });
    
    public static final BTWGBiome PLAINS_RIVER_SHORE = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_RIVER_SHORE_ID))
            .setHeightData(new HeightData(0.0F, 0.0F)))
            .setTemperatureAndRainfall(0.5F, 0.3F)
            .setTreeDistributor(new TreeDistributor(5) {});
    
    //------ Primary Biomes ------//
    
    public static final BTWGBiome RAINFOREST = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(RAINFOREST_ID))
            .setHeightData(mountainHeight())
            .setRiverShoreBiomeData(new BiomeData<>(RAINFOREST_VALLEY))
            .setSubBiomeData(new BiomeData<>(RAINFOREST_VALLEY)))
            .setTemperatureAndRainfall(1, 1)
            .setTreeDistributor(RAINFOREST_VALLEY.getTreeDistributor());
    
    public static final BTWGBiome PLAINS = ((BTWGBiome) ((BiomeInterface) new BTWGBiome(PLAINS_ID))
            .setHeightData(plainsHeight())
            .setRiverShoreBiomeData(new BiomeData<>(PLAINS_RIVER_SHORE)))
            .setTemperatureAndRainfall(0.5F, 0.3F)
            .setTreeDistributor(new TreeDistributor(0) {});
    
    public static HeightData mountainHeight() {
        return new HeightData(1.0F, 2.0F);
    }
    
    public static HeightData plainsHeight() {
        return new HeightData(0.1F, 0.3F);
    }
    
    public static void initBiomes() {
        ((BiomeInterface) BiomeGenBase.river).setRiver();
        
        biomeList.add(RAINFOREST);
    }
}
