package processes_and_threads.basic_multithreading;


public class ThreadInterruption {

    public static void main(String[] args) {
        Thread newThread = new Thread(ThreadInterruption::doSomething);
        newThread.start();
        newThread.interrupt();
    }

    private static void doSomething() {
        System.out.println(" doing something");
        for (int i = 0; i < 5; i++) {
            System.out.println(doSomething2(i));
        }
    }

    private static int doSomething2(int i) {
        if (i > 3 && Thread.currentThread().isInterrupted()) {
            System.out.println("Prematurely interrupted computation");
            return -1;
        }
        return i;
    }
}
