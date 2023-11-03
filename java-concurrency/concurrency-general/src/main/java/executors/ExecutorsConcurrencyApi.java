package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsConcurrencyApi {

    /*
    * The Concurrency API introduces the concept of an ExecutorService
    * as a higher level replacement for working with threads directly.
    * Executors are capable of running asynchronous tasks and typically
    * manage a pool of threads, so we don't have to create new threads manually.
    * All threads of the internal pool will be reused under the hood for
    * relevant tasks, so we can run as many concurrent tasks as we want throughout
    *  the life-cycle of our application with a single executor service.
    * */

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
           String threadName = Thread.currentThread().getName();
            System.out.println("Thread: " + threadName);
        });

        try {
            System.out.println("Attempt to shutdown executor...");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            // after a maximum of 5 seconds, the executor finally shuts down by interrupting all running tasks
        } catch (InterruptedException e) {
            System.err.println("Task was interrupted");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("Executor was not terminated. Cancelling non-finished tasks...");
            }
            executorService.shutdownNow();
            System.out.println("Shutdown finished");
        }
    }
}
