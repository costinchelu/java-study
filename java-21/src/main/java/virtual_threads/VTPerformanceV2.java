package virtual_threads;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class VTPerformanceV2 {

    public static void main(String[] args) throws InterruptedException {

        Set<String> poolNames = ConcurrentHashMap.newKeySet();
        Set<String> pThreadsNames = ConcurrentHashMap.newKeySet();

        /*
        No of (platform) threads we can have:

                   # of CPU Cores
        T <= -----------------------------
                 1 - blocking factor

        0 <= blocking factor < 1
        >> fraction of time a thread waits for IO / blocking calls
        >> if your task block for 50% of the time, then T <= 2 * # of Cores
         */
        List<Thread> threads = IntStream.range(0, 10_000_000).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
            poolNames.add(readPoolName());
            pThreadsNames.add(readWorkerName());
        })).toList();

        Instant begin = Instant.now();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        Instant end = Instant.now();
        System.out.println("Time = " + Duration.between(begin, end).toMillis() + " ms");
        System.out.println("# of cores = " + Runtime.getRuntime().availableProcessors());
        System.out.println("# of pools = " + poolNames.size());
        System.out.println("# of platform threads = " + pThreadsNames.size());
    }

    private static String readPoolName() {
        String name = Thread.currentThread().toString();
        int i1 = name.indexOf("@ForkJoinPool");
        int i2 = name.indexOf("worker");
        return name.substring(i1, i2);
    }

    private static String readWorkerName() {
        String name = Thread.currentThread().toString();
        int i1 = name.indexOf("worker");
        return name.substring(i1);
    }
}
