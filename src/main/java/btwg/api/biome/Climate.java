package btwg.api.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Climate(int id, boolean isOverworld) {
    public static final Map<Climate, ArrayList<ClimateEntry>> climateBiomeMap = new HashMap<>();

    public record ClimateEntry(BTWGBiome biome, float weight) {}

    public static Optional<Climate> getClimate(int id) {
        return climateBiomeMap.keySet().stream()
                .filter(climate -> climate.id == id)
                .findFirst();
    }
}