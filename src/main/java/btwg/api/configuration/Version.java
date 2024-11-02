package btwg.api.configuration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Version(String modid, int major, int minor, int patch) {
    public static Map<String, Set<Version>> versions = new HashMap<>();
    
    public Version {
        versions.computeIfAbsent(modid, m -> new HashSet<>());
        
        if (versions.get(modid).contains(this)) {
            throw new IllegalArgumentException("Cannot have duplicate versions within the same mod!");
        }
        versions.get(modid).add(this);
    }
    
    public boolean equals(Version version) {
        if (!version.modid.equals(this.modid)) {
            throw new IllegalArgumentException("Cannot compare versions between different mods!");
        }
        
        return version.major == this.major
                && version.minor == this.minor
                && version.patch == this.patch;
    }
    
    public boolean lessThan(Version version) {
        if (!version.modid.equals(this.modid)) {
            throw new IllegalArgumentException("Cannot compare versions between different mods!");
        }
    
        if (version.major > this.major) {
            return true;
        }
        else if (version.major == this.major) {
            if (version.minor > this.minor) {
                return true;
            }
            else if (version.minor == this.minor) {
                return version.patch > this.patch;
            }
        }
        
        return false;
    }
    
    public boolean lessThanOrEqual(Version version) {
        return this.lessThan(version) || this.equals(version);
    }
    
    public boolean greaterThan(Version version) {
        return !this.equals(version) && !this.lessThan(version);
    }
    
    public boolean greaterThanOrEqual(Version version) {
        return !this.lessThan(version);
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Version version) {
            return this.equals(version);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }
    
    @Override
    public String toString() {
        return this.major + "." + this.minor + "." + this.patch;
    }
    
    public static Optional<Version> fromString(String modid, String s) {
        Pattern pattern = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9])");
        Matcher matcher = pattern.matcher(s);
        
        if (matcher.matches()) {
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int patch = Integer.parseInt(matcher.group(3));
            
            for (Version v : versions.get(modid)) {
                if (v.major == major && v.minor == minor && v.patch == patch) {
                    return Optional.of(v);
                }
            }
        }
        
        return Optional.empty();
    }
}