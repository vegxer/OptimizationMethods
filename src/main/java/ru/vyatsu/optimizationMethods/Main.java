package ru.vyatsu.optimizationMethods;

import ru.vyatsu.optimizationMethods.factory.MethodName;
import ru.vyatsu.optimizationMethods.factory.OptimizationMethodFactory;
import ru.vyatsu.optimizationMethods.minimumSearch.OptimizationMethod;

import java.awt.geom.Point2D;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Double, Double> function = x -> x * x + Math.exp(x);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                printMainMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return;
                }

                OptimizationMethod method = OptimizationMethodFactory.getMethod(
                        MethodName.values()[choice - 1], function);

                System.out.print("Введите начало и конец отрезка: ");
                String[] borders = scanner.nextLine().split("\\s");
                double start = Double.parseDouble(borders[0]);
                double end = Double.parseDouble(borders[1]);
                System.out.print("Введите точность: ");
                double accuracy = Double.parseDouble(scanner.nextLine());

                Point2D.Double min;
                printStepType();
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> min = method.findMin(start, end, accuracy);
                    case 2 -> {
                        System.out.print("Введите количество разбиений: ");
                        int num = Integer.parseInt(scanner.nextLine());
                        min = method.findMin(start, end, accuracy, num);
                    }
                    default -> throw new IllegalArgumentException("Необходим аргумент из диапазона от 1 до 2");
                }

                System.out.printf("Минимум: f(%f) = %f%n", min.x, min.y);
            } catch (IllegalArgumentException exception) {
                System.out.println("Во время выполнения возникла ошибка: " + exception.getMessage());
            }
        }
    }

    static void printMainMenu() {
        System.out.println("1 - Метод сканирования");
        System.out.println("2 - Метод половинного деления");
        System.out.println("3 - Метод золотого сечения");
        System.out.println("4 - Метод Фибоначчи");
        System.out.println("0 - Выход из программы");
        System.out.print("Ваш выбор: ");
    }

    static void printStepType() {
        System.out.println("1 - Без переменного шага");
        System.out.println("2 - С переменным шагом");
        System.out.print("Ваш выбор: ");
    }
}
