package dp_creational.singleton.differentsingletons;

public class LazilyLoadedSingleton implements DifferentSingletons {

    private static LazilyLoadedSingleton instance = null;

    private LazilyLoadedSingleton() { }

    public static LazilyLoadedSingleton getInstance() {
        if (instance == null) {
            instance = new LazilyLoadedSingleton();
        }
        return instance;
    }

    // this could be more efficient than eagerly loading the singleton. It will create the singleton only when needed
}
