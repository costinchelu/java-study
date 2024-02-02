package structural.observer;

public class ObserverDemo {

    public static void main(String[] args) {
        TravelAgency subject = new TravelAgency("Sunrise");
        Observer c1 = new RegularClient("George");
        Observer c2 = new EmailClient("Anne");
        Observer c3 = new RegularClient("Paul");
        Observer c4 = new EmailClient("Fred");
        Observer c5 = new RegularClient("Joanna");
        subject.addObserver(c1);
        subject.addObserver(c2);
        subject.addObserver(c3);
        subject.addObserver(c4);
        subject.addObserver(c5);

        subject.notifyForANewOffer();

        subject.removeObserver(c2);

        subject.notifyForADiscount();
    }
}
