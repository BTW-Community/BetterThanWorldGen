package btwg.api.biome;

public record BiomeNoiseVector(
        double temperature,
        double humidity,
        double continentalness,
        double erosion,
        double weirdness
) {
    public BiomeNoiseVector {
        final double step = 0.1;
        temperature = Math.round(temperature / step) * step;
        humidity = Math.round(humidity / step) * step;
        continentalness = Math.round(continentalness / step) * step;
        erosion = Math.round(erosion / step) * step;
        weirdness = Math.round(weirdness / step) * step;
    }
}
