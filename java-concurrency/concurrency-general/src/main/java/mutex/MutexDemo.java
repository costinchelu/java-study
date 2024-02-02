package mutex;

import java.util.ArrayList;
import java.util.List;

public class MutexDemo {

    static LinkEdList list = new LinkEdList();

    /*
    All the 5 threads will rush to add a message inside the linked list
    The resulted list will likely fail with a NullPointerException, or it will have missing indexes

    By synchronizing the add method from the LinkEdList we may prevent this issue.
    Each thread that will acquire the add method will lock that resource for other threads. After it finishes
    with the method, that particular thread will release the lock
     */
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            Thread t = new Thread(() -> {
                list.add("val 1" + " : " + Thread.currentThread().getName());
                list.add("val 2" + " : " + Thread.currentThread().getName());
                list.add("val 3" + " : " + Thread.currentThread().getName());
                list.add("val 4" + " : " + Thread.currentThread().getName());
                list.add("val 5" + " : " + Thread.currentThread().getName());
            }, "WorkerThreadNo-" + i);
            t.start();
            threads.add(t);
        }

        // wait for all the user defined threads to finish first
        for (Thread t : threads) {
            t.join();
        }

        // Then print the resulted list
        list.printList();
    }
}
