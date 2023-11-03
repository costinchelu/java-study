package completable_future;


import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample4 {

    private static int compute() {
        return 2;
    }

    private static int transform(int number) {
        System.out.println("transform called...");
        if (Math.random() > 0.5) {
            System.out.println("transform failed!");
            throw new RuntimeException("Something went wrong.");
        }
        System.out.println("transform successful...");
        return number * 10;
    }

    private static <T> T handleException(Throwable throwable) {
        System.out.println("handling exception... " + throwable.getMessage());
        return null;
    }

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(CompletableFutureExample4::compute);
    }

    private static void simpleCompletableFutureExample() {
        var cf = create();
        System.out.println(cf);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cf);
    }

    private static void pipelineExample() {
        create()
                .thenApply(CompletableFutureExample4::transform)
                .thenApply(data -> data + 1)
                .thenAccept(System.out::println)
                .exceptionally(CompletableFutureExample4::handleException);
    }

    public static void main(String[] args) {
        simpleCompletableFutureExample();

        pipelineExample();

    }
}