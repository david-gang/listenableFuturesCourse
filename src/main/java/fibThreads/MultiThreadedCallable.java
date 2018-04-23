package fibThreads;

import common.Fibonacci;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiThreadedCallable {
    public static void main(String[] args)  {
        final Fibonacci fib = new Fibonacci();
        ExecutorService service = Executors.newCachedThreadPool();
        List<Long> results = IntStream.range(0,47).mapToObj((i)-> getCallable(fib, i)).map(callable -> service.submit(callable))
        .map(future -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        System.out.println(results);

        service.shutdown();

    }

    private static Callable<Long> getCallable(Fibonacci fib, int j) {
        return () -> fib.calculate(j);
    }
}
