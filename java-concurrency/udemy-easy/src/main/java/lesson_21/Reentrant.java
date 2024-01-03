package lesson_21;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * REENTRANT LOCK
 *
 * same behaviour as the synchronized approach (with additional features)
 */
public class Reentrant {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();


    public static void increment() {

        lock.lock();

        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
            // in order to make sure that we will unlock no mather what (or else we will get a deadlock)
        }
    }


    public static void main(String[] args) {
         Thread t1 = new Thread(new Runnable() {
             @Override
             public void run() {
                increment();
             }
         });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("counter = " + counter);
    }
}
