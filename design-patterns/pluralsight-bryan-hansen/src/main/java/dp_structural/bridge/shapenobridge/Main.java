package dp_structural.bridge.shapenobridge;

public class Main {

    public static void main(String[] args) {

        Circle circle = new BlueCircle();
        Square square = new BlueSquare();

        circle.applyColor();
        square.applyColor();
    }
}
/*
it seems that for every shape and color we should create a new class. That's not very efficient
 */