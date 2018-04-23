package fibThreads;

import com.google.common.util.concurrent.*;
import common.Fibonacci;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListenableFutures1 {

    public static void main(String[] args)  {
        final Fibonacci fib = new Fibonacci();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        List<ListenableFuture<Long>> results = IntStream.range(0, 47).mapToObj((i) -> getCallable(fib, i)).map(callable -> service.submit(callable)).collect(Collectors.toList());
        ListenableFuture<List<Long>> listListenableFuture = Futures.allAsList(results);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<Long>>() {
                    @Override
                    public void onSuccess(List<Long> result) {
                        System.out.println(result);
                        service.shutdown();

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        service.shutdown();

                    }
                },service);




    }

    private static Callable<Long> getCallable(Fibonacci fib, int j) {
        return () -> fib.calculate(j);
    }
}
