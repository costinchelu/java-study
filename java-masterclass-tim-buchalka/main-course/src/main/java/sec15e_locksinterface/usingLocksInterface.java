package sec15e_locksinterface;

import sec15a_threads.ThreadColor;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static sec15d_concurrentpackage.ConcurrentPackage.EOF;

public class usingLocksInterface
{
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();

        ReentrantLock bufferLock = new ReentrantLock();

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

        // 3 threads are using the same ArrayList (1 is writing to it and 2 are reading from it)
        //arrayList is unsynchronized (roughly equivalent to Vector class which is synchronized)
    }
}

// basically the producer is writing one of the 5 numbers and then one of the 2 consumers is concurrently removing
// that number and prints it to the screen. When the producer is writing EOF to the arrayList then the two consumers
// will finish the thread too. Because the producer sleeps a second, one of the consumers will have a chance to
// extract that number from the arrayList

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

        // by using locks we can get rid of synchronized blocks from the previous example
        // essentially we are replacing synchronized() {} block with lock() and unlock()

        for(String num: nums) {
            try {
                System.out.println(color + "Adding..." + num);

                // we have to manage the process of locking and unlocking ourselves
                // we can use a try - final to make sure we will release the lock
                bufferLock.lock();
                try {
                    buffer.add(num);
                }
                finally {
                    bufferLock.unlock();        // this will always run
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

    // in this run method we need more unlocks because we have two threads - first will enter the get() option
    // the other will enter the isEmpty option

    @Override
    public void run() {

        int counter = 0;

        while(true) {
            // using tryLock:
            if(bufferLock.tryLock()) {
                try {
                    if(buffer.isEmpty()) {
                        // bufferLock.unlock();             --> removed
                        continue;
                    }
                    System.out.println(color + "The counter = " + counter);
                    if(buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Exiting");
                        // bufferLock.unlock();             --> removed
                        break;
                    }
                    else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                }
                finally {
                    bufferLock.unlock();  // we only unlock once
                }
            }
            else {
                counter++;
            }
        }
        //synchronizing because we don't want de producer or another consumer to change the arrayList
        // once a consumer threads checked whether the arrayList is empty
        //all calls to methods in arrayList take place as a unit collectively and all re running in this
        // critical section
        // by synchronizing the arrayList, thread interference no longer produces errors or freezing the program
    }
}
