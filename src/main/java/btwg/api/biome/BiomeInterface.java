package btwg.api.biome;

import btwg.api.biome.data.BiomeData;
import btwg.api.world.surface.Surfacer;
import net.minecraft.src.BiomeGenBase;

import java.util.Optional;

public interface BiomeInterface {
    float getWeight();
    BiomeInterface setWeight(float weight);
    
    boolean isVanilla();
    BiomeInterface setVanilla();

    Optional<BiomeData<Surfacer>> getSurfacer();
    BiomeInterface setSurfacer(BiomeData<Surfacer> surfacer);
    BiomeInterface setSurfacer(Surfacer surfacer);
    
    Optional<BiomeData<BiomeGenBase>> getSubBiomeData();
    BiomeInterface setSubBiomeData(BiomeData<BiomeGenBase> subBiomeData);
    BiomeInterface setSubBiomeData(BiomeGenBase biome);
    
    boolean isRiver();
    BiomeInterface setRiver();
    Optional<BiomeData<BiomeGenBase>> getRiverShoreBiomeData();
    BiomeInterface setRiverShoreBiomeData(BiomeData<BiomeGenBase> riverShoreBiomeData);
    BiomeInterface setRiverShoreBiomeData(BiomeGenBase biome);
    Optional<BiomeData<BiomeGenBase>> getRiverBiomeData();
    BiomeInterface setRiverBiomeData(BiomeData<BiomeGenBase> riverBiomeData);
    BiomeInterface setRiverBiomeData(BiomeGenBase biome);

    boolean hasEdges();
    BiomeInterface setNoEdges();
    Optional<BiomeData.ConditionalBiomeData> getEdgeData();
    BiomeInterface setEdgeData(BiomeData.ConditionalBiomeData edgeBiomeData);
    BiomeInterface setEdgeData(BiomeGenBase biome);

    boolean makesBeaches();
    BiomeInterface setMakesBeaches();
    Optional<BiomeData<BiomeGenBase>> getBeachData();
    BiomeInterface setBeachBiomeData(BiomeData<BiomeGenBase> riverShoreBiomeData);
    BiomeInterface setBeachBiomeData(BiomeGenBase biome);
}
