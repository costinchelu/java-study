package sec15d_concurrentpackage;

import sec15a_threads.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sec15d_concurrentpackage.ConcurrentPackage.EOF;

public class ConcurrentPackage
{
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

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

    public MyProducer(List<String> buffer, String color) {
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
                synchronized (buffer) {
                    buffer.add(num);
                }
                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting...");
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}


class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (buffer) {
                if(buffer.isEmpty()) {
                    continue;
                }
                if(buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                }
                else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }
            //synchronizing because we don't want de producer or another consumer to change the arrayList
            // once a consumer threads checked whether the arrayList is empty
            //all calls to methods in arrayList take place as a unit collectively and all re running in this
            // critical section
            // by synchronizing the arrayList, thread interference no longer produces errors or freezing the program
        }
    }
}