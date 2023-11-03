package vavrdemo;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class VavrEither {

    public void vavrEitherDemo() {
        log.info("--> Either -->");

        Either<Throwable, Integer> willHaveRightResult = vavrTry(5, 2).toEither();
        Either<Throwable, Integer> willHaveLeftResult = vavrTry(4, 0).toEither();

        log.info("Result is Integer: {}", willHaveRightResult.get());
        log.info("Result is Throwable: {}", willHaveLeftResult.getOrElse(this::handleException));
    }

    private Try<Integer> vavrTry(int dividend, int divisor) {
        return Try.of(() -> dividend / divisor);
    }

    private Integer handleException() {
        log.error("Cannot divide by 0");
        return 0;
    }
}
