package chapter3.workperiod;

import java.time.LocalDateTime;

public class WorkPeriod {

    private final LocalDateTime start;
    private final LocalDateTime end;

    private WorkPeriod(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    // factory
    public static WorkPeriod of(LocalDateTime start, LocalDateTime end) {
        return new WorkPeriod(start, end);
    }

    @Override
    public String toString() {
        return start + " " + end;
    }
}
