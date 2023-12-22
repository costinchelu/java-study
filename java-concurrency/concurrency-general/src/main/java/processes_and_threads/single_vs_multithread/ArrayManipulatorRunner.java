package processes_and_threads.single_vs_multithread;

import lombok.RequiredArgsConstructor;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

/**
 *  <p>
 *      Single-threaded implementations outperforms multithreaded ones in situations like when the problem is simple,
 *      or when the data size is quite manageable.
 *  </p>
 *  <p>
 *      However, multithreaded implementations outperforms single-threaded ones in situations like
 *      when the load becomes enormous, or when the problem is not trivial, or when the process handled
 *      by a thread is a lengthy operation, so it would be faster to execute  concurrently/in parallel.
 *  </p>
 *
 */
class SingleThread {
    public static void main(String... args) {
        final int[] numbersList = IntStream.range(0, 1000000).toArray();

        final long startTime = System.nanoTime();
        for (int i = 0; i < numbersList.length; i++) {
            numbersList[i] = doSomething(numbersList[i]);
        }
        final long endTime = System.nanoTime();

        System.out.println("total execution time: " + (endTime - startTime) + " ns");
    }

    private static int doSomething(final int number) {
        return number * 2;
    }
}

class MultiThread {
    public static void main(String... args) {
        final int[] numbersList = IntStream.range(0, 1000000).toArray();

        final List<Thread> threads = new ArrayList<>();
        final int batchSize = 100;
        int index = 0;

        final long startTime = System.nanoTime();
        // breaking down the list and distributing work among threads
        while (index < numbersList.length) {
            // create the threads
            threads.add(new Thread(new ArrayManipulatorRunner(index, index + batchSize, numbersList)));
            index += batchSize;
        }

        // start the threads
        for (Thread thread : threads) {
            thread.start();
        }

        // main waits for all the threads to complete execution
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        final long endTime = System.nanoTime();

        System.out.println("total execution time: " + (endTime - startTime) + " ns");
        System.out.println("Thread created: " + threads.size());
    }
}

@RequiredArgsConstructor
public class ArrayManipulatorRunner implements Runnable {

    private final int startIndex;
    private final int size;
    private final int[] numbersList;

    @Override
    public void run() {
        for (int i = startIndex; i < size; i++) {
            numbersList[i] = doSomething(numbersList[i]);
        }
    }
    private int doSomething(final int number) {
        return number * 2;
    }

}
