package virtual_threads;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VenkatDemo {

    private static final int MAX = 10;

    public static void fetch(int index, String path) {
        try {
            System.out.println(index + " before >> " + Thread.currentThread());
            var numberOfLines = Files.lines(Paths.get(path)).count();
            System.out.println(index + " after  >> " + Thread.currentThread());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // it makes no sense to pool virtual threads
    public static void main(String[] args) throws InterruptedException {
        // old way (regular threads):
        // var executorService = Executors.newFixedThreadPool(MAX);

        // using VT
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for (var i = 0; i < MAX; i++) {
                var index = i;
                executorService.submit(() -> fetch(index, "./java-21/src/main/java/virtual_threads/VTPerformanceV2.java"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}