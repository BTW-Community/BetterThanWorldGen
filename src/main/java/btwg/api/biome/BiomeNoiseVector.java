package btwg.api.biome;

public record BiomeNoiseVector(
        double temperature,
        double humidity,
        double continentalness,
        double erosion,
        double weirdness
) {
    public BiomeNoiseVector {
        temperature = Math.round(temperature / 0.05) * 0.05;
        humidity = Math.round(humidity / 0.05) * 0.05;
        continentalness = Math.round(continentalness / 0.05) * 0.05;
        erosion = Math.round(erosion / 0.05) * 0.05;
        weirdness = Math.round(weirdness / 0.05) * 0.05;
    }
}
