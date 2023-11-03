package problems.math;

abstract class Progression {

    protected long current;

    public Progression(long startValue) {
        current = startValue;
    }

    public long nextValue() {
        long answer = current;
        advance();
        return answer;
    }

    protected abstract void advance();

    public void printProgression(int n) {
        System.out.print(nextValue());
        for (int j = 1; j < n; j++) {
            System.out.print(" " + nextValue());
        }
        System.out.println();
    }
}

class ArithmeticProgression extends Progression {

    protected long step;

    public ArithmeticProgression(long stepSize, long startValue) {
        super(startValue);
        step = stepSize;
    }

    public ArithmeticProgression(long stepSize) {
        this(stepSize, 0);
    }

    public ArithmeticProgression() {
        this(1, 0);
    }

    @Override
    protected void advance() {
        current += step;
    }
}

class GeometricProgression extends Progression {

    protected long base;

    public GeometricProgression(long base, long startValue) {
        super(startValue);
        this.base = base;
    }

    public GeometricProgression(long base) {
        this(base, 1);
    }

    public GeometricProgression() {
        this(2, 1);
    }

    @Override
    protected void advance() {
        current *= base;
    }
}

class FibonacciProgression extends Progression {

    protected long prev;

    public FibonacciProgression(long first, long second) {
        super(first);
        prev = second - first;
    }

    public FibonacciProgression() {
        this(0, 1);
    }

    @Override
    protected void advance() {
        long temp = prev;
        prev = current;
        current += temp;
    }
}

//Test program for the progression hierarchy.
public class MathProgression {

    public static void main(String[] args) {
        Progression prog;

        // test ArithmeticProgression
        System.out.print("Arithmetic progression with default increment: ");
        prog = new ArithmeticProgression();
        prog.printProgression(10);
        System.out.print("Arithmetic progression with increment 5: ");
        prog = new ArithmeticProgression(5);
        prog.printProgression(10);
        System.out.print("Arithmetic progression with start 2: ");
        prog = new ArithmeticProgression(1, 2);
        prog.printProgression(10);

        // test GeometricProgression
        System.out.print("Geometric progression with default base: ");
        prog = new GeometricProgression();
        prog.printProgression(10);
        System.out.print("Geometric progression with base 3: ");
        prog = new GeometricProgression(3);
        prog.printProgression(10);

        // test FibonacciProgression
        System.out.print("Fibonacci progression with default start values: ");
        prog = new FibonacciProgression();
        prog.printProgression(10);
        System.out.print("Fibonacci progression with start values 4 and 6: ");
        prog = new FibonacciProgression(4, 6);
        prog.printProgression(10);
    }
}