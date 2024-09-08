/*
Очищення рядка (O(n)).
Пошук оператора в рядку (O(n)).
Парсинг чисел (O(m)).
Виклик calculate (O(1)).
*/

import java.util.Scanner;

public class ExpressionParserTask2 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть вираз (наприклад, 2 + 4 або 4+5=?): ");
            final String expression = scanner.nextLine().replaceAll("\\s+", "");

            final double result = evaluateExpression(expression);
            System.out.println("Результат: " + result);

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Помилка: неможливо виконати математичну операцію (ділення на нуль).");
        }
    }

    static double evaluateExpression(final String expression) {
        String cleanExpression = expression.replace("=?", "").replace("=", "").replace("?", "");

        int operatorIndex = findOperatorIndex(cleanExpression);
        if (operatorIndex == -1) {
            throw new IllegalArgumentException("Невірний формат виразу. Потрібно використовувати оператори: +, -, *, /.");
        }

        final String number1Str = cleanExpression.substring(0, operatorIndex);
        final String operator = String.valueOf(cleanExpression.charAt(operatorIndex));
        final String number2Str = cleanExpression.substring(operatorIndex + 1);

        final double number1;
        final double number2;

        try {
            number1 = Double.parseDouble(number1Str);
            number2 = Double.parseDouble(number2Str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Помилка: введено некоректні числа.");
        }

        return calculate(number1, number2, operator);
    }


    private static int findOperatorIndex(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                return i;
            }
        }
        return -1;
    }

    private static double calculate(final double number1, final double number2, final String operator) {
        return switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> {
                if (number2 == 0) {
                    throw new ArithmeticException("Помилка: неможливо виконати математичну операцію (ділення на нуль).");
                }
                yield number1 / number2;
            }
            default -> throw new IllegalArgumentException("Помилка: невідомий оператор.");
        };
    }
}
