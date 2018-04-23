package fibThreads;

import com.google.common.util.concurrent.*;
import common.Fibonacci;
import common.ListenableFibonacci;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListenableFutures2 {

    public static void main(String[] args)  {
        final ListenableFibonacci fib = new ListenableFibonacci();
        System.out.println(LocalTime.now());
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        List<ListenableFuture<Long>> results = IntStream.range(0, 47).mapToObj((i) -> getFuture(fib, i,service)).collect(Collectors.toList());
        ListenableFuture<List<Long>> listListenableFuture = Futures.allAsList(results);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<Long>>() {
                    @Override
                    public void onSuccess(List<Long> result) {
                        System.out.println(result);
                        System.out.println(LocalTime.now());
                        service.shutdown();

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        service.shutdown();

                    }
                },service);




    }

    private static ListenableFuture<Long> getFuture(ListenableFibonacci fib, int j,ListeningExecutorService service) {
        return Futures.submitAsync(() -> fib.calculate(j),service);
    }
}
