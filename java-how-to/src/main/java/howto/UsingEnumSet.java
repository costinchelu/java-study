package howto;

import java.util.EnumSet;

enum Vehicle {
    CAR,
    AEROPLANE,
    MOTORCYCLE,
    BOAT
}

public class UsingEnumSet {

    public static void main(String[] args) {
        EnumSet<Vehicle> TERRESTRIAL = EnumSet.of(Vehicle.CAR, Vehicle.MOTORCYCLE);
        if (TERRESTRIAL.contains(Vehicle.BOAT)) {
            System.out.println("Not terrestrial");
        }
    }
}
