package sec15j_moreondeadlocks;

public class Deadlocks2 {
    public static void main(String[] args) {
        PolitePerson jane = new PolitePerson("Jane");
        PolitePerson john = new PolitePerson("John");


        // this will produce a deadlock:

        // 1. Thread1 acquires the lock on the jane object and enters the sayHello() method.
        // It prints to the console, then suspends.
        // 2. Thread2 acquires the lock on the john object and enters the sayHello() method.
        // It prints to the console, then suspends.
        // 3. Thread1 runs again and wants to say hello back to the john object. It tries to call the sayHelloBack() method
        // using the john object that was passed into the sayHello() method,
        // but Thread2 is holding the john lock, so Thread1 suspends.
        // 4. Thread2 runs again and wants to say hello back to the jane object. It tries to call the sayHelloBack() method
        // using the jane object that was passed into the sayHello() method,
        // but Thread1 is holding the jane lock, so Thread2 suspends.


        new Thread(new Runnable() {
            @Override
            public void run() {
                jane.sayHello(john);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                john.sayHello(jane);
            }
        }).start();

        // without threads the code would run fine
    }

    static class PolitePerson {
        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // using System.out.format to result a deadlock on the object
        public synchronized void sayHello(PolitePerson theOtherPerson) {
            System.out.format("%s: %s" + " has said hello to me!%n", this.name, theOtherPerson.getName());
            theOtherPerson.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson theOtherPerson) {
            System.out.format("%s: %s" + " has said hello back to me!%n", this.name, theOtherPerson.getName());
        }

        /*
        * because both methods are synchronized, only one thread can access a certain resource, so the other thread
        * will just wait. Of course the other thread will lock on the second resource and because it's a synchronized
        * method it cannot be accessed by the first thread
        */
    }
}
