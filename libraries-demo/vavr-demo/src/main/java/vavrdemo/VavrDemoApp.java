package vavrdemo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Log4j2
@AllArgsConstructor
public class VavrDemoApp implements CommandLineRunner {

    private VavrCollections vavrCollections;

    private VavrFunctions vavrFunctions;

    private VavrComposition vavrComposition;

    private VavrLifting vavrLifting;

    private VavrPartialApplication partialApplication;

    private VavrCurrying vavrCurrying;

    private VavrMemoization vavrMemoization;

    private VavrOption vavrOption;

    private VavrTry vavrTry;

    private VavrLazy vavrLazy;

    private VavrEither vavrEither;

    private VavrFuture vavrFuture;

    private VavrValidation vavrValidation;

    private VavrPatternMatching vavrPatternMatching;

    public static void main(String[] args) {
        SpringApplication.run(VavrDemoApp.class, args);
    }

    @Override
    public void run(String... args) {
        vavrCollections.vavrCollectionsDemo();
        vavrFunctions.vavrFunctionsDemo();
        vavrComposition.functionCompositionDemo();
        vavrLifting.vavrLiftingDemo();
        partialApplication.vavrPartialApplicationDemo();
        vavrCurrying.vavrCurryingDemo();
        vavrMemoization.vavrMemoizationDemo();
        vavrOption.vavrOptionDemo();
        vavrTry.vavrTryDemo();
        vavrLazy.vavrLazyDemo();
        vavrEither.vavrEitherDemo();
        vavrFuture.vavrFutureDemo();
        vavrValidation.vavrValidationDemo();
        vavrPatternMatching.vavrPatternMatchingDemo();

        log.info("------------------------------");
    }
}
