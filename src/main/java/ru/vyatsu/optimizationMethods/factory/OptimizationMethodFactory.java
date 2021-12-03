package ru.vyatsu.optimizationMethods.factory;

import ru.vyatsu.optimizationMethods.minimumSearch.*;

import java.util.function.Function;

public class OptimizationMethodFactory {

    public static OptimizationMethod getMethod(MethodName methodName, Function<Double, Double> function) {
        return switch (methodName) {
            case SCAN -> new ScanningMethod(function);
            case HALF_DIVISION -> new HalfDivisionMethod(function);
            case GOLDEN_RATIO -> new GoldenRatioMethod(function);
            case FIBONACCI -> new FibonacciMethod(function);
        };
    }
}
