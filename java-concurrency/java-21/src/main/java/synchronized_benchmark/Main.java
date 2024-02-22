package synchronized_benchmark;

/**
 * Pinning of virtual threads with synchronized blocks significantly hinders performance when concurrent threads exceed
 * the available cores.
 * This is due to the carrier thread’s inability to yield the processor even if a thread is blocked or waiting,
 * leading to an increase in execution time which is proportional to the blocking duration and the extent
 * to which thread count surpasses core count.
 * In this case, the total execution time for virtual threads became 8 seconds and 12 seconds
 * as the number of concurrent threads exceeded 12 and 24 respectively, while the total execution time
 * for platform threads remained constant at 4 seconds .
 *
 * The same behavior is seen if a blocking network call is made in the synchronized block.
 *
 * While virtual threads can offer performance benefits in high-throughput workloads involving
 * long-running I/O requests, it’s crucial to avoid using synchronized blocks or native methods
 * within those to prevent performance degradation.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Total Available Cores " + Runtime.getRuntime().availableProcessors());
        System.out.println("========================");
        int[] threadsCountList = new int[] {2, 4, 8, 11, 13, 16, 23, 25, 32, 64, 84, 100};
        for (int threads : threadsCountList) {
            System.out.println("Thread Count = " + threads);
            run(ThreadType.PLATFORM, threads);
            run(ThreadType.VIRTUAL, threads);
            System.out.println("------------------------");
        }
    }

    static void run(ThreadType threadType, int threads) {
        long start = System.currentTimeMillis();

        TaskManager taskManager = new TaskManager(threadType, threads);
        taskManager.start();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(
                "Thread Type: "
                        + String.format("%-10s" , threadType)
                        + " Time: "
                        + timeElapsed
                        + " ms");
    }
}
