package howto.callbacks;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CallbacksDemo1 {
}

/**
 * Anytime we pass an interface with a method implementation to another method in Java, we are using the concept of a callback function.
 * <p></p>
 *
 */
class AnonymousClassCallback {

    public static void main(String[] args) {
        performAction(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    public static void performAction(Consumer<String> consumer) {
        System.out.println("Action is being performed...");
        consumer.accept("Callback function executed...");
    }
}

/**
 * We passed the Runnable functional interface in the performAction method.
 * Therefore, we were able to override and execute the run() method after the action from the performAction method was finished.
 */
class LambdaCallback {

    public static void main(String[] args) {
        performAction(() -> System.out.println("Callback function executed..."));
    }

    public static void performAction(Runnable runnable) {
        System.out.println("Action is being performed...");
        runnable.run();
    }
}

class AsynchronousCallback {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Callback executed...");
        AsynchronousCallback asynchronousCallback = new AsynchronousCallback();
        asynchronousCallback.performAsynchronousAction(runnable);
    }

    public void performAsynchronousAction(Runnable runnable) {
        new Thread(() -> {
            System.out.println("Processing Asynchronous Task...");
            runnable.run();
        }).start();
    }
}

class AsynchronousParallelCallback {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Callback executed...");
        AsynchronousParallelCallback asynchronousParallelCallback = new AsynchronousParallelCallback();
        asynchronousParallelCallback.performAsynchronousAction(runnable);
    }

    public void performAsynchronousAction(Runnable runnable) {
        new Thread(() -> {
            System.out.println("Processing Asynchronous Task...");
            new Thread(runnable).start();
        }).start();
    }
}

class CompletableFutureCallback {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Supply Async...\n");
        CompletableFuture<String> execution = completableFuture.thenApply(s -> s + " Callback executed...");
        System.out.println(execution.get());
    }

}