package btwg.api.biome;

import btwg.api.world.generate.noise.NoiseProvider;

import java.util.function.BiPredicate;

public record BiomeNoiseParameterTarget(
        BiomeNoiseVector target,
        BiPredicate<BiomeNoiseVector, Integer> validator
) {
    private static final double TEMPERATURE_BIAS = 15;
    private static final double HUMIDITY_BIAS = 3;
    private static final double SEA_LEVEL_BIAS = 8;

    public static final BiPredicate<BiomeNoiseVector, Integer> DEFAULT_PREDICATE = (v, h) -> h >= NoiseProvider.SEA_LEVEL * 0.8;
    public static final BiPredicate<BiomeNoiseVector, Integer> DEFAULT_NON_SNOWY = (v, h) -> DEFAULT_PREDICATE.test(v, h) && v.temperature() > 0.2;

    public BiomeNoiseParameterTarget(BiomeNoiseVector target) {
        // TODO: Make sea level check dynamic
        this(target, DEFAULT_NON_SNOWY);
    }

    public double distanceSqFromTarget(BiomeNoiseVector vector) {
        return Math.pow(target.temperature() - vector.temperature(), 2) * TEMPERATURE_BIAS +
                Math.pow(target.humidity() - vector.humidity(), 2) * HUMIDITY_BIAS +
                Math.pow(target.continentalness() - vector.continentalness(), 2) * SEA_LEVEL_BIAS +
                Math.pow(target.erosion() - vector.erosion(), 2) +
                Math.pow(target.weirdness() - vector.weirdness(), 2);
    }
}
