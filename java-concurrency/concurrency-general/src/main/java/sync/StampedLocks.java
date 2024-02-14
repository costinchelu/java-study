package sync;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * It allows optimistic locking for the read operations.
 * Also, it has better performance than the ReentrantReadWriteLock.
 */
public class StampedLocks {

    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        Balance b = new Balance(10000);

        Runnable w = () -> {
            long stamp = lock.writeLock();

            b.setAmount(b.getAmount() + 1000);;
            System.out.println("Write: " + b.getAmount() + ". Thread: " + Thread.currentThread().getName());

            lock.unlockWrite(stamp);
        };

        Runnable r = () -> {
            long stamp = lock.tryOptimisticRead();
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    System.out.println("Read: " + b.getAmount() + ". Thread: " + Thread.currentThread().getName());
                } finally {
                    lock.unlockRead(stamp);
                }
            } else {
                System.out.println("Optimistic read fails!");
            }
        };

        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 50; i++) {
                executorService.submit(w);
                executorService.submit(r);
            }
        }
    }
}

@AllArgsConstructor
@Getter
@Setter
class Balance {

    private int amount;
}
