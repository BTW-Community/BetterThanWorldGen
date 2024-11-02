package btwg.api.biome;

import btwg.api.configuration.Version;
import btwg.api.configuration.VersionRule;
import btwg.api.configuration.VersionedDataset;

public class HeightData {
    private VersionedDataset<HeightValues> data;
    
    public HeightData(float height, float variance) {
        this.data = new VersionedDataset<>(new HeightValues(height, variance));
    }
    
    public HeightData addRule(VersionRule rule, float height, float variance) {
        this.data.addRule(rule, new HeightValues(height, variance));
        return this;
    }
    
    public HeightValues getHeightValues(Version version) {
        return this.data.getData(version);
    }
    
    public record HeightValues(float height, float variance) {}
}
