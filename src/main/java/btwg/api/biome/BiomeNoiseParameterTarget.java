package btwg.api.biome;

import java.util.function.Predicate;

public record BiomeNoiseParameterTarget(
        BiomeNoiseVector target,
        Predicate<BiomeNoiseParameterTarget> validator
) {}
