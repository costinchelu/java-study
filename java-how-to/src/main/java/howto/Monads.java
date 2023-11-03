package howto;

import lombok.Data;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Monads {

    public static void main(String[] args) {
        Monads monads = new Monads();
        monads.mapExample();
        monads.flatMapExample();
        monads.optionalAdd();
        monads.optionalCompute();
        monads.optionalStreams();
        monads.optionalFlatten();
    }

    public void mapExample() {
        Wrap<Integer> a = Wrap.of(1);                      // Wrap(value=1)
        Wrap<Integer> b = a.map(i -> i + 9);                    // Wrap(value=10)
        Wrap<Integer> c = b.map(i -> i * 11);                   // Wrap(value=110)
        a.map(i -> i * 10).map(i -> i + 11);                    // Wrap(value=21)
    }

    public void flatMapExample() {
        Wrap<Integer> a = Wrap.of(1);                     // Wrap(value=1)
        a.flatMap(this::increment);                            // Wrap(value=2)
        a.flatMap(this::increment).flatMap(this::increment);   // Wrap(value=3)
    }

    public void optionalAdd() {
        Optional<Integer> a = Optional.of(13);
        Optional<Integer> b = Optional.of(42);
        add(a, b);                                              // Optional[55]
        add(a, Optional.empty());                           // Optional.empty
        add(Optional.empty(), b);                           // Optional.empty
    }

    public void optionalCompute() {
        Optional<Integer> a = Optional.of(13);
        Optional<Integer> b = Optional.of(42);
        BiFunction<Integer, Integer, Integer> plus = Integer::sum;
        BiFunction<Integer, Integer, Integer> times = (x, y) -> x * y;
        compute(plus, a, b);                                    // Optional[55]
        compute(times, a, b);                                   // Optional[546]
    }

    public void optionalStreams() {
        Optional<Integer> one = Optional.of(1);
        BiFunction<Integer, Integer, Integer> times = (x, y) -> x * y;

        Stream<Optional<Integer>> stream = Stream.of(1, 2, 3, 4).map(Optional::of);
        stream.reduce(one, (acc, elem) -> compute(times, acc, elem));       // Optional[24]

        stream = Stream.of(Optional.of(10), Optional.empty());
        stream.reduce(one, (acc, elem) -> compute(times, acc, elem));       // Optional.empty
    }

    public void optionalFlatten() {
        BiFunction<Integer, Integer, Integer> times = (x, y) -> x * y;

        Stream<Optional<Integer>> stream = Stream.of(1, 2, 3, 4).map(Optional::of);
        stream.reduce((acc, elem) -> compute(times, acc, elem));            // Optional[Optional[24]]

        Optional<Optional<Integer>> ooa = Optional.of(Optional.of(24));
        ooa.flatMap(o -> o);                                                // Optional[24]
    }

    private Wrap<Integer> increment(Integer x) {
        return Wrap.of(x + 1);
    }

    private Optional<Integer> add(Optional<Integer> oa, Optional<Integer> ob) {
        return oa.flatMap(a -> ob.map(b -> a + b));
    }

    private <A, B, R> Optional<R> compute(BiFunction<A, B, R> operation, Optional<A> oa, Optional<B> ob) {
        return oa.flatMap(a -> ob.map(b -> operation.apply(a, b)));
    }
}


@Data
class Wrap<T> {

    private final T value;

    public static <T> Wrap<T> of(T value) {
        return new Wrap<>(value);
    }

    public <R> Wrap<R> map(Function<T, R> mapper) {
        return flatMap(mapper.andThen(Wrap::of));
    }

//    public <R> Wrap<R> map(Function<T, R> mapper) {
//        return of(mapper.apply(value));
//    }

    public <R> Wrap<R> flatMap(Function<T, Wrap<R>> mapper) {
        return mapper.apply(value);
    }
}

interface Monad<T> {

    Monad<T> of(T value);

    <R> Wrap<R> map(Function<T, R> mapper);

    <R> Monad<R> flatMap(Function<T, Monad<R>> mapper);
}
