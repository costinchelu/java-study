package problems;

import java.util.Random;


public class MeanVarianceStdDeviation {
    public static void main(String[] args) {
        Random rand = new Random();

        int[] val = new int[2000];
        float mean = 0;
        float old_mean;
        float s = 0;

        int i;
        for (i = 0; i < 20; i++) {
            val[i] = rand.nextInt(500);
            System.out.println(val[i]);

            old_mean = mean;
            mean = mean + (val[i] - mean) / (i + 1);

            s = s + (val[i] - mean) * (val[i] - old_mean);
        }
        System.out.println("Mean = " + mean);
        System.out.println("Variance = " + s / (i - 1));
        System.out.println("Standard deviation (sample) =" + Math.sqrt(s / (i - 1)));
        System.out.println("Standard deviation (population) =" + Math.sqrt((s / i)));
    }
}

/* Wellford's method for computing variance

variance(samples):
  M := 0
  S := 0
  for k from 1 to N:
    x := samples[k]
    oldM := M
    M := M + (x - M) / k
    S := S + (x - M) * (x - oldM)
  return S / (N - 1)

 */