package ocp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafety {

}

class SheepManager {

    private int sheepCount = 0;

    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");

        /*
        1 2 3 4 5 6 7 8 9 10
        1 9 8 7 3 6 6 2 4 5
        1 8 7 3 2 6 5 4 2 9
        5 8 1 2 4 6 7 10 3 9

        A problem occurs when two threads both execute the right side of the expression, reading the “old” value
        before either thread writes the “new” value of the variable.
         */
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        } finally {
            service.shutdown();
        }
    }
}

class SheepManagerAtomic {

    private AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport() {
        System.out.println(sheepCount.incrementAndGet() + " ");
        /*
        Unlike our previous sample output, the numbers 1 through 10 will always be printed, although the order is still not guaranteed.
         */
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            SheepManagerAtomic manager = new SheepManagerAtomic();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        } finally {
            service.shutdown();
        }
    }
}

class SheepManagerSynchronized {

    private int sheepCount = 0;

    private synchronized void incrementAndReport() {
        System.out.println(++sheepCount + " ");
    }

    public static void main(String[] args) {
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            SheepManagerSynchronized manager = new SheepManagerSynchronized();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        }
    }
}

class SheepManagerLock {

    private int sheepCount = 0;

    // although fair ordering locks are more resource expensive, they are useful when the order of processing is needed
    Lock lock = new ReentrantLock(true);

    private void incrementAndReport() {
        try {
            lock.lock();
            System.out.println(++sheepCount + " ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        try (ExecutorService service = Executors.newFixedThreadPool(15)) {
            SheepManagerLock manager = new SheepManagerLock();
            for (int i = 0; i < 10; i++)
                service.submit(manager::incrementAndReport);
        }
    }
}

class UsingTryLock {

    static Lock lock = new ReentrantLock();

    public static void printHello(Lock lock) {
        try {
            lock.lock();
            System.out.println("Hello");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> printHello(lock)).start();

        if (lock.tryLock()) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else");
        }
    }
}