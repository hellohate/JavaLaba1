import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    void testCountSubstringInMatrix() {
        String[][] matrix = {
                {"hello", "world"},
                {"this", "is"},
                {"a", "test"},
                {"substring", "search"}
        };

        assertEquals(1, CountInSubstringTask5.countSubstringInMatrix(matrix, "hello"), "Перевірка на входження 'hello'");
        assertEquals(2, CountInSubstringTask5.countSubstringInMatrix(matrix, "is"), "Перевірка на входження 'is'");
        assertEquals(0, CountInSubstringTask5.countSubstringInMatrix(matrix, "notfound"), "Перевірка на відсутність підрядка");
        assertEquals(1, CountInSubstringTask5.countSubstringInMatrix(matrix, "sub"), "Перевірка часткового входження 'sub'");
    }

    @Test
    void testEmptySubstring() {
        String[][] matrix = {
                {"hello", "world"}
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CountInSubstringTask5.countSubstringInMatrix(matrix, "");
        });
        assertEquals("Матриця або підрядок не можуть бути null або порожніми.", thrown.getMessage());
    }

    @Test
    void testNullMatrix() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CountInSubstringTask5.countSubstringInMatrix(null, "test");
        });
        assertEquals("Матриця або підрядок не можуть бути null або порожніми.", thrown.getMessage());
    }

    @Test
    void testNullSubstring() {
        String[][] matrix = {
                {"hello", "world"}
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            CountInSubstringTask5.countSubstringInMatrix(matrix, null);
        });
        assertEquals("Матриця або підрядок не можуть бути null або порожніми.", thrown.getMessage());
    }
}
