package howto;

public class Timeit {

    public static void code(Runnable block) {
        long start = System.nanoTime();
        try {
            block.run();
        } finally {
            long end = System.nanoTime();
            int seconds = (int)Math.floor((end - start)/1.0e9);
            double milliseconds = (end - start)/1.0e6 - (seconds * 1.0e3);
            System.out.println("Time taken: " + seconds + " s" + " " + milliseconds + " ms");
        }
    }
}
