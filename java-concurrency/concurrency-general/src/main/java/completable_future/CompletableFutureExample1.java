package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class CompletableFutureExample1 {

    private static ForkJoinPool fjp = new ForkJoinPool(10);

    public static void main(String[] args) throws Exception {
        System.out.println("Main: " + Thread.currentThread());
        CompletableFuture<Integer> future = create();

        // Completable future pipeline:
        // -> data line
        // -> error line
        future.thenApply(data -> data * 1.5)
                .thenApply(data -> data + 2)
                .thenAccept(CompletableFutureExample1::printIt)
                .thenRun(() -> System.out.println("....all done"))
                .thenRun(() -> System.out.println("....not really"))
                .thenRun(() -> System.out.println("....completable future will keep going..."));

        // because cf is not finishing we can use it for logging for example
        // thenAccept() is kinda like forEach() (only that in the case of CF we have only one value, not a stream)
        // thenApply() is kinda like map()
    }

    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(CompletableFutureExample1::compute, fjp);
    }

    public static int compute() {
        System.out.println("Compute: " + Thread.currentThread());
        return 2;
    }

    public static <T extends Number> void printIt(T value) {
        System.out.println("PrintIt: " + Thread.currentThread());
        System.out.println(value);
    }

    public static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
