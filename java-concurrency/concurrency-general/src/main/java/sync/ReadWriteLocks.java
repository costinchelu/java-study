package sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

import static java.lang.Thread.sleep;
import static sync.ConcurrentUtils.stop;


public class ReadWriteLocks {

    public static void main(String[] args) {

        // readRightLocks();
        // stampedLocks();
        optimisticLocking();
    }

    public static void readRightLocks() {
        /*
         * The interface ReadWriteLock specifies another type of lock maintaining
         * a pair of locks for read and write access. The idea behind read-write locks
         * is that it's usually safe to read mutable variables concurrently
         * as long as nobody is writing to this variable. So the read-lock can be held simultaneously
         * by multiple threads as long as no threads hold the write-lock.
         * This can improve performance and throughput in case that reads are more frequent than writes.
         * */
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1000);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();
        /*
         * The above example first acquires a write-lock in order to put
         * a new value to the map after sleeping for one second. Before this task has finished
         * two other tasks are being submitted trying to read the entry from the map and sleep for one second
         *
         * Both read tasks have to wait the whole second until the write task has finished.
         * After the write lock has been released both read tasks are executed in parallel
         * and print the result simultaneously to the console. They don't have to wait for each other
         * to finish because read-locks can safely be acquired concurrently as long as no write-lock is held by another thread.
         * */
    }

    public static void stampedLocks() {
        /*
         * In contrast to ReadWriteLock the locking methods of a StampedLock
         * return a stamp represented by a long value. You can use these stamps
         * to either release a lock or to check if the lock is still valid.
         * Additionally stamped locks support another lock mode called optimistic locking.
         * */
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                sleep(1000);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();
        /*
         * Obtaining a read or write lock via readLock() or writeLock() returns a stamp
         * which is later used for unlocking within the finally block.
         * Keep in mind that stamped locks don't implement reentrant characteristics.
         * Each call to lock returns a new stamp and blocks if no lock is available
         * even if the same thread already holds a lock. So you have to pay particular attention
         * not to run into deadlocks.
         * */
    }

    public static void optimisticLocking() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(1000);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(2000);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        stop(executor);
        /*
        * An optimistic read lock is acquired by calling tryOptimisticRead()
        * which always returns a stamp without blocking the current thread,
        * no matter if the lock is actually available.
        * If there's already a write lock active the returned stamp equals zero.
        * You can always check if a stamp is valid by calling lock.validate(stamp).
        *
        * The optimistic lock is valid right after acquiring the lock.
        * In contrast to normal read locks an optimistic lock doesn't prevent other threads
        * to obtain a write lock instantaneously. After sending the first thread to sleep
        * for one second the second thread obtains a write lock without
        * waiting for the optimistic read lock to be released.
        * From this point the optimistic read lock is no longer valid.
        * Even when the write lock is released the optimistic read locks stays invalid.
        * So when working with optimistic locks you have to validate the lock every time
        * after accessing any shared mutable variable to make sure the read was still valid.
        *
        * */
    }
}
