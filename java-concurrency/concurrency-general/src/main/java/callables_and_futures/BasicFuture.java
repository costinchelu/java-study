package callables_and_futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicFuture {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> 1.0 + 1);
        System.out.println("Other operations...");

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (ExecutionException ee) {
// the computation threw an exception
        } catch (InterruptedException ie) {
// the current thread was interrupted while waiting
        } catch (TimeoutException te) {
// the timeout expired before the Future completion
        }
    }
}
