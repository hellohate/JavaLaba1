// O(1)

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverterTask6 {

    private static final Map<String, Double> rates = new HashMap<>();

    static {
        rates.put("UAHtoUSD", 0.027);
        rates.put("USDtoUAH", 37.0);
        rates.put("UAHtoEUR", 0.025);
        rates.put("EURtoUAH", 40.0);
        rates.put("UAHtoCAD", 0.036);
        rates.put("CADtoUAH", 28.0);
        rates.put("USDtoEUR", 0.93);
        rates.put("EURtoUSD", 1.08);
        rates.put("USDtoCAD", 1.34);
        rates.put("CADtoUSD", 0.75);
        rates.put("EURtoCAD", 1.45);
        rates.put("CADtoEUR", 0.69);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть запит конвертації (наприклад, '100 UAH into USD'): ");
            String input = scanner.nextLine();

            double result = convertCurrency(input);
            System.out.println("Результат конвертації: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    static double convertCurrency(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Невірний формат запиту.");
        }

        String[] parts = input.split(" ");
        if (parts.length != 4 || !parts[2].equalsIgnoreCase("into")) {
            throw new IllegalArgumentException("Невірний формат. Використовуйте формат '100 UAH into USD'.");
        }

        double amount;
        try {
            amount = Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Перша частина повинна бути числом.");
        }

        String fromCurrency = parts[1].toUpperCase();
        String toCurrency = parts[3].toUpperCase();

        String key = fromCurrency + "to" + toCurrency;
        Double rate = rates.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("Невідома конвертація між валютами " + fromCurrency + " і " + toCurrency);
        }

        return amount * rate;
    }
}
