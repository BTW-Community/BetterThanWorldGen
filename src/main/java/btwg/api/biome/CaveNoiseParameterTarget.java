package btwg.api.biome;

import btwg.api.world.generate.noise.NoiseProvider;

import java.util.function.BiPredicate;

public record CaveNoiseParameterTarget(
        CaveNoiseVector target
) {
    private static final double TEMPERATURE_BIAS = 15;
    private static final double HUMIDITY_BIAS = 1;

    public double distanceSqFromTarget(CaveNoiseVector vector) {
        return Math.pow(target.temperature() - vector.temperature(), 2) * TEMPERATURE_BIAS +
                Math.pow(target.humidity() - vector.humidity(), 2) * HUMIDITY_BIAS;
    }
}
