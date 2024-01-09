package behavioral.flyweight;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

interface ITravelPackage {

    void attachExtra(Extras optional);
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
    public void attachExtra(Extras extra) {
        System.out.println("Travel package code: " + packageCode);
        System.out.println("Travel destination: " + destination);
        System.out.println("Accommodation at hotel: " + hotel);
        System.out.println(breakfast ? "Has breakfast" : "No breakfast");
        System.out.println(extra.isDinner() ? "Has dinner" : "No dinner");
        System.out.println("Optional trips: " + extra.getOptionalTrips());
        System.out.println("--------------------------");
    }
}


@AllArgsConstructor
@Getter
@Setter
class Extras {

    private boolean dinner;

    private int optionalTrips;
}


class PackageFlyweightFactory {

    private final Map<Integer, ITravelPackage> travelPackages = new HashMap<>();

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
        Extras extra1 = new Extras(true, 3);
        Extras extra2 = new Extras(false, 3);
        Extras extra3 = new Extras(true, 1);
        Extras extra4 = new Extras(true, 5);

        ITravelPackage pack = factory.getOrSetPackage(1, "Inter", "Bucharest", true);
        pack.attachExtra(extra1);
        factory.getOrSetPackage(2, "California", "Los Angeles", false).attachExtra(extra2);
        extra2.setOptionalTrips(6);
        factory.getOrSetPackage(2, "California", "Los Angeles", false).attachExtra(extra2);
        factory.getOrSetPackage(3, "Tourist", "Prague", true).attachExtra(extra4);
        factory.getOrSetPackage(4, "Atlas", "Casablanca", true).attachExtra(extra3);

        factory.printMap();
    }
}
