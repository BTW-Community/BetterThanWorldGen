package btwg.api.biome;

import java.util.function.Predicate;

public record BiomeNoiseParameterTarget(
        BiomeNoiseVector target,
        Predicate<BiomeNoiseVector> validator
) {
    public BiomeNoiseParameterTarget(BiomeNoiseVector target) {
        this(target, v -> v.continentalness() >= 0);
    }

    public double distanceSqFromTarget(BiomeNoiseVector vector) {
        return Math.pow(target.temperature() - vector.temperature(), 2) +
                Math.pow(target.humidity() - vector.humidity(), 2) +
                Math.pow(target.continentalness() - vector.continentalness(), 2) +
                Math.pow(target.erosion() - vector.erosion(), 2) +
                Math.pow(target.weirdness() - vector.weirdness(), 2);
    }
}
