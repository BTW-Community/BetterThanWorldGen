package btwg.api.biome;

import btwg.api.biome.data.BiomeData;
import net.minecraft.src.BiomeGenBase;

import java.util.Optional;

public interface BiomeInterface {
    BiomeData.HeightData getHeightData();
    BiomeInterface setHeightData(BiomeData.HeightData heightData);
    
    float getWeight();
    BiomeInterface setWeight(float weight);
    
    boolean isVanilla();
    BiomeInterface setVanilla();
    
    boolean isRiver();
    BiomeInterface setRiver();

    boolean hasEdges();
    BiomeInterface setNoEdges();
    
    Optional<BiomeData<BiomeGenBase>> getSubBiomeData();
    BiomeInterface setSubBiomeData(BiomeData<BiomeGenBase> subBiomeData);
    
    Optional<BiomeData<BiomeGenBase>> getRiverShoreBiomeData();
    BiomeInterface setRiverShoreBiomeData(BiomeData<BiomeGenBase> riverShoreBiomeData);
    
    Optional<BiomeData.ConditionalBiomeData> getEdgeData();
    BiomeInterface setEdgeData(BiomeData.ConditionalBiomeData edgeBiomeData);
}
