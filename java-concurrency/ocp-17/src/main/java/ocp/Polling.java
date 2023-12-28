package ocp;

public class Polling {
    public static void main(String[] args) {
        CheckResultsWithSleepAndInterrupt.run();
    }
}

class CheckResults {

    private static int counter = 0;

    public static void run() {
        new Thread(() -> {
           for (int i = 0; i < 1_000_000; i++)
               counter++;
        }).start();

        while (counter < 1_000_000) {
            System.out.println("Not reached yet!");
        }

        System.out.println("Reached: " + counter);
    }
}

// main thread - TIMED_WAITING - when sleep
// While this prevents the CPU from waiting endlessly on a while() loop, it
//comes at the cost of inserting one-second delays into our program.
// If the task takes 2.1 seconds to run, the program will use the full 3 seconds, wasting 0.9 seconds.
class CheckResultsWithSleep {

    private static int counter = 0;

    public static void run() {
        new Thread(() -> {
            for(int i = 0; i < 1_000_000_000; i++)
                counter++;
        }).start();

        while(counter < 1_000_000) {
            System.out.println("Not reached yet");
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
        }
        System.out.println("Reached: " + counter);
    }
}

// Allow the thread to interrupt the main() thread when it’s done
// while waiting, after the count is finished, main thread who is waiting (sleep) will be
// switched from the timed waiting state to become RUNNABLE again
// so we would not need to wait the whole interval if the counting is finished faster
class CheckResultsWithSleepAndInterrupt {

    private static int counter = 0;

    public static void run() {
        final var mainThread = Thread.currentThread();
        new Thread(() -> {
            for(int i = 0; i < 1_000_000_000; i++)
                counter++;
            mainThread.interrupt();
        }).start();

        while(counter < 1_000_000) {
            System.out.println("Not reached yet");
            try {
                Thread.sleep(10_000); // 10 seconds (it will not wait for so long)
            } catch (InterruptedException e) {
                System.out.println("Interrupted - exits the TIMED_WAITING state!");
            }
        }
        System.out.println("Reached: " + counter);
    } }
