package btwg.api.world.generate.noise.spline;

public record Key(double x, double y, double t) {
    public Key(double x, double y) {
        this(x, y, Double.NaN);
    }
}