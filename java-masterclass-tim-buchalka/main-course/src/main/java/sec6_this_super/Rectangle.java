package sec6_this_super;

class Shape {
    private int x;
    private int y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Rectangle extends Shape {
    private int width;
    private int length;

    //constructor chaining:

    public Rectangle() {
        this(0, 0);                      //calls the 2nd constructor
    }

    public Rectangle(int width, int length) {
        this(0, 0, width, length);              //calls the 3rd constructor
    }

    public Rectangle(int x, int y, int width, int length) {
        super(x, y);
        this.width = width;
        this.length = length;
    }
}
