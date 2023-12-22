package processes_and_threads.thread_vs_runnable;


class PrintThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread executing: " + Thread.currentThread().getName());
    }
}

public class ThreadTest {
    public static void main(String... args) {
        // creating the thread - thread has not started yet
        final PrintThread printThread = new PrintThread();

        // starting the thread
        printThread.start();

        // main thread is waiting for printThread to complete execution
        try {
            printThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the name of the main thread - main
        System.out.println("Thread executing: " + Thread.currentThread().getName());
    }
}
