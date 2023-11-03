package dp_behavioral.observer.everydayexample;

import java.util.Observable;
import java.util.Observer;

public class Main {

    public static void main(String[] args) {

        TwitterStream messageStream = new TwitterStream();

        Client client1 = new Client("Bryan");
        Client client2 = new Client("Mark");

        messageStream.addObserver(client1);
        messageStream.addObserver(client2);

        messageStream.someoneTweeted();
    }
}


// ConcreteSubject
class TwitterStream extends Observable {

    public void someoneTweeted() {
        setChanged();       // marks the object as changed (modified)
        notifyObservers();  // notify all observers that the object changed. After that, call clearChanged()
    }
}


// ConcreteObserver
class Client implements Observer {

    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update " + name + "'s stream, someone tweeted something.");
    }
}


/*
- usually we choose observer pattern, when a subject has one to many observers
- broadcast to pbservers
- when we are trying to decouple objects
- event handling
- publisher/subscriber
- MVC situations


java.util.Observer
java.util.EventListener
javax.jms.Topic

- can get unexpected updates
- debugging difficult
 */