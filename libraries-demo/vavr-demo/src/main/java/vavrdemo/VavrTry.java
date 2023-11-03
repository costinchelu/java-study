package vavrdemo;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrTry {

    public void vavrTryDemo() {
        log.info("--> Try -->");


        int divideOk = divide(5, 2);
        int divideThrowable = divide(4, 0);
        log.info("DivideResultSuccess: {}", divideOk);
        log.info("DivideResultFailure: {}", divideThrowable);

        int vavrTry1 = vavrTry(5, 2).get();
        int vavrTry2 = vavrTry(4, 0).get();
        log.info("vavrTry1: {}", vavrTry1);
        log.info("vavrTry2: {}", vavrTry2);

        useTryWithAVoidMethod(5, 4);
        useTryWithAVoidMethod(4, 0);
    }

    private int divide(int dividend, int divisor) {
        int result = 0;
        try {
            result = dividend / divisor;
        } catch (ArithmeticException e) {
            log.error("Cannot divide by 0: {}", e.toString());
        }
        return result;
    }

    private void voidMethod(int dividend, int divisor) {
        int operation = dividend / divisor;
        log.info("Void method result {}", operation);
    }

    public void useTryWithAVoidMethod(int dividend, int divisor) {
        Try.run(() -> voidMethod(dividend, divisor))
                .onFailure(exception -> log.error("Void method failed. " + exception.getMessage()));
    }


    public Try<Integer> vavrTry(int dividend, int divisor) {
        return Try.of(() -> dividend / divisor).recover(this::handleException);
    }

    // or

    private int vavrTryAlternative(int dividend, int divisor) {
        return Try.of(() -> dividend / divisor).getOrElse(0);
    }

    private int handleException(Throwable t) {
        log.error("Cannot divide by 0: {}", t.toString());
        return 0;
    }
}
