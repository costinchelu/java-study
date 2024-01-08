package behavioral.adapter;

import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
class VehicleRentServiceO implements ITarget {

    private final RentedVehicle rentedVehicle;

    @Override
    public void description() {
        System.out.println(this.rentedVehicle.toString());
    }

    @Override
    public void bookPackage() {
        this.rentedVehicle.rentVehicle();
    }
}



class AdapterDemo {

    public static void main(String[] args) {
        ITarget accommodation = new Accommodation();
        printReservation(accommodation);

        ITarget rent = new VehicleRentServiceO(new RentedVehicle(new Vehicle("Skoda Octavia", 2.0)));
        printReservation(rent);

        ITarget rentC = new VehicleRentServiceC(new Vehicle("Skoda Octavia", 2.0));
        printReservation(rentC);
    }

    private static void printReservation(ITarget travelPackage) {
        System.out.println("For customer: ");
        travelPackage.description();
        System.out.println("For operator: ");
        travelPackage.bookPackage();
        System.out.println();
    }
}







public class ObjectAdapter {}


