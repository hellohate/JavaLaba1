// O(n * m * p)

import java.util.Scanner;

public class CountInSubstringTask5 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[][] matrix = {
                    {"hello", "world"},
                    {"this", "is"},
                    {"a", "test"},
                    {"substring", "search"}
            };

            System.out.print("Введіть підрядок для пошуку: ");
            final String substring = scanner.nextLine();

            int count = countSubstringInMatrix(matrix, substring);
            System.out.println("Кількість входжень підрядка: " + count);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    static int countSubstringInMatrix(final String[][] matrix, final String substring) {
        if (matrix == null || substring == null || substring.isEmpty()) {
            throw new IllegalArgumentException("Матриця або підрядок не можуть бути null або порожніми.");
        }

        int count = 0;
        for (String[] row : matrix) {
            for (String element : row) {
                count += countOccurrencesInString(element, substring);
            }
        }
        return count;
    }

    static int countOccurrencesInString(final String str, final String substring) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }
}
