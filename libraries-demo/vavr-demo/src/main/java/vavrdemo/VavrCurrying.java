package vavrdemo;

import io.vavr.Function1;
import io.vavr.Function4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrCurrying {

    public void vavrCurryingDemo() {
        log.info("---> Currying");


        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d) -> a + b + c + d;
        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> add2 = sum.curried().apply(2);

        int result = add2.apply(4).apply(3).apply(4);
        log.info("Result: {}", result);
    }
}
