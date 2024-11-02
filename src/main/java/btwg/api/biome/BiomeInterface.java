package btwg.api.biome;

import net.minecraft.src.BiomeGenBase;

public interface BiomeInterface {
    HeightData getHeightData();
    BiomeGenBase setHeightData(HeightData heightData);
    
    float getWeight();
    BiomeGenBase setWeight(float weight);
    
    boolean isVanilla();
    BiomeGenBase setVanilla();
}
