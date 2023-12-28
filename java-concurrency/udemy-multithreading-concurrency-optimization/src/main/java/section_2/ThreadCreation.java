package section_2;

public class ThreadCreation {
}

class Main1 {

    public static void main(String[] args) {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in  a new thread as soon as it is scheduled by the OS
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        newThread.setName("New-Worker-Thread");
        newThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " before starting a new thread");

        newThread.start();

        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " after starting a new thread");
    }
}

class Main2 {

    public static void main(String [] args) {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in a new thread
                throw new RuntimeException("Intentional Exception");
            }
        });

        newThread.setName("Misbehaving-thread");

        // handler is called if an exception is thrown inside the thread and had not got caught anywhere
        newThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
            }
        });

        newThread.start();
    }
}