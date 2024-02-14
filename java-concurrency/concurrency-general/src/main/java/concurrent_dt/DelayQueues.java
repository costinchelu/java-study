package concurrent_dt;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

record DelayedEvent(long startTime, String msg) implements Delayed {

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.startTime - ((DelayedEvent) o).startTime);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
}

public class DelayQueues {

    public static void main(String[] args) {
        final DelayQueue<DelayedEvent> delayQueue = new DelayQueue<>();
        final long timeFirst = System.currentTimeMillis() + 5_000;
        final long timeSecond = timeFirst + 5_000;
        delayQueue.offer(new DelayedEvent(timeFirst, "1"));
        delayQueue.offer(new DelayedEvent(timeSecond, "2"));
        System.out.println("delay queue");
        try {
            System.out.println(delayQueue.take().msg());
            System.out.println(delayQueue.take().msg());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
