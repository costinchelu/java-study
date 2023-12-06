package howto.callbacks;

public class CallbacksDemo2 {
}

// Java program to illustrate synchronous callback
interface SomeEventListener {

    // this can be any type of method
    void someEvent();
}

class B {

    private SomeEventListener mListener;

    // setting the listener
    public void registerSomeEventListener(SomeEventListener mListener) {
        this.mListener = mListener;
    }

    // my synchronous task
    public void doSomeStuff() {
        // perform any operation
        System.out.println("Performing callback before synchronous Task");

        if (this.mListener != null) {
            // invoke the callback method of class A
            mListener.someEvent();
        }
    }

    public static void main(String[] args) {
        SomeEventListener mListener = new A();
        B obj = new B();
        obj.registerSomeEventListener(mListener);
        obj.doSomeStuff();
    }
}

class C {

    private SomeEventListener mListener;

    public void registerOnGeekEventListener(SomeEventListener mListener) {
        this.mListener = mListener;
    }

    // my asynchronous task
    public void doSomeStuff() {
        // An Async task always executes in new thread

        new Thread(() -> {
            // perform any operation
            System.out.println("Performing operation in Asynchronous Task");
            if (mListener != null) {
                // invoke the callback method of class A
                mListener.someEvent();
            }
        }).start();
    }

    public static void main(String[] args) {
        C obj = new C();
        SomeEventListener mListener = new A();
        obj.registerOnGeekEventListener(mListener);
        obj.doSomeStuff();
    }
}

class A implements SomeEventListener {

    @Override
    public void someEvent() {
        System.out.println("Performing callback after synchronous Task");
        // perform some routine operation
    }
    // some class A methods
}

