package ru.vyatsu.optimizationMethods.minimumSearch;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class FibonacciMethod extends OptimizationMethod {
    public FibonacciMethod(@NotNull Function<Double, Double> function) {
        super(function);
    }

    @Override
    public Point2D.Double findMin(double start, double end, double eps) {
        validateBorders(start, end);
        validateAccuracy(eps);

        int n = getIterationsNum(start, end, eps);
        double x1 = start + (end - start) * fibonacci(n - 2) / fibonacci(n);
        double x2 = start + (end - start) * fibonacci(n - 1) / fibonacci(n);

        while (n-- > 0) {
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

    private int getIterationsNum(double start, double end, double eps) {
        double l = (end - start) / eps;
        int i;
        for (i = 1; fibonacci(i) < l; ++i);

        return i;
    }

    private int fibonacci(int number) {
        int fib1 = 1;
        int fib2 = 2;
        int fib3 = fib1 + fib2;
        if (number == 1)
            return fib1;
        if (number == 2)
            return fib2;
        if (number == 3)
            return fib3;

        for (int i = 3; i < number; ++i) {
            fib1 = fib2;
            fib2 = fib3;
            fib3 = fib1 + fib2;
        }

        return fib3;
    }
}
