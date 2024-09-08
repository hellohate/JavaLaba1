import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BinaryConverterTask1Test {

    @Test
    void testConvertToBinary() {
        assertEquals("0", BinaryConverterTask1.convertToBinary(0), "Перевірка для числа 0");
        assertEquals("1", BinaryConverterTask1.convertToBinary(1), "Перевірка для числа 1");
        assertEquals("10", BinaryConverterTask1.convertToBinary(2), "Перевірка для числа 2");
        assertEquals("1010", BinaryConverterTask1.convertToBinary(10), "Перевірка для числа 10");
        assertEquals("1111", BinaryConverterTask1.convertToBinary(15), "Перевірка для числа 15");
        assertEquals("1100100", BinaryConverterTask1.convertToBinary(100), "Перевірка для числа 100");
    }

    @Test
    void testNegativeNumber() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> BinaryConverterTask1.convertToBinary(-5));
        assertEquals("Число повинно бути невід'ємним.", thrown.getMessage());
    }

    @Test
    void testInvalidInput() {
        InputMismatchException thrown = assertThrows(InputMismatchException.class, () -> {
            Scanner scanner = new Scanner("text");
            scanner.nextInt();
        });
        assertNotNull(thrown);
    }
}
