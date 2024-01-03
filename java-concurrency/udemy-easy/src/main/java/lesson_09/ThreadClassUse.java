package lesson_09;

class Runner1 extends Thread {

    @Override
    public void run() {
        for (int i = 1; i < 20; i += 2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 extends Thread {

    @Override
    public void run() {
        for (int i = 2; i < 20; i += 2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner2: " + i);
        }
    }
}

public class ThreadClassUse {

    public static void main(String[] args) {

        Thread runner1 = new Runner1();
        Thread runner2 = new Runner2();

        runner1.start();
        runner2.start();

        // we can wait for a thread to finish with join()

        try {
            runner1.join();
            runner2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // using runner1.join() we've stated that we want the runner1 thread to finish first, and after runner1 is
        // in dead state we will continue with the next instruction
        // this means that the next console print (main thread)
        // will run after everything in the run() method of runner1 has been executed
        System.out.println("Finished with threads...");

        // threads we create are worker threads (children of the main thread)

        // daemon threads are created for I/O operations or h2rest.services and are terminated by the JVM when all
        // other worker threads are terminated
        // worker threads are never terminated by the JVM
    }
}
