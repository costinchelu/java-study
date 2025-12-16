package creational.singleton;

import lombok.Getter;

@Getter
public final class ThreadSafeSingleton {

    private final String name;

    private final double amount;

    private static volatile ThreadSafeSingleton instance = null;

    private ThreadSafeSingleton(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /* The field would be read first time in the first if statement and  second time in the return statement.
       The field is declared volatile, which means it has to be re-fetched from memory every time it is accessed (more processing is required to access volatile variables)
       and can not be stored into a register by the compiler.
       When copied to the local variable and then used in both statements (if and return), the register optimization can be done by the JVM. */
    public static ThreadSafeSingleton getInstance(String name, double amount) {
        ThreadSafeSingleton query = instance;
        if (query == null) {
            synchronized (ThreadSafeSingleton.class) {
                query = instance;
                if (query == null) {
                    instance = new ThreadSafeSingleton(name, amount);
                }
            }
        }
        return instance;
    }

}


class DemoThreadSafeSingleton {

    public static void main(String[] args) {
        System.out.println("""
                If you see the same value, then singleton was reused
                If you see different values, then 2 singletons were created
                
                RESULT:
                """);
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("FOO", 1D);
            System.out.println(singleton.getName());
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("BAR", 2D);
            System.out.println(singleton.getName());
        }
    }
}