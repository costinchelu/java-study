package sec15g_threadpools;

import sec15a_threads.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static sec15d_concurrentpackage.ConcurrentPackage.EOF;

public class ThreadPools
{
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);


        // executor service is vital for applications that are using a large number of threads.
        // It optimises thread management, but for smaller number of threads is overkill

        // we will use an anonymous callable class
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I'm being printed for the Callable class.");
                return "This is the callable result";
            }
        });
        // we wouldn't use this method in a UI app because main thread would be frozen until future.get()
        // block result is available
        // whole java.util.concurrent.* package should not be used in a UI app


        try {
            System.out.println(future.get());
        }
        catch (ExecutionException ee) {
            System.out.println("Something went wrong");
        }
        catch (InterruptedException ie) {
            System.out.println("Thread running the task was interrupted!");
        }


        executorService.shutdown();
        // executor needs to be closed too
    }
}


class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5" };

        for(String num: nums) {
            try {
                System.out.println(color + "Adding..." + num);

                bufferLock.lock();
                try {
                    buffer.add(num);
                }
                finally {
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting...");

        bufferLock.lock();
        try {
            buffer.add("EOF");
        }
        finally {
            bufferLock.unlock();
        }
    }
}


class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true) {
            if(bufferLock.tryLock()) {
                try {
                    if(buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color + "The counter = " + counter);
                    if(buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    }
                    else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                }
                finally {
                    bufferLock.unlock();
                }
            }
            else {
                counter++;
            }
        }
    }
}
