package vavrdemo;

import io.vavr.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VavrFuture {

    public void vavrFutureDemo() {
        log.info("--> Future -->");

        Future<Object> future = Future.of(Math::random);
        Future<Object> future2 = Future.of(this::someOperation);
        var value1 =  future.get();
        var value2 =  future2.get();
        log.info(value1.toString());
        log.info(value2.toString());
    }

    private int someOperation() throws InterruptedException {
        Thread.sleep(500);
        return -1;
    }
}
