package lesson_12;


class NormalWorker implements Runnable {

    @Override
    public void run() {
        System.out.println("Normal thread started...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal thread finished execution...");
    }
}

class DaemonWorker implements Runnable {

    @Override
    public void run() {
        System.out.println("Daemon thread started...");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread is running...");
        }
    }
}


public class DaemonVsWorker {

    public static void main(String[] args) {

        Thread t1 = new Thread(new DaemonWorker(), "DAEMON");
        Thread t2 = new Thread(new NormalWorker(), "WORKER");

        t1.setDaemon(true);
        System.out.println(t1.getName() + t1.isDaemon());
        System.out.println(t2.getName() + t2.isDaemon());

        t1.start();
        t2.start();
    }
}
