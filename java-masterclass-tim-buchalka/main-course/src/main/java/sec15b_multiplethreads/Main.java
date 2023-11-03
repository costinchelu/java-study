package sec15b_multiplethreads;

import sec15a_threads.ThreadColor;

public class Main {
    public static void main(String[] args) {

        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
        // we have 2 threads that are trying to access the same method "do countdown".
        // without synchronization both will try to access same resource in the same time, and the result
        // is unpredictable (thread interference). Synchronizing the block, permits an expected result, threads running one after the other
    }
}


class Countdown {
    private int i;

    // using a global variable = the program will keep this global variable in the heap and both threads can
    // access the same variable. This will provoke a "thread interference" (race condition).
    // Keeping only local variables will not result in thread interference because both threads will have
    // their own version of that variable which is local and it will be present on each stack of every thread.

    // the problem is more prevalent when each thread will write (modify) that resource


    // the process of controlling when threads execute code and therefore when they can access the heap,
    // is called synchronization
    // when working with threads we have to synchronize all areas where interference can happen.

    // we can synchronize whole method or just a block:

    // public synchronized void doCountdown() { (doing it that way would synchronize too much code)

     public void doCountdown() {
        String color;

        // we don't need to synchronize this (no reason to do it - threads suspended or blocked unnecessarily)
        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        // adding synchronization to a block. In this case only one thread can
        // access the resource at a given point of time. this - in the parameter area, is the synchronized object
         // we only actually need to synchronize this block
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        // the actual shared resources
        }
    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}
