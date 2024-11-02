package btwg.api.biome;

import btwg.api.configuration.Version;
import btwg.api.configuration.VersionRule;
import btwg.api.configuration.VersionedDataset;

public class BiomeData<T> {
    private VersionedDataset<T> data;
    
    public BiomeData(T entrydefaultEntry) {
        this.data = new VersionedDataset<>(entrydefaultEntry);
    }
    
    public BiomeData addRule(VersionRule rule, T entry) {
        this.data.addRule(rule, entry);
        return this;
    }
    
    public T get(Version version) {
        return this.data.getData(version);
    }
}
