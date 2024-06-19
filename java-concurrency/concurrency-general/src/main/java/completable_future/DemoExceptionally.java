package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DemoExceptionally {

    public static void main(String[] args) {

        try {
            Integer r = CompletableFuture.supplyAsync(() -> 10 / 2)
                    .thenApplyAsync(result -> result + 5)
                    .thenApplyAsync(result -> result / 0)
                    .exceptionally(ex -> {
                        System.out.println("Error occurred: " + ex.getMessage());
                        return Integer.MIN_VALUE;
                    })
                    .get(1000, TimeUnit.MILLISECONDS);
            System.out.println(r);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("get() threw an exception");
        }
    }
}
