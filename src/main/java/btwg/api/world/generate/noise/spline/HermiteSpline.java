package btwg.api.world.generate.noise.spline;

import java.util.Arrays;
import java.util.Comparator;

public final class HermiteSpline {
    private final Key[] keys;

    public HermiteSpline(Key... keys) {
        if (keys == null || keys.length < 2) throw new IllegalArgumentException("Need at least 2 keys");
        for (Key k : keys) {
            if (Double.isNaN(k.t())) throw new IllegalArgumentException("Hermite requires tangents");
        }
        this.keys = Arrays.stream(keys).sorted(Comparator.comparingDouble(Key::x)).toArray(Key[]::new);
    }

    public double eval(double x) {
        if (x <= keys[0].x()) return keys[0].y();
        if (x >= keys[keys.length - 1].x()) return keys[keys.length - 1].y();

        int i = locateSegment(x);
        Key k1 = keys[i];
        Key k2 = keys[i + 1];

        double t = (x - k1.x()) / (k2.x() - k1.x());
        t = Math.max(0.0, Math.min(1.0, t));
        double dx = (k2.x() - k1.x());

        double t2 = t * t;
        double t3 = t2 * t;
        double h00 = 2 * t3 - 3 * t2 + 1;
        double h10 = t3 - 2 * t2 + t;
        double h01 = -2 * t3 + 3 * t2;
        double h11 = t3 - t2;

        return h00 * k1.y() + h10 * k1.t() * dx + h01 * k2.y() + h11 * k2.t() * dx;
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
