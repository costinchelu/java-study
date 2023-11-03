package open_closed_example;

public class OpenClosedExample {

    public static void main(String[] args) {
        Car car1 = new Car(2020, new TurboEngine());
        System.out.println(car1);

        // if we want to copy the first car:
        Car car2 = new Car(car1);
        System.out.println(car2);
    }
}

class Car {
    private int year;
    private Engine engine;

    public Car(int year, Engine engine) {
        this.year = year;
        this.engine = engine;
    }

    public Car(Car otherCar) {
        year = otherCar.year;

        // for deep copy
        // a wrong approach to copy the engine would be:
//        if(otherCar.engine instanceof TurboEngine) {
//            engine = new TurboEngine((TurboEngine) otherCar.engine);
//        } else if (otherCar.engine instanceof PistonEngine) {
//            engine = new PistonEngine((PistonEngine) otherCar.engine);
//        } else {
//            engine = new Engine(otherCar.engine);
//        }
        // ^^ so what if we need other types of engines?
        // we would need to modify again the constructor of the car class
        // this could be problematic and could potentially determine side effects in other part of the program
        // or could determine the need for change in other classes/methods too


        // a good approach for open for extension, closed for change
        // now we can add as many engines that we want
        engine = otherCar.engine.copyEngine();
    }

    @Override
    public String toString() {
        return "Car{ " + year + ", " + engine + " }";
    }
}

class Engine {
    public Engine() {
    }

    protected Engine(Engine other) {
        // just to let us copy an engine by using this constructor
    }

    // still this will break the DRY principle
    public Engine copyEngine() {
        // like a copy constructor
        return new Engine(this);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ":" + hashCode();
    }
}

class TurboEngine extends Engine {
    public TurboEngine() {
    }

    protected TurboEngine(TurboEngine other) {
    }

    public Engine copyEngine() {
        return new TurboEngine(this);
    }
}

class PistonEngine extends Engine {
    public PistonEngine() {
    }

    protected PistonEngine(PistonEngine other) {
    }

    public Engine copyEngine() {
        return new PistonEngine(this);
    }
}

