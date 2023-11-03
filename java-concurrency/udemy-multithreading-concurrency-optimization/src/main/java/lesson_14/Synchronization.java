package lesson_14;

public class Synchronization {

    private static int counter = 0;


    // we have to make sure that this method will be executed only by a single thread at a given time
    public static synchronized void increment() {
        counter++;
        System.out.println(Thread.currentThread().getName() + " : " + counter);
    }

    public static void process() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment();
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

        System.out.println("The counter is " + counter);
    }

    public static void main(String[] args) {
        process();
    }
}
