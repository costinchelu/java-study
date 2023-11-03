package sec15a_threads;

import static sec15a_threads.ThreadColor.ANSI_BLUE;


public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());

        try {
            Thread.sleep(5000);     // time is in milliseconds. 3000 ms = 3 s
        }
        catch(InterruptedException e) {
            System.out.println(ANSI_BLUE + "Couldn't sleep for 5 seconds because something interrupted me!");
                                            //only if another thread is interrupting this one
            return;
                    // will immediately terminate the AnotherThread instance
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake!");
    }
}
