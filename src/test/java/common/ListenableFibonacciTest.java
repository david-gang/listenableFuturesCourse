package common;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListenableFibonacciTest {
    @Test
    void myFirstTest() throws ExecutionException, InterruptedException {
        ListenableFibonacci fib = new ListenableFibonacci();

        assertEquals(0,(long)fib.calculate(0).get());
        assertEquals(1,(long)fib.calculate(1).get());
        assertEquals(21,(long)fib.calculate(8).get());
        assertEquals(89,(long)fib.calculate(11).get());
        assertEquals(12586269025L, (long)fib.calculate(50).get());
    }
}
