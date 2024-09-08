// O(n)

import java.util.Scanner;

public class CountChTask4 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть рядок: ");
            final String input = scanner.nextLine();

            CharacterCount[] result = countCharacters(input);
            printCharacterCount(result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    static CharacterCount[] countCharacters(final String input) {
        final int[] charCounts = new int[256];

        for (int i = 0; i < input.length(); i++) {
            charCounts[input.charAt(i)]++;
        }

        CharacterCount[] result = new CharacterCount[256];
        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (charCounts[i] > 0) {
                result[index++] = new CharacterCount((char) i, charCounts[i]);
            }
        }

        return result;
    }

    private static void printCharacterCount(final CharacterCount[] characterCounts) {
        for (CharacterCount cc : characterCounts) {
            if (cc != null) {
                System.out.println(cc.character() + ": " + cc.count());
            }
        }
    }
}

record CharacterCount(char character, int count) {
}
