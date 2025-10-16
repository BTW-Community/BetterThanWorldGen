package btwg.api.biome;

public record CaveNoiseVector(
        double temperature,
        double humidity,
        boolean isDeep,
        double step
) {
    public CaveNoiseVector {
        temperature = Math.round(temperature / step) * step;
        humidity = Math.round(humidity / step) * step;
    }

    public CaveNoiseVector(double temperature, double humidity, boolean isDeep) {
        this(temperature, humidity, isDeep, 0.1);
    }
}
