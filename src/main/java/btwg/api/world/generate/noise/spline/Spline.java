package btwg.api.world.generate.noise.spline;

import java.util.Arrays;
import java.util.Comparator;

public sealed abstract class Spline permits HermiteSpline, CatmullRomSpline {
    protected final Key[] keys;

    protected Spline(Key[] keys) {
        if (keys == null || keys.length < 2) throw new IllegalArgumentException("Need at least 2 keys");
        this.keys = Arrays.stream(keys).sorted(Comparator.comparingDouble(Key::x)).toArray(Key[]::new);
    }

    public static Spline of(Key... keys) {
        if (Arrays.stream(keys).allMatch(key -> Double.isNaN(key.t()))) {
            return new CatmullRomSpline(keys);
        }
        else if (Arrays.stream(keys).noneMatch(key -> Double.isNaN(key.t()))) {
            return new HermiteSpline(keys);
        }
        else {
            throw new IllegalArgumentException("Mixed tangent types: all keys must have undefined tangents or all keys must have tangents defined");
        }
    }

    public abstract double eval(double x);

    protected int locateSegment(double x) {
        int lo = 0, hi = keys.length - 2;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (x < keys[mid + 1].x()) {
                if (x >= keys[mid].x()) return mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return Math.max(0, Math.min(keys.length - 2, lo));
    }
}
