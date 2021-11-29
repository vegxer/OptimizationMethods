package ru.vyatsu.optimizationMethods;

import java.awt.geom.Point2D;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Double, Double> function = x -> x * x + Math.exp(x);
        Scanner scanner = new Scanner(System.in);
        OptimizationMethod method = null;
        while (true) {
            try {
                System.out.println("1 - Метод сканирования");
                System.out.println("0 - Выход из программы");
                System.out.print("Ваш выбор: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> method = new ScanningMethod(function);
                    case 0 -> System.exit(0);
                    default -> throw new IllegalArgumentException("Необходим аргумент из диапазона от 0 до 1");
                }

                System.out.print("Введите начало и конец отрезка: ");
                String[] borders = scanner.nextLine().split("\\s");
                double start = Double.parseDouble(borders[0]);
                double end = Double.parseDouble(borders[1]);
                System.out.print("Введите точность: ");
                double accuracy = Double.parseDouble(scanner.nextLine());

                Point2D.Double min;
                switch (choice) {
                    case 1 -> {
                        System.out.println("1 - Без переменного шага");
                        System.out.println("2 - С переменным шагом");
                        System.out.print("Ваш выбор: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1 ->  min = method.findMin(start, end, accuracy);
                            case 2 -> {
                                System.out.print("Введите количество разбиений: ");
                                int num = Integer.parseInt(scanner.nextLine());
                                min = method.findMin(start, end, accuracy, num);
                            }
                            default -> throw new IllegalArgumentException("Необходим аргумент из диапазона от 1 до 2");
                        }
                    }
                    default -> throw new IllegalArgumentException("Необходим аргумент из диапазона от 1 до 2");
                }

                System.out.printf("Минимум: f(%f) = %f%n", min.x, min.y);
            } catch (IllegalArgumentException | ExtremumNotFoundException exception) {
                System.out.println("Во время выполнения возникла ошибка: " + exception.getMessage());
            }
        }
    }
}
