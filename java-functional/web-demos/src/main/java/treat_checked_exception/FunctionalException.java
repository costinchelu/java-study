package treat_checked_exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

// treat checked exceptions when using streams
public class FunctionalException {

    public static void main(String[] args) {

        String filePath = "";

        // option 1
        List<String> fileLines1 = Optional.of(filePath)
                .map(Path::of)
                .map(FunctionalException::readAllLinesSafe)
                .orElseThrow();


        // option 2
        List<String> fileLines2 = Optional.of(filePath)
                .map(Path::of)
                .flatMap(mapSafeFunction(Files::readAllLines))
                .orElseThrow();


        // option 3
        List<String> fileLines3 = Optional.of(filePath)
                .map(Path::of)
                .flatMap(mapSafeFunction(Files::readAllLines, System.out::println))
                .orElseThrow();

    }

    // option 1 - works only in this specific case and similar copy-paste is required in other cases
    private static List<String> readAllLinesSafe(Path path) {
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> allLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
            return allLines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // option 2 - generic hof that converts any such function to a function that suppresses the exception
    private static <T, R> Function<T, Optional<R>> mapSafeFunction(ExceptionalFunction<T, R> exceptionalFunction) {
        return mapSafeFunction(exceptionalFunction, e -> {});
    }

    // generic hof that converts any such function to a function and allows exception handling
    private static <T, R> Function<T, Optional<R>> mapSafeFunction(ExceptionalFunction<T, R> exceptionalFunction, Consumer<Exception> exceptionHandler) {
        return (T t) -> {
            try {
                R result = exceptionalFunction.apply(t);
                return Optional.ofNullable(result);
            } catch (Exception e) {
                exceptionHandler.accept(e);
                return Optional.empty();
            }
        };
    }
}

interface ExceptionalFunction<T, R> {

    R apply (T t) throws Exception;
}