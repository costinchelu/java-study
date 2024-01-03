package lesson_08;

class Runner1 implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i < 20; i += 2) {
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 implements Runnable {

    @Override
    public void run() {
        for (int i = 2; i < 20; i += 2) {
            System.out.println("Runner2: " + i);
        }
    }
}

public class RunnableInterfaceUse {

    public static void main(String[] args) {

        // this is not parallel execution
        // multithreading is using a single processor core with the help of time-slicing algorithm
        Thread runner1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i += 2) {
                    System.out.println("Runner1: " + i);
                }
            }
        });
        Thread runner2 = new Thread(new Runner2());

        runner1.start();
        runner2.start();
    }
}
