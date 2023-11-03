package processes_and_threads;

class OddNumbersThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 1; i < 20; i += 2) {
                System.out.print(i + " ");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EvenNumbersRunnable implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i += 2) {
                System.out.print(i + " ");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class OddEvenExample {

    public static void main(String[] args) {

        OddNumbersThread odd = new OddNumbersThread();
        EvenNumbersRunnable r = new EvenNumbersRunnable();
        Thread even = new Thread(r);
        //Thread eve = new Thread(new processes_and_threads.EvenNumbersRunnable());   // alternative

        even.start();
        odd.start();

        System.out.println("THE END");
    }
}
