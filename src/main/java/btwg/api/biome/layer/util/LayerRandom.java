package btwg.api.biome.layer.util;

import net.minecraft.src.GenLayer;

import java.io.Serial;
import java.util.Objects;
import java.util.Random;

/// Delegates Random calls to the GenLayer implementation
public final class LayerRandom extends Random {
    @Serial
    private static final long serialVersionUID = 1L;
    private final GenLayer layer;

    public LayerRandom(GenLayer layer) {
        this.layer = Objects.requireNonNull(layer);
    }

    @Override
    public int nextInt(int bound) {
        return layer.nextInt(bound);
    }

    @Override
    public double nextDouble() {
        // Build a [0,1) double using 53 random bits (like java.util.Random)
        long high = nextBits(26);
        long low  = nextBits(27);
        long bits = (high << 27) | low;
        return bits / (double) (1L << 53);
    }

    @Override
    protected int next(int bits) {
        return (int) nextBits(bits);
    }

    // Assemble 'bits' random bits using power-of-two bounds from the delegated nextInt(bound).
    private long nextBits(int bits) {
        if (bits <= 0) return 0L;
        long result = 0L;
        int filled = 0;
        // Use chunks up to 30 bits to avoid overflow in (1 << take)
        while (filled < bits) {
            int take = Math.min(30, bits - filled);
            int part = layer.nextInt(1 << take); // value in [0, 2^take)
            result = (result << take) | (part & ((1L << take) - 1));
            filled += take;
        }
        return result;
    }
}
