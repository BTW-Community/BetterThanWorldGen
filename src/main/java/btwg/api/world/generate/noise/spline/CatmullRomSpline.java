package btwg.api.world.generate.noise.spline;

import java.util.Arrays;
import java.util.Comparator;

public final class CatmullRomSpline {
    private final Key[] keys;

    public CatmullRomSpline(Key... keys) {
        if (keys == null || keys.length < 2) throw new IllegalArgumentException("Need at least 2 keys");
        this.keys = Arrays.stream(keys).sorted(Comparator.comparingDouble(Key::x)).toArray(Key[]::new);
    }

    public double eval(double x) {
        // Clamp outside range to avoid kinks at ends (C1 within, C0 at ends by design)
        if (x <= keys[0].x()) return keys[0].y();
        if (x >= keys[keys.length - 1].x()) return keys[keys.length - 1].y();

        int i = locateSegment(x);
        Key k0 = i > 0 ? keys[i - 1] : keys[i];
        Key k1 = keys[i];
        Key k2 = keys[i + 1];
        Key k3 = (i + 2 < keys.length) ? keys[i + 2] : k2;

        double t = (x - k1.x()) / (k2.x() - k1.x());
        t = Math.max(0.0, Math.min(1.0, t));

        // Centripetal parameterization distances to reduce overshoot
        double d01 = Math.sqrt(Math.abs(k1.x() - k0.x()));
        double d12 = Math.sqrt(Math.abs(k2.x() - k1.x()));
        double d23 = Math.sqrt(Math.abs(k3.x() - k2.x()));
        if (d01 < 1e-6) d01 = 1.0;
        if (d12 < 1e-6) d12 = 1.0;
        if (d23 < 1e-6) d23 = 1.0;

        double m1 = (k2.y() - k1.y()) / d12 - (k1.y() - k0.y()) / d01;
        m1 *= d12 / (d01 + d12);

        double m2 = (k3.y() - k2.y()) / d23 - (k2.y() - k1.y()) / d12;
        m2 *= d12 / (d12 + d23);

        // Cubic Hermite basis with computed tangents
        double t2 = t * t;
        double t3 = t2 * t;
        double h00 = 2 * t3 - 3 * t2 + 1;
        double h10 = t3 - 2 * t2 + t;
        double h01 = -2 * t3 + 3 * t2;
        double h11 = t3 - t2;

        double dx = (k2.x() - k1.x());
        return h00 * k1.y() + h10 * m1 * dx + h01 * k2.y() + h11 * m2 * dx;
    }

    private int locateSegment(double x) {
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