package vavrdemo;


import io.vavr.Function3;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Vavr functional interfaces are Java 8 functional interfaces on steroids. They also provide features like:
 * Composition,
 * Lifting,
 * Currying,
 * Memoization,
 */
@Component
@Slf4j
public class VavrFunctions {

    public void vavrFunctionsDemo() {
        log.info("--> Function3 -->");
        log.info(methodReturningAFunction3().apply(1, 2, 3));

        Function3<Integer, Integer, Integer, String> function3 = Function3.of(this::methodWhichAccepts3Parameters);
        log.info("--> Function3 -->");
        log.info(function3.apply(4, 5, 6));
    }

    private Function3<Integer, Integer, Integer, String> methodReturningAFunction3() {
        return this::methodWhichAccepts3Parameters;
    }

    private String methodWhichAccepts3Parameters(Integer integer, Integer integer2, Integer integer3) {
        return List.of(integer, integer2, integer3)
                .map(String::valueOf)
                .map(e -> "str(" + e + ")")
                .intersperse(", ")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
    }
}
