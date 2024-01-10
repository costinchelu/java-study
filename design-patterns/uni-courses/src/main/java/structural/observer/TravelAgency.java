package structural.observer;


import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// SubjectBase
interface Subject {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notify(String message);
}



// ConcreteSubject
@RequiredArgsConstructor
public class TravelAgency implements Subject {

    private final String agencyName;

    private List<Observer> observers = new ArrayList<>();


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer o: observers) {
            o.receiveMessage(message);
        }
    }

    public void notifyForANewOffer() {
        notify("We have a great new offer!");
    }

    public void notifyForADiscount() {
        notify("We have a discount on the \"Christmas in Thailand\" Package");
    }
}
