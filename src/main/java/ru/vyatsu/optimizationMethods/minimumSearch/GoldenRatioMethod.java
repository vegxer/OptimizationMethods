package ru.vyatsu.optimizationMethods.minimumSearch;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class GoldenRatioMethod extends OptimizationMethod {
    public GoldenRatioMethod(@NotNull Function<Double, Double> function) {
        super(function);
    }

    @Override
    public Point2D.Double findMin(double start, double end, double eps) {
        validateBorders(start, end);
        validateAccuracy(eps);

        double t = (1 + Math.sqrt(5)) / 2;
        double x1 = end - (end - start) / t;
        double x2 = start + (end - start) / t;

        while ((end - start) / 2 > eps) {
            if (function.apply(x1) > function.apply(x2)) {
                start = x1;
                x1 = x2;
                x2 = end - (x1 - start);
            } else {
                end = x2;
                x2 = x1;
                x1 = start + end - x2;
            }
        }

        return new Point2D.Double((start + end) / 2, function.apply((start + end) / 2));
    }
}
