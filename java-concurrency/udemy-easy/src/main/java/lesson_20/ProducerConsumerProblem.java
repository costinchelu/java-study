package lesson_20;

import java.util.ArrayList;
import java.util.List;

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
