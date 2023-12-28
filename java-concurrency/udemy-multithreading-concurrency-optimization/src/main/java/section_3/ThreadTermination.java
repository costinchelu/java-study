package section_3;

import lombok.AllArgsConstructor;

import java.math.BigInteger;

public class ThreadTermination {
}

class Main1 {
    public static void main(String [] args) {
        Thread newThread = new Thread(new BlockingTask());
        newThread.start();
        newThread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            //do things
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Existing blocking thread by interrupting the thread");
            }
        }
    }
}

class Main2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));
        thread.start();
        thread.interrupt();
    }

    @AllArgsConstructor
    private static class LongComputationTask implements Runnable {

        private BigInteger base;

        private BigInteger power;

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}

class Main3 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }

    private record LongComputationTask(BigInteger base, BigInteger power) implements Runnable {

        private LongComputationTask (BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }
}