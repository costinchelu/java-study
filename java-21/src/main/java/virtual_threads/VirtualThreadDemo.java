package virtual_threads;

public class VirtualThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread platformThread = Thread.ofPlatform().unstarted(() -> System.out.println(Thread.currentThread()));
        platformThread.start();

        Thread virtualThread = Thread.ofVirtual().unstarted(() -> System.out.println(Thread.currentThread()));
        virtualThread.start();

        platformThread.join();
        virtualThread.join();

        System.out.println("Virtual Thread class: " + virtualThread.getClass());
    }
}
