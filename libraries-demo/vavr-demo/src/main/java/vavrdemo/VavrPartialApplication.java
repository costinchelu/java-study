package vavrdemo;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function4;
import io.vavr.Function5;
import io.vavr.Function8;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrPartialApplication {
    public void vavrPartialApplicationDemo() {
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sum.apply(1, 2, 3);
        Integer add9 = add6.apply(4, 5);

        if (add9.equals(15)) {
            log.info("---> Partial application");
        }

        Function1<Integer, Integer> add000 = getIntegerFunction1();
        log.info(String.valueOf(getIntegerFunction1().apply(8)));
    }

    private Function1<Integer, Integer> getIntegerFunction1() {
        Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> sum =
                (a, b, c, d, e, f, g, h) -> a + b + c + d + e + f + g + h;

        Function4<Integer, Integer, Integer, Integer, Integer> addPartial = sum.apply(1, 2, 3, 4);
        Function2<Integer, Integer, Integer> add00 = addPartial.apply(5, 6);
        return add00.apply(7);
    }
}
