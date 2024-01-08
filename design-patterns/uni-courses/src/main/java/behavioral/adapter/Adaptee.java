package behavioral.adapter;

import lombok.AllArgsConstructor;
import lombok.ToString;




record Vehicle(String model, double cc) { }



@AllArgsConstructor
@ToString
class RentedVehicle {

    private Vehicle vehicle;

    public void rentVehicle() {
        System.out.println("Vehicle rented: " + vehicle.toString());
    }
}




















public class Adaptee {
}
