package sec15a_threads;

import static sec15a_threads.ThreadColor.ANSI_CYAN;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(ANSI_CYAN + "Hello from MyRunnable's implementation of run!");
    }
}

// using Runnable interface