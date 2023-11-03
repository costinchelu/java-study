package howto;

public class RunnableExample {

    public static void main(String[] args) {

        System.out.println("Creating an implementation of the Runnable interface");
        System.out.println("Runnable is also a functional interface (only method = run()");
        Runnable runnable = () -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Thread " + Thread.currentThread().getId() + ": iteration " + i);
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.start();

        Thread thread3 = new Thread(runnable);
        thread3.start();
    }
}
