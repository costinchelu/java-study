package dp_creational.singleton.differentsingletons;

public class ThreadSafeSingleton implements DifferentSingletons {

    private static ThreadSafeSingleton instance = null;

    private ThreadSafeSingleton() { }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
    // the outer if is not thread safe but we still have to check if it's null (because in that case we just
    // want to return that instance and move on
    // so we are using synchronized only if it's not null (synchronization will slow down the class anyway)
}
