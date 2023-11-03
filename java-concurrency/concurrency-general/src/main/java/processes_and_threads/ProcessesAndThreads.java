package processes_and_threads;

import java.util.concurrent.TimeUnit;

public class ProcessesAndThreads {

    /*
    Processes are instances of programs which typically run independent
     to each other, e.g. if you start a java program the operating system spawns a new process
     which runs in parallel to other programs.
     Inside those processes we can utilize threads to execute code concurrently,
     so we can make the most out of the available cores of the CPU.

    Runnable: functional interface defining a single void no-args method run()
     */
    public static void main(String[] args) {

       threadExample1();
       threadExample2();

    }

    public static void threadExample1() {
        Runnable runnableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Current thread: " + threadName);
        };

        runnableTask.run();

        Thread thread = new Thread(runnableTask);
        thread.start();

        System.out.println("Message: in main thread");

        /*
        Due to concurrent execution we cannot predict if the runnable will be invoked before
        or after printing 'done'. The order is non-deterministic,
        thus making concurrent programming a complex runnableTask in larger applications.
         */
    }

    public static void threadExample2() {
        Runnable runnableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("START " + threadName);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CONTINUE (after sleeping for 1 sec) " + threadName);
        };

        Thread thread = new Thread(runnableTask);
        thread.start();
    }
}
