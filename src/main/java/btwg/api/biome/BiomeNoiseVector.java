package btwg.api.biome;

public record BiomeNoiseVector(
        double temperature,
        double humidity,
        double continentalness,
        double erosion,
        double weirdness,
        double step
) {
    public BiomeNoiseVector {
        temperature = Math.round(temperature / step) * step;
        humidity = Math.round(humidity / step) * step;
        continentalness = Math.round(continentalness / step) * step;
        erosion = Math.round(erosion / step) * step;
        weirdness = Math.round(weirdness / step) * step;
    }

    public BiomeNoiseVector(double temperature, double humidity, double continentalness, double erosion, double weirdness) {
        this(temperature, humidity, continentalness, erosion, weirdness, 0.1);
    }
}
