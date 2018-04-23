package fibThreads;
import common.SmartFibonacci;

import java.time.LocalTime;

public class OneThreadedSmart {
    public static void main(String[] args) {
        SmartFibonacci fib = new SmartFibonacci();
        for (int i=0;i<50;i++) {
            System.out.println(LocalTime.now()+" "+i+" "+fib.calculate(i));
        }

    }
}
