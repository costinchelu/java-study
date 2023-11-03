package sec15a_threads;

import static sec15a_threads.ThreadColor.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(ANSI_PURPLE + "Hello from the main thread!");

        Thread anotherThread = new AnotherThread();         // create an instance of the thread
        anotherThread.setName("== Another Thread ==");      // we can set names for different threads
        anotherThread.start();                              // enables the JVM to run the run() method

        // it is up to the system to schedule when the threads will run
        // we can never assume that threads will run in a particular order

        // we have to create a new instance of the thread class every time we want to run one. Using same
        // instance will give an IllegalState exception

        // we can use an anonymous class but this will start the thread immediately
        // of course if we want to reuse that thread is preferable to create a new class for that (not anonymous)

        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread!");
            }
        }.start();


        // using MyRunnable
        Thread myRunnableThread = new Thread(new MyRunnable());     // creating thread
        myRunnableThread.start();

        // or with an anonymous class:
        Thread myRunnableThread2 = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()!");

                // we also can join threads
                // (and when we do that, myRunnableThread2 will wait for the termination of myRunnableThread)
                try {
                    myRunnableThread.join();
                    System.out.println(ANSI_RED + "myRunnableThread terminated, so I'm running myRunnableThread2 now.");
                }
                catch(InterruptedException e) {
                    System.out.println(ANSI_RED + "For some reason, I couldn't wait.");
                }
            }
        });
        myRunnableThread2.start();
        anotherThread.interrupt();          // to provoke an interruption of the anotherThread.
                                            // Can be used by some threads to stop other threads

        System.out.println(ANSI_PURPLE + "Hello again from the main thread!");

        // most of the time we use the Runnable way (there are many methods in the Java API that use the Runnable interface)
        // usually are implemented with the help of lambda expressions (using anonymous runnable instances)

        // a thread will terminate when it reaches run() end (implicitly or explicitly)
        // thread is started with .start() method
        // if instead we are using .run() method, that thread will run in the previous thread
        // so we will not call run() method directly

    }
}
