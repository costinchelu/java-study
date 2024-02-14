package semaphores_barriers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * We set a barrier of 50 threads to arrive before going to the next phase of execution.
 *
 * Then we create a thread that invokes the method arriveAndAwaitAdvance() on the Phaser instance.
 *
 * It blocks the thread until all the 50 threads won’t arrive to the barrier.
 *
 * Then it goes to the phase-1 and also invokes the method arriveAndAwaitAdvance().
 */
public class Phasers {

    public static void main(String[] args) {

        Phaser phaser = new Phaser(50);

        Runnable r = () -> {
            System.out.println("PHASE 0");
            phaser.arriveAndAwaitAdvance();
            System.out.println("PHASE 1");
            phaser.arriveAndAwaitAdvance();
            System.out.println("PHASE 3");
            phaser.arriveAndDeregister();
        };

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            for (int i = 0; i < 50; i++) {
                executorService.submit(r);
            }
        }
    }
}
