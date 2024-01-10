package structural.state;

import lombok.Getter;
import lombok.Setter;

// StateBase
public interface State {

    void doActionOn(Reservation reservation);
}


// Context
@Getter
@Setter
class Reservation {

    private final int id;

    private State state;

    public Reservation(int id) {
        this.id = id;
        this.state = null;
    }
}

class Done implements State {

    @Override
    public void doActionOn(Reservation reservation) {
        System.out.println(" Reservation no " + reservation.getId() + " has now a DONE state");
        reservation.setState(this);
    }
}

class NotPayed implements State {

    @Override
    public void doActionOn(Reservation reservation) {
        System.out.println(" Reservation no " + reservation.getId() + " has now a NOT PAYED state");
        reservation.setState(this);
    }
}

class Payed implements State {

    @Override
    public void doActionOn(Reservation reservation) {
        System.out.println(" Reservation no " + reservation.getId() + " has now a PAYED state");
        reservation.setState(this);
    }
}



class StateDemo {

    public static void main(String[] args) {
        State notPayed = new NotPayed();
        State payed = new Payed();
        State done = new Done();
        Reservation r1 = new Reservation(1521);
        Reservation r2 = new Reservation(1528);

        notPayed.doActionOn(r1);
        payed.doActionOn(r1);
        notPayed.doActionOn(r2);
        done.doActionOn(r1);
        payed.doActionOn(r2);
        done.doActionOn(r2);
    }
}