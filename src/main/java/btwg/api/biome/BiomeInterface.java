package btwg.api.biome;

import net.minecraft.src.BiomeGenBase;

import java.util.Optional;

public interface BiomeInterface {
    HeightData getHeightData();
    BiomeInterface setHeightData(HeightData heightData);
    
    float getWeight();
    BiomeInterface setWeight(float weight);
    
    boolean isVanilla();
    BiomeInterface setVanilla();
    
    Optional<BiomeData<BiomeGenBase>> getSubBiomeData();
    BiomeInterface setSubBiomeData(BiomeData<BiomeGenBase> subBiomeData);
}
