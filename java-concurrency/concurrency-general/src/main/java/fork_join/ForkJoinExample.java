package fork_join;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class ForkJoinExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        Stream<Integer> stream = numbers.stream()
                .parallel()
                .map(ForkJoinExample::transform);

        process(stream);
    }

    private static <R> void process(Stream<R> stream) {
        ForkJoinPool pool = new ForkJoinPool(50);
        pool.submit(() -> stream.forEach(e -> {}));
        pool.shutdown();
        try {
            boolean b = pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static <R> R transform(R number) {
        System.out.println("TRANSFORM: " + number + " " + Thread.currentThread().getName());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }
}
