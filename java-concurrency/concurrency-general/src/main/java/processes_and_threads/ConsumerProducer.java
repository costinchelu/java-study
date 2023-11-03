package processes_and_threads;

import java.util.ArrayList;
import java.util.Random;

class Consumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (ConsumerProducer.list) {
                    if(!ConsumerProducer.list.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " : " + ConsumerProducer.list.get(0));
                        ConsumerProducer.list.remove(0);
                        ConsumerProducer.list.notifyAll();
                    } else {
                        ConsumerProducer.list.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable {

    private Random random = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (ConsumerProducer.list) {
                    if(ConsumerProducer.list.size() < 3) {
                        int newNumber = random.nextInt();
                        ConsumerProducer.list.add(newNumber);
                        System.out.println(Thread.currentThread().getName() + " : " + newNumber);
                        ConsumerProducer.list.notifyAll();
                    } else {
                        ConsumerProducer.list.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



public class ConsumerProducer {

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Producer producer = new Producer();
        Thread producerThread = new Thread(producer, "PRODUCER");
        Consumer consumer = new Consumer();
        Thread consumerThread = new Thread(consumer, "CONSUMER");

        producerThread.start();
        consumerThread.start();
    }
}
