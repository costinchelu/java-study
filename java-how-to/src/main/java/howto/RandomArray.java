package howto;

public class RandomArray {

    // Create a random array with random values
    public static double[] createRandomArray(int arrLength) {
        double[] a = new double[arrLength];
        for (int i = 0; i < arrLength; i++) {
            a[i] = Math.random() * 1.3 - Math.random();
        }
        for (int i = 0; i < arrLength; i++) {
            System.out.printf(String.format("%.4f", a[i]) + " \t");
        }
        return a;
    }


    // Create a matrix with random values
    public static double[][] createRandomMatrix(int line, int column) {
        double[][] m = new double[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                m[i][j] = Math.random();
                System.out.print(String.format("%.4f", m[i][j]) + "  ");
            }
            System.out.println();
        }
        return m;
    }
}
