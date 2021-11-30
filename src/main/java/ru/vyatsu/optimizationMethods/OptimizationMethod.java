package ru.vyatsu.optimizationMethods;

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

    public abstract Point2D.Double findMin(double start, double end, double eps, int splitNumber) throws ExtremumNotFoundException;
    public abstract Point2D.Double findMin(double start, double end, double eps) throws ExtremumNotFoundException;
}
