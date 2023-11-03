package vavrdemo;

import io.vavr.Function2;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class VavrLifting {
    public void vavrLiftingDemo() {
        log.info("---> Lifting");

        Function2<Integer, Integer, Option<Integer>> safeSum =  Function2.lift(this::sum);
        Option<Integer> applyReturnsNone = safeSum.apply(-1, 2);
        Option<Integer> applyReturnsSome = safeSum.apply(1, 2);

        // lifted function catches the IllegalArgumentException and maps it to None.
        log.info(applyReturnsNone.getOrElse(Integer.MIN_VALUE).toString());
        log.info(applyReturnsSome.getOrElse(Integer.MIN_VALUE).toString());
    }

    private int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }
}
