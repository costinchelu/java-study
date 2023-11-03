package callables_and_futures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallablesAndFutures {
    /*
    * In addition to Runnable executors support another kind of task named Callable.
    * Callables are functional interfaces just like runnables but instead of being void they return a value.
    * */

    public static void main(String[] args) throws InterruptedException {

        //example1();
        example2();

    }

    private static void example1() {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task interrupted ", e);
            }
        };

        // Callables can be submitted to executor services just like runnables.
        // But what about the callables result? Since submit() doesn't wait until the task completes,
        // the executor service cannot return the result of the callable directly.
        // Instead the executor returns a special result of type Future which can be used to retrieve
        // the actual result at a later point in time.

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);

        System.out.println("Future done? " + future.isDone());

        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Calling the method get() blocks the current thread and waits until the callable completes
        // before returning the actual result 123.
        // Futures are tightly coupled to the underlying executor service.
        // Keep in mind that every non-terminated future will throw exceptions if you shutdown the executor.

        System.out.println("Future done? " + future.isDone());
        System.out.println("Result (retrieved from the future): " + result);
    }

    private static void example2() throws InterruptedException {
        // Executors support batch submitting of multiple callables at once via invokeAll().
        // This method accepts a collection of callables and returns a list of futures.

        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callableList = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3"
        );

        executorService.invokeAll(callableList)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);

        // process all futures returned by the invocation of invokeAll
    }

}
