package vavrdemo;

import io.vavr.Function1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrComposition {

    public void functionCompositionDemo() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> deductOne = a -> a - 1;

        Function1<Integer, Integer> add1MultiplyBy2deduct1 = plusOne.andThen(multiplyByTwo).andThen(deductOne);
        int value = add1MultiplyBy2deduct1.apply(5);

        if (value == 11) {
            log.info("---> Function composition");
        }
    }
}
