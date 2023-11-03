package howto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TupleInJava {

    public static void main(String[] args) {
        Pair<String, Integer> example = new Pair<>("Str", 3);
        String first = example.getFirst();
        Integer second = example.getSecond();

        Pair<String, Integer> example2 = Pair.of("Str", 3);
        String f = example2.getFirst();
        Integer i = example2.getSecond();
    }
}

@RequiredArgsConstructor
@Getter
class Pair<A, B> {

    private final A first;

    private final B second;

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }
}
