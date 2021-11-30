package ru.vyatsu.optimizationMethods;

import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class ScanningMethod extends OptimizationMethod {
    public ScanningMethod(@NotNull Function<Double, Double> function) {
        super(function);
    }

    @Override
    public Point2D.Double findMin(double start, double end, double eps, int splitNumber) throws ExtremumNotFoundException {
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

    @Override
    public Point2D.Double findMin(double start, double end, double eps) throws ExtremumNotFoundException {
        if (!validateBorders(start, end)) {
            throw new IllegalArgumentException("Начальная точка должна быть не больше конечной");
        }
        if (!validateAccuracy(eps)) {
            throw new IllegalArgumentException("Точность вычислений должна быть больше нуля");
        }

        for (double x = start; Precision.round(x + eps, 12) <= end; x = Precision.round(x + eps, 12)) {
            if (function.apply(x) < function.apply(Precision.round(x + eps, 12)))
                return new Point2D.Double(x, function.apply(x));
        }

        throw new ExtremumNotFoundException(String.format("Экстремум на отрезке [%f; %f] не найден", start, end));
    }
}
