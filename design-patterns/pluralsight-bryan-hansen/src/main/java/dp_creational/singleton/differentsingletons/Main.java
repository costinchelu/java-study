package dp_creational.singleton.differentsingletons;

public class Main {

    public static void main(String[] args) {

        // v. 0.1 - eagerly loaded singleton
        DifferentSingletons instance1 = EagerlyLoadedSingleton.getInstance();
        System.out.println(instance1);

        DifferentSingletons instance2 = EagerlyLoadedSingleton.getInstance();
        System.out.println(instance2);
        // we will get the same object (although it's not thread safe)


        // v. 0.2 - eagerly loaded singleton
        DifferentSingletons instance3 = LazilyLoadedSingleton.getInstance();
        System.out.println(instance3);

        DifferentSingletons instance4 = LazilyLoadedSingleton.getInstance();
        System.out.println(instance4);


        // v. 0.3 - synchronized singleton
        DifferentSingletons instance5 = ThreadSafeSingleton.getInstance();
        System.out.println(instance5);

        DifferentSingletons instance6 = ThreadSafeSingleton.getInstance();
        System.out.println(instance6);

    }
}
/*
Ensure a class only has one instance and provide a global point of access to it.
 */