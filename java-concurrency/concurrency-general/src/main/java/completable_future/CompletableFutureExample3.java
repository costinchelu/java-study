package completable_future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample3 {

    public static void main(String[] args) {

        // combine behaves like reduce
//        create(2)
//                .thenCombine(create(3), Integer::sum)
//                .thenAccept(System.out::println);

        // behaves like flatMap
        create(2)
                .thenCompose(CompletableFutureExample3::create)
                .thenAccept(System.out::println);
    }

    public static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> n);
    }
}
