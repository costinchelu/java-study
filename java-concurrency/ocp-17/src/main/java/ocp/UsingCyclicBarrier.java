package ocp;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

class LionPenManager {

    private void removeLions() {
        System.out.println("Removing lions");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addLions() {
        System.out.println("Adding lions");
    }

    public void performTask() {
        removeLions();
        cleanPen();
        addLions();

        /*
        Although the results are ordered within a single thread, the output is entirely random among multiple workers.
        We see that some lions are still being removed while the cage is being cleaned,
           and other lions are added before the cleaning process is finished.
         */
    }

    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(4)) {
            var manager = new LionPenManager();
            for (int i = 0; i < 4; i++)
                service.submit(manager::performTask);
        }
    }

}

public class UsingCyclicBarrier {

    private void removeLions() {
        System.out.println("Removing lions");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addLions() {
        System.out.println("Adding lions");
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        /*
        The CyclicBarrier class allows us to perform complex, multithreaded tasks while all
threads stop and wait at logical barriers. This solution is superior to a single-threaded solution,
as the individual tasks, such as removing the lions, can be completed in parallel by all four zoo workers.
         */
    }

    public static void main(String[] args) {
        try (var service = Executors.newFixedThreadPool(4)) {
            var manager = new UsingCyclicBarrier();
            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4, () -> System.out.println("Pen cleared!"));

            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask(c1, c2));
        }
    }
}






