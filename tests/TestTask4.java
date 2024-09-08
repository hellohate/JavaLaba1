import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {


    @Test
    void testEmptyString() {
        String input = "";
        CharacterCount[] result = CountChTask4.countCharacters(input);

        // Перевіряємо, що масив пустий
        for (CharacterCount cc : result) {
            assertNull(cc);
        }
    }

    @Test
    void testSpecialCharacters() {
        String input = "!!##$$";
        CharacterCount[] result = CountChTask4.countCharacters(input);

        // Перевіряємо символ '!'
        assertEquals('!', result[0].character());
        assertEquals(2, result[0].count());

        // Перевіряємо символ '#'
        assertEquals('#', result[1].character());
        assertEquals(2, result[1].count());

        // Перевіряємо символ '$'
        assertEquals('$', result[2].character());
        assertEquals(2, result[2].count());
    }
}