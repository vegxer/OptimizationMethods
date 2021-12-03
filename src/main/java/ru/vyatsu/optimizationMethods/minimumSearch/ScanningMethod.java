package ru.vyatsu.optimizationMethods.minimumSearch;

import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class ScanningMethod extends OptimizationMethod {
    public ScanningMethod(@NotNull Function<Double, Double> function) {
        super(function);
    }

    @Override
    public Point2D.Double findMin(double start, double end, double eps) {
        validateBorders(start, end);
        validateAccuracy(eps);

        for (double x = start; Precision.round(x + eps, 12) <= end; x = Precision.round(x + eps, 12)) {
            if (function.apply(x) < function.apply(Precision.round(x + eps, 12)))
                return new Point2D.Double(x, function.apply(x));
        }

        return new Point2D.Double(end, function.apply(end));
    }
}
