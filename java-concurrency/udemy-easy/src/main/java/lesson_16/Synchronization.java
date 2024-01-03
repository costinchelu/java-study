package lesson_16;


public class Synchronization {

    private static int counter1 = 0;
    private static int counter2 = 0;

    // final objects for different locks. Independent methods can be executed by independent threads.
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();



    // getting the intrinsic lock of an object means that we are not blocking the whole Synchronization class
    // so both threads can run concurrently
    public static void increment1() {
        synchronized (lock1) {
            counter1++;
            System.out.println(Thread.currentThread().getName() + " : " + counter1);
        }
    }

    public static void increment2() {
        synchronized (lock2) {
            counter2++;
            System.out.println(Thread.currentThread().getName() + " : " + counter2);
        }
    }

    public static void process() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                increment1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment2();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The counter 1 is " + counter1);
        System.out.println("The counter 2 is " + counter2);

    }

    public static void main(String[] args) {
        process();
    }
}

