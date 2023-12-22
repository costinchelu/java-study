package processes_and_threads.thread_vs_runnable;


class PrintRunner implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread executing: " + Thread.currentThread().getName());
    }
}

public class RunnableTest {
    public static void main(String... args) {
        // creating the thread - thread has not started yet
        final PrintRunner printRunner = new PrintRunner();
        final Thread printThread = new Thread(printRunner);

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
