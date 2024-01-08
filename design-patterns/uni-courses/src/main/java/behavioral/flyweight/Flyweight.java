package behavioral.flyweight;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

interface ITravelPackage {

    void printComplete(Optionals optional);
}


@AllArgsConstructor
@Getter
@Setter
@ToString
class TravelPackage implements ITravelPackage {

    private int packageCode;

    private String hotel;

    private String destination;

    private boolean breakfast;

    @Override
    public void printComplete(Optionals optional) {
        System.out.println("Travel package code: " + packageCode);
        System.out.println("Travel destination: " + destination);
        System.out.println("Accommodation at hotel: " + hotel);
        System.out.println(breakfast ? "Has breakfast" : "No breakfast");
        System.out.println(optional.isDinner() ? "Has dinner" : "No dinner");
        System.out.println("Optional trips: " + optional.getOptionalTrips());
        System.out.println("--------------------------");
    }
}


@AllArgsConstructor
@Getter
@Setter
class Optionals {

    private boolean dinner;

    private int optionalTrips;
}


class PackageFlyweightFactory {

    private final Map<Integer, ITravelPackage> travelPackages = new HashMap<>();

    public int count() {
        return travelPackages.size();
    }

    public ITravelPackage getOrSetPackage(int packageCode, String hotel, String destination, boolean breakfast) {
        ITravelPackage travelPackage = travelPackages.get(packageCode);
        if (travelPackage == null) {
            travelPackage = new TravelPackage(packageCode, hotel, destination, breakfast);
            travelPackages.put(packageCode, travelPackage);
        }
        return travelPackage;
    }

    public void printMap() {
        travelPackages.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}



public class Flyweight {

    public static void main(String[] args) {
        PackageFlyweightFactory factory = new PackageFlyweightFactory();
        Optionals o1 = new Optionals(true, 3);
        Optionals o2 = new Optionals(false, 3);
        Optionals o3 = new Optionals(true, 1);
        Optionals o4 = new Optionals(true, 5);

        ITravelPackage pack = factory.getOrSetPackage(1, "Inter", "Bucharest", true);
        pack.printComplete(o1);
        factory.getOrSetPackage(2, "California", "Los Angeles", false).printComplete(o2);
        o2.setOptionalTrips(6);
        factory.getOrSetPackage(2, "California", "Los Angeles", false).printComplete(o2);
        factory.getOrSetPackage(3, "Tourist", "Prague", true).printComplete(o4);
        factory.getOrSetPackage(4, "Atlas", "Casablanca", true).printComplete(o3);

        factory.printMap();
    }
}
