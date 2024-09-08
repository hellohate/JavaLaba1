import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTask3Test {

    @Test
    void testFizzBuzzNormal() {
        assertEquals("Buzz", FizzBuzzTask3.evaluateFizzBuzz(5), "Число кратне 5");
        assertEquals("FizzBuzz", FizzBuzzTask3.evaluateFizzBuzz(15), "Число кратне 3 і 5");
        assertEquals("Fizz", FizzBuzzTask3.evaluateFizzBuzz(3), "Число кратне 3");
        assertEquals("7", FizzBuzzTask3.evaluateFizzBuzz(7), "Число не кратне ні 3, ні 5");
    }
}