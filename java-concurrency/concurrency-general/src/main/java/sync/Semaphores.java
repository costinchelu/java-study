package sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static sync.ConcurrentUtils.sleep;
import static sync.ConcurrentUtils.stop;

public class Semaphores {

    /*
    * In addition to locks the Concurrency API also supports counting semaphores.
    * Whereas locks usually grant exclusive access to variables or resources,
    * a semaphore is capable of maintaining whole sets of permits.
    * This is useful in different scenarios where you have to limit the amount concurrent access
    * to certain parts of your application. Here's an example how to limit access
    * to a long running task simulated by sleep(5000):
    * */

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(longRunningTask));

        stop(executor);

        /*
        * The executor can potentially run 10 tasks concurrently but we use a semaphore of size 5,
        * thus limiting concurrent access to 5. It's important to use a try/finally block
        * to properly release the semaphore even in case of exceptions.
        * The semaphores permits access to the actual long running operation simulated by sleep(5000)
        * up to a maximum of 5. Every subsequent call to tryAcquire() elapses the maximum wait timeout
        * of one second, resulting in the appropriate console output that no semaphore could be acquired.
        * */
    }
}
