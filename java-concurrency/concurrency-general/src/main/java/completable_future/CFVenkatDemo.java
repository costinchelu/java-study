package completable_future;

import java.util.concurrent.CompletableFuture;

public class CFVenkatDemo {

    public static int compute(int n) {
        System.out.println("<><> Compute called ..." + Thread.currentThread());
        if (n <= 0) {
            throw new RuntimeException("Invalid input!");
        }

        try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace(); }

        return n * 2;
    }

    // STATES of the CompletableFuture:
    // pending [transient state],
    // resolved [terminal state],
    // rejected [terminal state]
    // a completable future never really ends
    public static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> compute(n));
    }

    public static CompletableFuture<Integer> createAndApply(int n) {
        var cf = new CompletableFuture<Integer>();
        cf.thenApply(data -> data * n)
          .thenApply(data -> data + n);
        return cf;
    }

    public static void main(String[] args) {
        System.out.println(">>> Started the computation: " + Thread.currentThread());

        // call to create is non-blocking
        // if OK, go to the next then... but if exception, go to the next exceptionally
        System.out.println(create(4)                                                                  // CompletableFuture<Integer>
                .exceptionally(error -> {
                            error.printStackTrace();
//                            return 100;      // by returning a value, all the operations will continue using this value
                            throw new RuntimeException("Cannot recover from this .......");
                })
                .thenApply(response -> {
                            System.out.println("<><> Inside thenApply ... " + Thread.currentThread());
                            return response + 1.0;
                })                                                                                    // CompletableFuture<Double>
                .thenAccept(finalResult -> System.out.println("<><> Final result is " + finalResult)) // CompletableFuture<Void>
                .thenRun(() -> System.out.println("<><> Log some info ..."))                          // CompletableFuture<Void>
                .thenRun(() -> System.out.println("<><> Some post operations ..."))                   // CompletableFuture<Void>
                .exceptionally(error -> {
                            error.printStackTrace();
                            throw new RuntimeException("SORRY!");
                }));

        var cf1 = create(2);
        var cf2 = create(3);

        cf1.thenCombine(cf2, Integer::sum)
                .thenAccept(result -> System.out.println("<><> Combining 2 CF = " + result));


        // if your function return data, use thenApply
        // if your function returns a CF, use thenCompose
        create(2).thenCompose(data -> create(data))
                .thenAccept(result -> System.out.println("<><> Compose = " + result));

        System.out.println("<<< Finished the computation: " + Thread.currentThread());

        // just to leave some time before the result is returned and see the thread pool
        // before the completableFuture will get resolved, the Started the computation is triggered
        try { Thread.sleep(3000); } catch (Exception e) { e.printStackTrace(); }
    }
}
