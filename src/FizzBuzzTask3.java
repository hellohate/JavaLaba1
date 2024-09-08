// O(n)

public class FizzBuzzTask3 {

    public static void main(String[] args) {
        try {
            printFizzBuzz();
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }

    private static void printFizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(evaluateFizzBuzz(i));
        }
    }

    static String evaluateFizzBuzz(final int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }
}
