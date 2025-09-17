package btwg.api.biome.data;

import btwg.api.biome.BiomeInterface;
import btwg.api.configuration.Version;
import btwg.api.configuration.VersionRule;
import btwg.api.configuration.VersionedDataset;
import net.minecraft.src.BiomeGenBase;

import java.util.Optional;
import java.util.function.Function;

public class BiomeData<T> {
    private VersionedDataset<T> data;
    
    public BiomeData(T defaultEntry) {
        this.data = new VersionedDataset<>(defaultEntry);
    }
    
    public BiomeData<T> addRule(VersionRule rule, T entry) {
        this.data.addRule(rule, entry);
        return this;
    }
    
    public T get(Version version) {
        return this.data.getData(version);
    }
    
    public static class HeightData extends BiomeData<HeightData.HeightValues>  {
        public HeightData(float height, float variance) {
            super(new HeightValues(height, variance));
        }
        
        public record HeightValues(float height, float variance) {}
    }
    
    public static class ConditionalBiomeData extends BiomeData<Function<BiomeInterface, Optional<BiomeGenBase>>>  {
        public ConditionalBiomeData(Function<BiomeInterface, Optional<BiomeGenBase>> transform) {
            super(transform);
        }
    }
}
