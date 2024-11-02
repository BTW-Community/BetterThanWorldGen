package btwg.api.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VersionedDataset<T> {
    private final T defaultData;
    private final Map<VersionRule, T> data = new HashMap<>();
    
    public VersionedDataset(T defaultData) {
        this.defaultData = defaultData;
    }
    
    public VersionedDataset<T> addRule(VersionRule rule, T data) {
        if (this.validateNewRule(rule)) {
            this.data.put(rule, data);
            return this;
        }
        else {
            throw new IllegalArgumentException("Version rules must not overlap!");
        }
    }
    
    public T getData(Version version) {
        return this.getDataFromRules(version).orElse(this.defaultData);
    }
    
    private Optional<T> getDataFromRules(Version version) {
        for (var rule : data.keySet()) {
            if (rule.test(version)) {
                return Optional.of(data.get(rule));
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Rules must be bijective - each version must only satisfy at most a single version rule
     * @return Whether the new rule maintains bijectivity
     */
    private boolean validateNewRule(VersionRule rule) {
        for (var versions : Version.versions.values()) {
            for (var v : versions) {
                if (this.getDataFromRules(v).isPresent() && rule.test(v)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
