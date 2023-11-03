package sec15i_deadlocks;

public class Deadlocks {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        // we got a deadlock - program doesn't crash but just waits for something to change.
        // It will stay in that state indefinitely

        // if we made both threads to obtain the same locks in the same order, the deadlocks would not have been occur
    }


    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                System.out.println("Thread 1: waiting for lock2");

                synchronized (lock2) {
                    System.out.println("Thread 1: has lock1 and lock2");
                }
                System.out.println("Thread 1: released lock2");
            }
            System.out.println("Thread 1: released lock1. Exiting...");
        }
    }


    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: has lock 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                System.out.println("Thread 2: waiting for lock1");

                synchronized (lock1) {
                    System.out.println("Thread 2: has lock2 and lock1");
                }
                System.out.println("Thread 2: released lock1");
            }
            System.out.println("Thread 2: released lock2. Exiting...");
        }
    }
}

/*
*
* Thread2 should obtain the lock in the same order than Thread1 does.
*
* So Thread2 class will look like Thread1 class
*
* */

