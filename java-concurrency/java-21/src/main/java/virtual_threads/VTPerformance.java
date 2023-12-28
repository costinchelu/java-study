package virtual_threads;

import java.util.List;
import java.util.stream.IntStream;

public class VTPerformance {

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = IntStream.range(0, 10).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
            if (i == 0) {
                System.out.println(Thread.currentThread());
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if (i == 0) {
                System.out.println(Thread.currentThread());
                // can jump from one platform thread to another
            }
        })).toList();

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
