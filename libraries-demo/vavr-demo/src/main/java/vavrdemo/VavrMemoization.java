package vavrdemo;

import io.vavr.Function0;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrMemoization {

    public void vavrMemoizationDemo() {
        log.info("---> Memoization");

        Function0<Double> hasCache = Function0.of(Math::random).memoized();

        double randomValue1 = hasCache.apply();
        double randomValue2 = hasCache.apply();

        log.info("randomValue1 (memoized) = {}, randomValue2 (memoized) = {}", randomValue1, randomValue2);
    }
}
