package sec15m_livelocks;

public class Main {
    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        }).start();

       /*
        *
        * in this case worker1 will give the sharedResource to worker2 then worker2 will give the resource
        * back to worker1. Because of that, the loop will go forever
        *
        */
    }
}
