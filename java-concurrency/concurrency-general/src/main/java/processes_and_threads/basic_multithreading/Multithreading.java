package processes_and_threads.basic_multithreading;

public class Multithreading {

    public static void main(String[] args) {

        for (int i = 1; i < 5; i++) {
            MultithreadingThing myObj = new MultithreadingThing(i);
            Thread myThread = new Thread(myObj);
            myThread.start();
        }
    }
}
