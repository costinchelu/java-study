package problems.concurrency;

import java.util.ArrayList;
import java.util.List;



/**
 * <b>In computing, the producer-consumer problem (also known as the bounded-buffer problem)
 * is a classic example of a multi-process synchronization problem.</b><br>
 * The problem describes two processes, the producer and the consumer,
 * which share a common, fixed-size buffer used as a queue.
 *<br>
 * The producer’s job is to generate data, put it into the buffer, and start again.
 * At the same time, the consumer is consuming the data (i.e. removing it from the buffer), one piece at a time.
 * <br><br>
 * <b>Problem:</b><br>
 * To make sure that the producer won’t try to add data into the buffer if it’s full
 * and that the consumer won’t try to remove data from an empty buffer.
 * <br><br>
 * <b>Solution:</b><br>
 * The producer is to either go to sleep or discard data if the buffer is full.
 * The next time the consumer removes an item from the buffer, it notifies the producer,
 * who starts to fill the buffer again.
 * In the same way, the consumer can go to sleep if it finds the buffer to be empty.
 * The next time the producer puts data into the buffer, it wakes up the sleeping consumer.
 * An inadequate solution could result in a deadlock where both processes are waiting to be awakened.
 */
class Processor {

    private List<Integer> list = new ArrayList<>();
    private static final int LIST_UPPER_LIMIT = 5;
    private static final int LIST_LOWER_LIMIT = 0;
    private int value = 0;
    private final Object lock = new Object();


    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIST_UPPER_LIMIT) {
                    System.out.println("Waiting for the consumer to empty the list...");
                    lock.wait();
                } else {
                    System.out.println("Producing: " + value);
                    list.add(value++);
                    lock.notify();
                    // if we call notify and the other thread is not in the waiting state, nothing is going to happen
                }

                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIST_LOWER_LIMIT) {
                    System.out.println("Waiting for producer to fill the list...");
                    lock.wait();
                } else {
                    int consumed =  list.remove(list.size() - 1);
                    System.out.println("Consuming: " + consumed);
                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }
}


public class ProducerConsumerProblem {

    public static void main(String[] args) {

        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
