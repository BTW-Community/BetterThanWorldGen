package btwg.mod;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.HeightData;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;

import java.util.ArrayList;

public abstract class BiomeConfiguration {
    public static final ArrayList<BiomeGenBase> biomeList = new ArrayList<>();
    
    public static final float COMMON_WEIGHT = 1F;
    public static final float UNCOMMON_WEIGHT = 0.5F;
    public static final float RARE_WEIGHT = 0.2F;
    public static final float VERY_RARE_WEIGHT = 0.1F;
    
    public static final int MOUNTAIN_FOREST_ID = 100;
    
    public static final BiomeGenBase MOUNTAIN_FOREST = (BiomeGenBase) ((BiomeInterface) new BiomeGenForest(MOUNTAIN_FOREST_ID)).setHeightData(mountainData());
    
    public static HeightData mountainData() {
        return new HeightData(2.0F, 1.5F);
    }
    
    public static HeightData plainsData() {
        return new HeightData(0.1F, 0.3F);
    }
    
    public static void initBiomes() {
        biomeList.add(MOUNTAIN_FOREST);
    }
}
