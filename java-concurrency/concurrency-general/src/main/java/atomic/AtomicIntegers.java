package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static sync.ConcurrentUtils.stop;

/*
* The package java.concurrent.atomic contains many useful classes to perform atomic operations. 
* An operation is atomic when you can safely perform the operation in parallel on multiple threads 
* without using the synchronized keyword or locks
* */
public class AtomicIntegers {

    public static void main(String[] args) {
        
        example1atomic();
        example2atomic();
        example3atomic();
        example4atomic();
    }

    private static void example1atomic() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        stop(executor);

        System.out.println(atomicInt.get());    // => 1000

        /*
        * By using AtomicInteger as a replacement for Integer we're able to increment the number concurrently
        * in a thread-safe manor without synchronizing the access to the variable.
        * The method incrementAndGet() is an atomic operation so we can safely call this method from multiple threads.
        * */
    }

    private static void example2atomic() {
        /*
        * AtomicInteger supports various kinds of atomic operations.
        * The method updateAndGet() accepts a howto.stream_api.lambda expression in order to perform
        * arbitrary arithmetic operations upon the integer
        * */
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(n -> n + 2);
                            executor.submit(task);
                });

        stop(executor);

        System.out.println(atomicInt.get());    // => 2000
    }

    private static void example3atomic() {
        /*
        * The method accumulateAndGet() accepts another kind of howto.stream_api.lambda expression of type IntBinaryOperator.
        * We use this method to sum up all values from 0 to 1000 concurrently
        * */
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, Integer::sum);
                            executor.submit(task);
                });

        stop(executor);

        System.out.println(atomicInt.get());    // => 499500
    }

    private static void example4atomic() {
        /*
        * The class LongAdder as an alternative to AtomicLong can be used to consecutively add values to a number.
        * LongAdder provides methods add() and increment() just like the atomic number classes
        * and is also thread-safe. But instead of summing up a single result this class maintains
        * a set of variables internally to reduce contention over threads.
        * The actual result can be retrieved by calling sum() or sumThenReset(). This class is usually preferable
        * over atomic numbers when updates from multiple threads are more common than reads.
        * This is often the case when capturing statistical data, e.g. you want to count the number of requests
        * served on a web server. The drawback of LongAdder is higher memory consumption
        * because a set of variables is held in-memory.
        * */
        ExecutorService executor = Executors.newFixedThreadPool(2);
        LongAdder adder = new LongAdder();

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));

        stop(executor);

        System.out.println(adder.sumThenReset());   // => 1000
    }

    private static void example5atomic() {
        /*
        * LongAccumulator is a more generalized version of LongAdder.
        * Instead of performing simple add operations the class LongAccumulator builds around a howto.stream_api.lambda expression
        * of type LongBinaryOperator. We create a LongAccumulator with the function 2 * x + y
        * and an initial value of one. With every call to accumulate(i) both the current result and the value i
        * are passed as parameters to the howto.stream_api.lambda expression. A LongAccumulator
        * just like LongAdder maintains a set of variables internally to reduce contention over threads.
        * */
        LongBinaryOperator op = (x, y) -> 2 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        stop(executor);

        System.out.println(accumulator.getThenReset());     // => 2539
    }

}
