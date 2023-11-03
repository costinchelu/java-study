package sec7d_polymorphismexercise.challenge;

class Car {
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;


    public Car(int cylinders, String name) {
        this.engine = true;
        this.cylinders = cylinders;
        this.name = name;
        this.wheels = 4;
    }

    public String getName() {
        return name;
    }

    public String startEngien() {
        return "Car -> startEngine()";
    }

    public String accelerate() {
        return  "Car -> accelerate()";
    }

    public String brake() {
        return "Car -> brake";
    }
}

class Mitsubishi extends Car {

    public Mitsubishi(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngien() {
        return "Mitsubishi -> startEngine()";
    }

    @Override
    public String accelerate() {
        return  "Mitsubishi -> accelerate()";
    }

    @Override
    public String brake() {
        return  "Mitsubishi -> brake()";
    }
}


public class Main {
    public static void main(String[] args) {
        Car car = new Car(8, "Base car");
        System.out.println(car.startEngien());
        System.out.println(car.accelerate());

        Mitsubishi mitsu = new Mitsubishi(6, "Outlander");
        System.out.println(mitsu.startEngien());
        System.out.println(mitsu.accelerate());

        Ford ford = new Ford(6, "Focus");
        System.out.println(ford.startEngien());
        System.out.println(ford.accelerate());
    }


}
