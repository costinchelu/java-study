package howto.record_vs_class;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Check source: <a href = "https://www.baeldung.com/java-microbenchmark-harness" >Baeldung article</a>
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(value=3)
public class Bench {

    private static final RegularPoint REGULAR_ORIGIN = new RegularPoint(0, 0);
    private static final RecordPoint RECORD_ORIGIN = new RecordPoint(0, 0);

    private List<RegularPoint> regularPoints;
    private List<RecordPoint> recordPoints;

    @Setup
    public void setup() {
        regularPoints = IntStream.range(0, 16)
                .mapToObj(i -> new RegularPoint(i, i))
                .toList();

        recordPoints = IntStream.range(0, 16)
                .mapToObj(i -> new RecordPoint(i, i))
                .toList();
    }

    @Benchmark
    public void regular(Blackhole bh) {
        for (RegularPoint point: regularPoints) {
            if (point.x() == REGULAR_ORIGIN.x() && point.y() == REGULAR_ORIGIN.y()) {
                bh.consume(1);
            } else {
                bh.consume(0);
            }
        }
    }

    @Benchmark
    public void record(Blackhole bh) {
        for (RecordPoint point: recordPoints) {
            if (point.x() == RECORD_ORIGIN.x() && point.y() == RECORD_ORIGIN.y()) {
                bh.consume(1);
            } else {
                bh.consume(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

}
