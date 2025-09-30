package btwg.api.biome;

public record BiomeNoiseVector(
        double temperature,
        double humidity,
        double continentalness,
        double erosion,
        double weirdness
) {}
