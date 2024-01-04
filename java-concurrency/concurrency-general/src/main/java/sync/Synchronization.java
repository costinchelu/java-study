package sync;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static sync.ConcurrentUtils.sleep;
import static sync.ConcurrentUtils.stop;


public class Synchronization {

    public static void main(String[] args) {

        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executorService.submit(counter::incrementSync));

        sleep(2);
        System.out.println(counter.getCount());

        stop(executorService);

        /*
        * Internally Java uses a so-called monitor also known as monitor lock or intrinsic lock
        * in order to manage synchronization.
        * This monitor is bound to an object, e.g. when using synchronized methods each method
        * share the same monitor of the corresponding object.

        All implicit monitors implement the reentrant characteristics.
        * Reentrant means that locks are bound to the current thread.
        * A thread can safely acquire the same lock multiple times without running into deadlocks
        * (e.g. a synchronized method calls another synchronized method on the same object).
        * */
    }
}

@Getter
class Counter {

    private int count = 0;

    public void incrementSync() {
        synchronized (this) {
            count = count + 1;
        }
    }
}
