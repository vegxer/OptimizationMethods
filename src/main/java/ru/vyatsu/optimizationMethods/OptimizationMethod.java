package ru.vyatsu.optimizationMethods;

import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public abstract class OptimizationMethod {
    protected final Function<Double, Double> function;

    public OptimizationMethod(@NotNull Function<Double, Double> function) {
        this.function = function;
    }

    protected boolean validateSplitNumber(int splitNumber) {
        return splitNumber > 1;
    }

    protected boolean validateBorders(double start, double end) {
        return end >= start;
    }

    protected boolean validateAccuracy(double eps) {
        return eps > 0;
    }

    protected Point2D.Double findMin(double start, double end, double eps, int splitNumber) throws ExtremumNotFoundException {
        if (!validateSplitNumber(splitNumber)) {
            throw new IllegalArgumentException("Количество разбиений должно быть больше 1");
        }

        double h = (end - start) / splitNumber;
        while (Precision.round(h, 12) >= eps) {
            Point2D.Double min = findMin(start, end, h);
            start = Precision.round(min.x - h, 12);
            end = Precision.round(min.x + h, 12);
            h = (end - start) / splitNumber;
        }

        return findMin(start, end, h);
    }

    public abstract Point2D.Double findMin(double start, double end, double eps) throws ExtremumNotFoundException;
}
