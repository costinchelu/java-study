package ocp;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UsingConcurrencyApi {
    public static void main(String[] args) {

    }
}

class RewritingTheCreatingAThreadExample {

    public static void main(String[] args) {

        Runnable printInventory = () -> System.out.println("Printing zoo inventory");

        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record: " + i);
            }
        };

//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // starting with Java 19 ExecutorService implements AutoCloseable
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            System.out.println("Begin");
            executorService.execute(printInventory);
            executorService.execute(printRecords);
            executorService.execute(printInventory);
            System.out.println("End");
        }
//        finally {
//            executorService.shutdown();
//        }
    }
}

class RewritingTheCreatingAThreadExample2 {

    public static void main(String[] args) {

        Runnable printInventory = () -> System.out.println("Printing zoo inventory");

        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record: " + i);
            }
        };

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            System.out.println("Begin");
            executorService.submit(printInventory);
            Future<?> submit = executorService.submit(printRecords);
            executorService.submit(printInventory);
            System.out.println("End");
        }

    }
}

class FutureGetWithRunnable {

    private static int counter = 0;

    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 1_000_000; i++) counter++;
            });

            result.get(1, TimeUnit.NANOSECONDS); // Returns null for Runnable
            System.out.println("Reached!");
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Not reached in time");
        }
    }
}

class FutureGetWithCallable {

    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<Integer> result = service.submit(() -> 30 + 11);
            Integer i = result.get(1, TimeUnit.NANOSECONDS);
            System.out.println("Reached! Result is " + i);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Not reached in time");
        }
    }
}

