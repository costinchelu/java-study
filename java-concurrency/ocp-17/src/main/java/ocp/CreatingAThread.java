package ocp;

public class CreatingAThread {
    public static void main(String[] args) {

        // one liner
        new Thread(() -> System.out.println("Hello "))
                .start();

        System.out.println("World!");
        // order of thread execution is not often guaranteed.

        // World!
        //Hello
    }
}

class FourThreads {
    public static void main(String[] args) {
        Runnable printInventory = () -> System.out.println("Printing zoo inventory");

        Runnable printRecords = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record: " + i);
            }
        };

        // 1 main thread + 3 user threads
        System.out.println("Begin");
        new Thread(printInventory).start();
        new Thread(printRecords).start();
        new Thread(printInventory).start();
        System.out.println("End");
    }
}