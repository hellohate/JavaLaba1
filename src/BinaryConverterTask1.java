// O(log n)
import java.util.InputMismatchException;
import java.util.Scanner;

public class BinaryConverterTask1 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть ціле число: ");
            final int number = scanner.nextInt();

            final String binaryRepresentation = convertToBinary(number);
            System.out.println("Двійкове представлення числа: " + binaryRepresentation);

        } catch (InputMismatchException e) {
            System.out.println("Помилка: введено некоректні дані. Будь ласка, введіть ціле число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    static String convertToBinary(final int number) {
        StringBuilder binary = new StringBuilder();
        int n = number;

        if (number < 0) {
            throw new IllegalArgumentException("Число повинно бути невід'ємним.");
        }

        if (n == 0) {
            return "0";
        }

        while (n > 0) {
            binary.append(n % 2);
            n /= 2;
        }

        return binary.reverse().toString();
    }
}
