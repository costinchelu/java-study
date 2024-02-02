package structural.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// originator
@AllArgsConstructor
@Getter
@Setter
@ToString
class TravelPackage {

    private double price;

    private String name;

    public MementoTP savePriceToMemento() {
        return new MementoTP(price);
    }

    public void getFromMemento(MementoTP memento) {
        this.price = memento.getMemPrice();
    }
}



// Memento
@Getter
@AllArgsConstructor
class MementoTP {

    private double memPrice;
}



// Caretaker
class PackageManager {

    private final List<MementoTP> list = new ArrayList<>();

    public void pushToMemento(MementoTP memento) {
        list.add(memento);
    }

    public MementoTP getMementoAtIndex(int index) {
        return list.get(index);
    }

    public MementoTP popMemento() {
        if (!list.isEmpty()) {
            int index = list.size() - 1;
            MementoTP memento = list.get(index);
            list.remove(index);
            return memento;
        } else {
            throw new RuntimeException("No entries!");
        }
    }
}



public class Memento {

    public static void main(String[] args) {
        PackageManager manager = new PackageManager();
        TravelPackage tp = new TravelPackage(10, "TP 1");

        manager.pushToMemento(tp.savePriceToMemento());
        System.out.println(tp);
        tp.setPrice(50);
        System.out.println(tp);
        tp.setPrice(100);
        manager.pushToMemento(tp.savePriceToMemento());
        System.out.println(tp);
        tp.setPrice(150);
        manager.pushToMemento(tp.savePriceToMemento());
        System.out.println(tp);
        tp.setPrice(200);
        System.out.println(tp);

        try {
            tp.getFromMemento(manager.popMemento());
            System.out.println(tp);
            tp.getFromMemento(manager.popMemento());
            System.out.println(tp);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
