package concurrent_dt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class ConcurrentAccumulators {

    public static void main(String[] args) {
        LongAccumulator balance = new LongAccumulator(Long::sum, 10_000L);

        Runnable w = () -> balance.accumulate(1000L);

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executorService.submit(w);
        }
        executorService.shutdown();

        try {
            if (executorService.awaitTermination(1000L, TimeUnit.MILLISECONDS)) {
                System.out.println("Balance: " + balance.get());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
