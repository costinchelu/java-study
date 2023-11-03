package processes_and_threads.basic_multithreading;

import lombok.AllArgsConstructor;

// we can use extends Thread
@AllArgsConstructor
public class MultithreadingThing implements Runnable {

    private int threadNumber;

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " - thread number: " + threadNumber);

            if (threadNumber == 3) {
                throw new RuntimeException("this thread will be interrupted");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
