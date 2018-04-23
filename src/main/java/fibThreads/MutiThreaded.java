package fibThreads;

import common.Fibonacci;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


class FibonacciRunnable{
    public static void main(String[] args) throws InterruptedException {
        final Fibonacci fib = new Fibonacci();
        ExecutorService service = Executors.newCachedThreadPool();
        IntStream.range(0,47).mapToObj((i)-> getRunnable(fib, i)).forEach(runnable -> service.submit(runnable));

        service.shutdown();
        service.awaitTermination(1000, TimeUnit.MINUTES);

    }

    private static Runnable getRunnable(Fibonacci fib, int j) {
        return () -> {
            System.out.println(j + " " + fib.calculate(j)+ " "+ LocalTime.now() );
        };
    }
}
