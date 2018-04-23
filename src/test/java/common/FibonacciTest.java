package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {
    @Test
    void myFirstTest() {
        Fibonacci fib = new Fibonacci();

        assertEquals(0,fib.calculate(0));
        assertEquals(1,fib.calculate(1));
        assertEquals(21,fib.calculate(8));
    }
}
