package section_3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

/**
 * In this exercise we will efficiently calculate the following result = base1 ^ power1 + base2 ^ power2
 *<br>
 * Where a^b means: a raised to the power of b.
 *<br>
 * For example 10^2 = 100
 *<br>
 * We know that raising a number to a power is a complex computation, so we would like to execute:
 *<br>
 * result1 = x1 ^ y1
 *<br>
 * result2 = x2 ^ y2
 *<br>
 * In parallel.
 *<br>
 * and combine the result in the end : result = result1 + result2
 *<br>
 * This way we can speed up the entire calculation.
 *<br>
 * Note :
 *<br>
 * base1 >= 0, base2 >= 0, power1 >= 0, power2 >= 0
 */
public class ComplexCalculation {

    public BigInteger calculateResult(BigInteger base1,
                                      BigInteger power1,
                                      BigInteger base2,
                                      BigInteger power2) {
        BigInteger result;
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = thread1.getResult().add(thread2.getResult());
        return result;
    }

    @RequiredArgsConstructor
    private static class PowerCalculatingThread extends Thread {

        @Getter
        private BigInteger result = BigInteger.ONE;

        private final BigInteger base;

        private final BigInteger power;

        @Override
        public void run() {
            result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }
    }

    public static void main(String[] args) {
        ComplexCalculation cc = new ComplexCalculation();
        BigInteger bigInteger = cc.calculateResult(BigInteger.TWO, BigInteger.valueOf(4), BigInteger.valueOf(3), BigInteger.valueOf(3));
        System.out.printf(bigInteger.toString());
    }
}
