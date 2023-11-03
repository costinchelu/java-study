package vavrdemo;

import io.vavr.Lazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrLazy {

    public void vavrLazyDemo() {
        log.info("--> Lazy -->");


        Lazy<Double> lazy = Lazy.of(Math::random);
        log.info("{}", lazy.isEvaluated());
        log.info("Lazy will generate the random value: {}", lazy.get());
        log.info("{}", lazy.isEvaluated());
        log.info("Lazy is memoized: {}", lazy.get());
    }
}
