package howto.polymorphism;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
abstract class Vehicle {

    private final int numberOfWheels;

    public abstract void drive();
}


@Getter
class Car extends Vehicle {

    private final String brand;

    private final String model;

    public Car(String brand, String model) {
        super(4);
        this.brand = brand;
        this.model = model;
    }

    @Override
    public void drive() {
        System.out.println("Driving a car!");
    }
}


@Getter
class Bike extends Vehicle {

    private final int numberOfGears;

    public Bike(int numberOfGears) {
        super(2);
        this.numberOfGears = numberOfGears;
    }

    @Override
    public void drive() {
        System.out.println("Cycling along");
    }
}


class Train extends Vehicle {

    public Train(int numberOfWheels) {
        super(numberOfWheels);
    }

    @Override
    public void drive() {
        System.out.println("Choo choo!");
    }
}


@AllArgsConstructor
class Driver {

    private final String name;

    private final Vehicle vehicle;

    public void drive() {
        System.out.println(name + " is starting to drive...");
        vehicle.drive();
    }
}



public class ExamplePolymorphism {

    public static void main(String[] args) {
        var car = new Car("VW", "Golf");
        var bike = new Bike(3);
        var train = new Train(80);

        var driver1 = new Driver("John", bike);
        var driver2 = new Driver("Dan", car);
        var driver3 = new Driver("Alex", train);

        driver1.drive();
        driver2.drive();
        driver3.drive();

        var vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Car("BMW", "1"));
        vehicles.add(new Bike(16));
        vehicles.add(new Train(80));

        for (var vehicle : vehicles) {
            System.out.printf("Number of wheels: %d%n", vehicle.getNumberOfWheels());

            switch (vehicle) {
                case Bike bike2 -> System.out.printf("Number of gears: %d%n", bike2.getNumberOfGears());
                case Car car2 -> System.out.printf("Brand and model: %s %s%n", car2.getBrand(), car2.getModel());
                case Train train2 -> System.out.printf("Number of wheels: %d", train2.getNumberOfWheels());
                default -> System.out.println("This is just a generic Vehicle");
            }
        }
    }
}
