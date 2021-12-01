package ru.vyatsu.optimizationMethods;

import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class HalfDivisionMethod extends OptimizationMethod {
    public HalfDivisionMethod(@NotNull Function<Double, Double> function) {
        super(function);
    }

    @Override
    public Point2D.Double findMin(double start, double end, double eps) {
        double mid = (start + end) / 2;
        double left = mid - eps / 2;
        double right = mid + eps / 2;

        while (Precision.round(end - start, 12) / 2 > eps) {
            if (function.apply(left) < function.apply(right)) {
                end = right;
            } else {
                start = left;
            }

            mid = (start + end) / 2;
            left = mid - eps / 2;
            right = mid + eps / 2;
        }

        return new Point2D.Double((start + end) / 2, function.apply((start + end) / 2));
    }
}
