package ru.vyatsu.optimizationMethods;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.function.Function;

public abstract class OptimizationMethod {
    private final Function<Double, Double> function;

    public OptimizationMethod(@NotNull Function<Double, Double> function) {
        this.function = function;
    }

    protected boolean validateSplitNumber(int splitNumber) {
        return splitNumber >= 1;
    }

    protected boolean validateBorders(double start, double end) {
        return end >= start;
    }

    public abstract Point findMin(double start, double end, int splitNumber);
}
