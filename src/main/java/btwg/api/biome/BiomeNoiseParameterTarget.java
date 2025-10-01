package btwg.api.biome;

import btwg.api.world.generate.noise.NoiseProvider;

import java.util.function.BiPredicate;

public record BiomeNoiseParameterTarget(
        BiomeNoiseVector target,
        BiPredicate<BiomeNoiseVector, Integer> validator
) {
    private static final int CLIMATE_BIAS = 2;

    public BiomeNoiseParameterTarget(BiomeNoiseVector target) {
        // TODO: Make sea level check dynamic
        this(target, (v, h) -> h >= NoiseProvider.SEA_LEVEL);
    }

    public double distanceSqFromTarget(BiomeNoiseVector vector) {
        return Math.pow(target.temperature() - vector.temperature(), 2) * CLIMATE_BIAS +
                Math.pow(target.humidity() - vector.humidity(), 2) * CLIMATE_BIAS +
                Math.pow(target.continentalness() - vector.continentalness(), 2) +
                Math.pow(target.erosion() - vector.erosion(), 2) +
                Math.pow(target.weirdness() - vector.weirdness(), 2);
    }
}
