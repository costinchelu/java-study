package creational.singleton;

import lombok.Getter;

@Getter
public class SimpleThreadSafeSingle {

    private final String name;

    private final double amount;

    private static SimpleThreadSafeSingle instance = null;

    private SimpleThreadSafeSingle(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public static synchronized SimpleThreadSafeSingle getInstance(String name, double amount) {
        if (instance == null) {
            instance = new SimpleThreadSafeSingle(name, amount);
        }
        return instance;
    }
}

class DemoSimpleMultiThread {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused" + "\n" +
                "If you see different values, then 2 singletons were created" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new DemoSimpleMultiThread.ThreadFoo());
        Thread threadBar = new Thread(new DemoSimpleMultiThread.ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            SimpleThreadSafeSingle singleton = SimpleThreadSafeSingle.getInstance("FOO", 1D);
            System.out.println(singleton.getName());
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            SimpleThreadSafeSingle singleton = SimpleThreadSafeSingle.getInstance("BAR", 2D);
            System.out.println(singleton.getName());
        }
    }
}