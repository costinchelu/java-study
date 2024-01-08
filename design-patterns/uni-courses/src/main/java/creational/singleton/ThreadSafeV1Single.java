package creational.singleton;

import lombok.Getter;

@Getter
public class ThreadSafeV1Single {

    private final String name;

    private final double amount;

    private static ThreadSafeV1Single instance = null;

    private ThreadSafeV1Single(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public static synchronized ThreadSafeV1Single getInstance(String name, double amount) {
        if (instance == null) {
            instance = new ThreadSafeV1Single(name, amount);
        }
        return instance;
    }

}


class DemoMultiThread {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused" + "\n" +
                "If you see different values, then 2 singletons were created" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new DemoMultiThreadV2.ThreadFoo());
        Thread threadBar = new Thread(new DemoMultiThreadV2.ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            ThreadSafeV1Single singleton = ThreadSafeV1Single.getInstance("FOO", 1D);
            System.out.println(singleton.getName());
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            ThreadSafeV1Single singleton = ThreadSafeV1Single.getInstance("BAR", 2D);
            System.out.println(singleton.getName());
        }
    }
}