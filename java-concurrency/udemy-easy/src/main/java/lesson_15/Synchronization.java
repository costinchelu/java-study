package lesson_15;


public class Synchronization {

    private static int counter1 = 0;
    private static int counter2 = 0;


// because Synchronization object has a single lock, this is why the 2 methods cannot be executed at the same time
    public static void increment1() {
        synchronized (Synchronization.class) {
            counter1++;
            System.out.println(Thread.currentThread().getName() + " : " + counter1);
        }
    }

    public static void increment2() {
        synchronized (Synchronization.class) {
            counter2++;
            System.out.println(Thread.currentThread().getName() + " : " + counter2);
        }
    }

    public static void process() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment1();
                }
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

