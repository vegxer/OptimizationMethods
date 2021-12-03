package ru.vyatsu.optimizationMethods.minimumSearch;

import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public abstract class OptimizationMethod {
    protected final Function<Double, Double> function;

    public OptimizationMethod(@NotNull Function<Double, Double> function) {
        this.function = function;
    }

    protected void validateSplitNumber(int splitNumber) {
        if (splitNumber < 2) {
            throw new IllegalArgumentException("Количество разбиений должно быть больше 1");
        }
    }

    protected void validateBorders(double start, double end) {
        if (end < start) {
            throw new IllegalArgumentException("Начальная точка должна быть не больше конечной");
        }
    }

    protected void validateAccuracy(double eps) {
        if (eps <= 0) {
            throw new IllegalArgumentException("Точность вычислений должна быть больше нуля");
        }
    }

    public Point2D.Double findMin(double start, double end, double eps, int splitNumber) {
        validateSplitNumber(splitNumber);

        double h = (end - start) / splitNumber;
        while (Precision.round(h, 12) >= eps) {
            Point2D.Double min = findMin(start, end, h);
            start = Precision.round(min.x - h, 12);
            end = Precision.round(min.x + h, 12);
            h = (end - start) / splitNumber;
        }

        return findMin(start, end, h);
    }

    public abstract Point2D.Double findMin(double start, double end, double eps);
}
