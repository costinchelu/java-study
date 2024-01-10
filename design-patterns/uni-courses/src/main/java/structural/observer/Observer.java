package structural.observer;

import lombok.RequiredArgsConstructor;

// ObserverBase
public interface Observer {

    void receiveMessage(String message);
}



// ConcreteObserver
@RequiredArgsConstructor
class EmailClient implements Observer {

    private final String name;

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " you have a new message: " + message);
    }
}


// Concrete Observer
@RequiredArgsConstructor
class RegularClient implements Observer {

    private final String name;

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " you have a new message:" + message);
    }
}