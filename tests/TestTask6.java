import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void testValidConversion() {
        assertEquals(2.7, CurrencyConverterTask6.convertCurrency("100 UAH into USD"), 0.001, "Перевірка конвертації з UAH до USD");
        assertEquals(37.0, CurrencyConverterTask6.convertCurrency("1 USD into UAH"), 0.001, "Перевірка конвертації з USD до UAH");
        assertEquals(25.0, CurrencyConverterTask6.convertCurrency("1000 UAH into EUR"), 0.001, "Перевірка конвертації з UAH до EUR");
        assertEquals(36.0, CurrencyConverterTask6.convertCurrency("1000 UAH into CAD"), 0.001, "Перевірка конвертації з UAH до CAD");
    }

    @Test
    void testInvalidFormat() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverterTask6.convertCurrency("100UAHintoUSD");
        });
        assertEquals("Невірний формат. Використовуйте формат '100 UAH into USD'.", thrown.getMessage());
    }

    @Test
    void testInvalidCurrency() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverterTask6.convertCurrency("100 UAH into JPY");
        });
        assertEquals("Невідома конвертація між валютами UAH і JPY", thrown.getMessage());
    }

    @Test
    void testNonNumericAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverterTask6.convertCurrency("сто UAH into USD");
        });
        assertEquals("Перша частина повинна бути числом.", thrown.getMessage());
    }

    @Test
    void testEmptyInput() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverterTask6  .convertCurrency("");
        });
        assertEquals("Невірний формат запиту.", thrown.getMessage());
    }
}
