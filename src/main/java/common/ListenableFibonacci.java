package common;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executors;

public class ListenableFibonacci {
    Fibonacci fib = new Fibonacci();
    static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
    public ListenableFuture<Long> calculate(int n) {
        if(n<42) {
            return Futures.immediateFuture(fib.calculate(n));
        }

        ListenableFuture<Long> prevResult = getCalculate(n-1);
        ListenableFuture<Long> pprevResult = getCalculate(n-2);
        ListenableFuture<List<Long>> listListenableFuture = Futures.allAsList(prevResult, pprevResult);
        return Futures.transform(listListenableFuture, nums-> nums.get(0) + nums.get(1),MoreExecutors.directExecutor());
    }

    private ListenableFuture<Long> getCalculate(int n) {
        return Futures.submitAsync(() -> calculate(n),service);

    }

}
