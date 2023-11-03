package dp_creational.singleton.differentsingletons;

public class EagerlyLoadedSingleton implements DifferentSingletons {

    private static EagerlyLoadedSingleton instance = new EagerlyLoadedSingleton();
    // we are creating it when we are starting the program

    private EagerlyLoadedSingleton() { }

    public static EagerlyLoadedSingleton getInstance() {
        return instance;
    }
}
