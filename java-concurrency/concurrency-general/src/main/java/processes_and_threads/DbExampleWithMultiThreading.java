package processes_and_threads;

public class DbExampleWithMultiThreading {

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();

        Thread t1 = new Thread(DbExampleWithMultiThreading::collectDataFromDB1);
        Thread t2 = new Thread(DbExampleWithMultiThreading::collectDataFromDB2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }


    public static void collectDataFromDB1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void collectDataFromDB2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
