package section_2;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
 * <br>
 * When the client runs the {@code executeAll()},  the MultiExecutor,  will execute all the given tasks.
 * <br>
 * To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks in parallel, by passing each task to a different thread.
 */
@RequiredArgsConstructor
public class MultiExecutor {

    // @param tasks to executed concurrently
    private final List<Runnable> tasks;

     // Executes all the tasks concurrently
    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for(Thread thread : threads) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int taskNo = i + 1;
            tasks.add(() -> System.out.println("Task " + taskNo + " executed."));
        }
        MultiExecutor multiExecutor = new MultiExecutor(tasks);
        multiExecutor.executeAll();
    }
}
