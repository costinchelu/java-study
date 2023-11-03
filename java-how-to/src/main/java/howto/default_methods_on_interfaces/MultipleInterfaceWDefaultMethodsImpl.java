package howto.default_methods_on_interfaces;

public class MultipleInterfaceWDefaultMethodsImpl implements A, B {

    public static void main(String[] args) {
        MultipleInterfaceWDefaultMethodsImpl test = new MultipleInterfaceWDefaultMethodsImpl();
        test.hello();
    }

    @Override
    public void hello() {
        B.super.hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void hello() {
        System.out.println("Hello from B");
    }
}
