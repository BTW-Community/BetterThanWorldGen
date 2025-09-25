package btwg.api.world.generate.noise.spline;

import java.util.function.DoubleUnaryOperator;

public final class SplineCombiner {
    private final DoubleUnaryOperator[] splines;
    private final double[] weights;

    public SplineCombiner(DoubleUnaryOperator[] splines, double[] weights) {
        if (splines == null || weights == null || splines.length != weights.length || splines.length == 0) {
            throw new IllegalArgumentException("Mismatched sizes");
        }
        this.splines = splines;
        this.weights = weights;
    }

    public double eval(double... inputs) {
        if (inputs.length != splines.length) throw new IllegalArgumentException("inputs length != splines length");
        double sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += weights[i] * splines[i].applyAsDouble(inputs[i]);
        }
        return sum;
    }
}
