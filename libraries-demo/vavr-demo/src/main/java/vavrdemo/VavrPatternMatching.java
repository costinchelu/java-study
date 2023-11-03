package vavrdemo;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static io.vavr.Predicates.instanceOf;
import static io.vavr.Predicates.isIn;

@Component
@Slf4j
public class VavrPatternMatching {

    public void vavrPatternMatchingDemo() {
        log.info("--> PatternMatching -->");


        Integer i = 7;
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(4), "four"),
                Case($(5), "five"),
                Case($(isIn(6, 7)), "six or seven"),
                Case($(t -> t > 7 && t <= 10), "gt 7 & gte 10"),
                Case($(), "gt 10")
        );
        log.info(s);


        Option<String> s2 = Match(i).option(
                Case($(0), "zero")
        );
        log.info(s2.getOrElse("gt 0"));


        String s3 = "-h";
        Match(s3).of(
                Case($(isIn("-h", "--help")), c -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), c -> run(this::displayVersion)),
                Case($(), c -> run(() -> { throw new IllegalArgumentException(s3); }))
        );


        Number obj = 5.25;
        Number plusOne = Match(obj).of(
                Case($(instanceOf(Integer.class)), o -> o + 1),
                Case($(instanceOf(Double.class)), d -> d + 1),
                Case($(), o -> { throw new NumberFormatException(); })
        );
        log.info("Number = {}", plusOne);


        Option<Object> option = Option.none();
        String result = Match(option).of(
                Case($Some($()), "defined"),
                Case($None(), "empty")
        );
        log.info("Option's content: {}", result);
    }

    private void displayVersion() {
        log.info("Display version");
    }

    private void displayHelp() {
        log.info("Display help");
    }
}
