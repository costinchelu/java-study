package vavrdemo;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class VavrOption {

    public void vavrOptionDemo() {
        log.info("---> Value: Option");


        Optional<List<Integer>> optional = Optional.of(List.of(1, 2, 3));
        Optional<String> s = optional.map(e -> null).map(Object::toString);
        log.error("Optional: {}", s);


        try {
            Option.of(List.of(1, 2, 3)).map(e -> null).map(Object::toString);
        } catch (NullPointerException e) {
            log.error("NPE on Object::toString, because there is a null value inside the Option. {}", e.getMessage());
        }


        Option<String> result = Option.of(List.of("one", "two", "three"))
                .flatMap(e -> Option.of(null))
                .map(e -> e.toString().toUpperCase() + "...");

        log.error("None result: {}", result);
    }
}
