package fibThreads;

import common.Fibonacci;

import java.time.LocalTime;

public class OneThreaded {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        for (int i=0;i<47;i++) {
            System.out.println(LocalTime.now()+" "+i+" "+fib.calculate(i));
        }

    }

}
