package sec15l_fairlocks;

import sec15a_threads.ThreadColor;
import java.util.concurrent.locks.ReentrantLock;

public class FairLocks {
    private static ReentrantLock lock = new ReentrantLock(true);
    /*
    * ReentrantLock parameter true = fair lock (FIFO) = no starvation
    * only fairness in acquiring the lock (first come, first served), not fairness about thread scheduling
    * in this case, if a thread is taking a long time to complete, then other threads could
    * potentially wait a lot of time (it could slow quite a lot things down when we have a lot of threads)
    * being static, this object will get locked by the different threads concurrently
    *
    * Fair locks will lower the performance of the app
    * With fair locks we will correct the starvation problem
    */
    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);
        //setting a priority is just a suggestion to the operating system

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    System.out.format(threadColor + "%s: runCount = %d\n",
                            Thread.currentThread().getName(), runCount++);
                    // execute critical section  of code
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

/*
* a live lock is similar to a deadlock but instead of the threads been blocked they're actually
* constantly active and usually waiting for all the other threads to complete their tasks
* now since all the threads are waiting for others to complete none of them can actually progress
*
* so let's say that the thread a will loop until thread b complete its task and thread b will loop
*  until thread a complete its task thread a and thread b can get into a state in which they're
* both looping and waiting for the other to complete
* threads will never progress but they're not actually blocked
 */