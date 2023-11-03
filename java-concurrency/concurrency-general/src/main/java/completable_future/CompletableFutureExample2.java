package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static completable_future.CompletableFutureExample1.sleep;

public class CompletableFutureExample2 {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        process(future);
        // without a future.complete or future.completeExceptionally we get a
        // live lock (waiting for an event that will never happen)

        sleep(3000);


        //future.complete(2);
        // it resolves the future (goes on the data line)

        //future.completeExceptionally(new RuntimeException("Some exception occurred"));
        // goes on the error line

        //future.completeOnTimeout(10, 4, TimeUnit.SECONDS);
        // setting a timeout for cases when operations take a long time

        future.orTimeout(3, TimeUnit.SECONDS);
        // if task is not finishing in 3 seconds, future will be terminated so that it will return a timeout exception

        sleep(5000);
    }

    public static void process(CompletableFuture<Integer> future) {
        // form the CF pipeline:
        future
                .exceptionally(CompletableFutureExample2::handleExc)
                .thenApply(data -> data * 2)
                .thenApply(data -> data + 2)
                .thenAccept(System.out::println);
    }

    private static int handleExc(Throwable throwable) {
        System.out.println("ERROR: " + throwable);
//        return 100;

        throw new RuntimeException("This is unrecoverable...");
    }

}
