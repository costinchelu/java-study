package section_3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoiningThreads {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true); // even if we try a timed join, being user threads, some of them will still run and this will overdue the timeout
            // by assigning them as daemon threads, we leave the JVM terminate the program even if some of them are still running
            thread.start();  // race condition between the start of the thread, and the isFinished() in the main thread (some operations are too computation intensive)
        }

        for (Thread thread : threads) {
            thread.join(2000);  // force the main thread to wait for other threads will finish
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    @RequiredArgsConstructor
    public static class FactorialThread extends Thread {

        private final long inputNumber;

        @Getter
        private BigInteger result = BigInteger.ZERO;

        private boolean isFinished = false;

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
