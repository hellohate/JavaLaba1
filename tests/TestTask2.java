import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTask2Test {

    @Test
    void testAdditionWithSpaces() {
        assertEquals(9.0, ExpressionParserTask2.evaluateExpression("4+5=?"), 0.001, "Перевірка додавання з пробілами та =?");
        assertEquals(6.0, ExpressionParserTask2.evaluateExpression("2 + 4"), 0.001, "Перевірка додавання без пробілів");
        assertEquals(5.0, ExpressionParserTask2.evaluateExpression("   2 +  3  = ?"), 0.001, "Перевірка додавання з нерівномірними пробілами");
    }

    @Test
    void testSubtraction() {
        assertEquals(1.0, ExpressionParserTask2.evaluateExpression("4 - 3 = ?"), 0.001, "Перевірка віднімання з =?");
        assertEquals(-1.0, ExpressionParserTask2.evaluateExpression("  4 -  5  =  "), 0.001, "Перевірка віднімання з пробілами");
    }

    @Test
    void testMultiplication() {
        assertEquals(20.0, ExpressionParserTask2.evaluateExpression("  4   *  5 = "), 0.001, "Перевірка множення з пробілами");
        assertEquals(12.0, ExpressionParserTask2.evaluateExpression(" 3*4 "), 0.001, "Перевірка множення без пробілів");
    }

    @Test
    void testDivision() {
        assertEquals(2.0, ExpressionParserTask2.evaluateExpression(" 10 / 5 =?"), 0.001, "Перевірка ділення з =?");
        assertEquals(3.0, ExpressionParserTask2.evaluateExpression(" 9 / 3 "), 0.001, "Перевірка ділення без пробілів");
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException thrown = assertThrows(ArithmeticException.class, () -> {
            ExpressionParserTask2.evaluateExpression("10 / 0 = ?");
        });
        assertEquals("Помилка: неможливо виконати математичну операцію (ділення на нуль).", thrown.getMessage());
    }

    @Test
    void testInvalidExpression() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            ExpressionParserTask2.evaluateExpression("10 + = ?");
        });
        assertEquals("Помилка: введено некоректні числа.", thrown.getMessage());
    }
}
