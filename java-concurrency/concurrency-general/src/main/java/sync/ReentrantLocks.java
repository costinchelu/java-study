package sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static sync.ConcurrentUtils.sleep;
import static sync.ConcurrentUtils.stop;


/*
* The class ReentrantLock is a mutual exclusion lock with the same basic behavior
* as the implicit monitors accessed via the synchronized keyword but with extended capabilities.
* As the name suggests this lock implements reentrant characteristics just as implicit monitors.
*
* A lock is acquired via lock() and released via unlock().
* It's important to wrap your code into a try/finally block to ensure unlocking in case of exceptions.
* This method is thread-safe just like the synchronized counterpart.
* If another thread has already acquired the lock subsequent calls to lock()
* pause the current thread until the lock has been unlocked.
* Only one thread can hold the lock at any given time.
* */
public class ReentrantLocks {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock reentrantLock = new ReentrantLock();

        executor.submit(() -> {
            reentrantLock.lock();
            try {
                sleep(1);
            } finally {
                reentrantLock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + reentrantLock.isLocked());
            System.out.println("Held by me: " + reentrantLock.isHeldByCurrentThread());
            boolean locked = reentrantLock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executor);
    }

}
