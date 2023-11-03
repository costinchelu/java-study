package sec15h_arrayblockingqueue;

import sec15a_threads.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static sec15d_concurrentpackage.ConcurrentPackage.EOF;

public class ArrayBlockingQueueClass
{
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
        //ArrayBlockingQueues are Bounded - that means we have to pass their size to the constructor
        //So they're not actually grow like ArrayList do
        //ArrayBlockingQueue is using put, take instead of add, remove and peek instead of get
        // put is thread safe so we don't need to put synchronize or put a lock
        // ArrayBlockingQueue is a FIFO queue so we don't need to specify any index when putting or getting from it

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

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
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5" };

        for(String num: nums) {
            try {
                System.out.println(color + "Adding..." + num);
                buffer.put(num);
                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting...");
        try {
            buffer.put("EOF");
        }
        catch(InterruptedException e) {
        }
    }
}


class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (buffer) {
                // we still need some synchronization (else we get a NullPointerException because while we are suspended
                // in a thread, the other consumer thread can run and take the next element from the queue)
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        // peek method doesn't block when the queue is empty so we don't need to use isEmpty()
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) { }
            }
        }
    }
}
