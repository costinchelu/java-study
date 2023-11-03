package demo;

import java.util.List;
import java.util.function.Consumer;

public class ExceptionHandling {

    private static final String EXCEPTION_MSG = "Exception : ";

    public static void main(String[] args) {
        List<String> list = List.of("10", "20", "30yyy");

        list.stream().map(Integer::parseInt).forEach(System.out::println);
        list.forEach(ExceptionHandling::printList);
        list.forEach(handleExceptionIfAny(System.out::println));
        list.forEach(handleGenericException(s -> System.out.println(Integer.parseInt(s)), NumberFormatException.class));

        List<Integer> list2 = List.of(10, 20, 30);

        list2.forEach(handleCheckedExceptionConsumer(i -> {
            Thread.sleep(i);
            System.out.println(i);
        }));
    }

    public static void printList(String s) {
        try {
            System.out.println(Integer.parseInt(s));
        } catch (Exception ex) {
            System.out.println(EXCEPTION_MSG + ex.getMessage());
        }
    }

    static Consumer<String> handleExceptionIfAny(Consumer<String> payload) {
        return obj -> {
            try {
                payload.accept(obj);
            } catch (Exception ex) {
                System.out.println(EXCEPTION_MSG + ex.getMessage());
            }
        };
    }

    static <Target, ExObj extends Exception> Consumer<Target> handleGenericException(Consumer<Target> targetConsumer,
                                                                                     Class<ExObj> exObjClass) {
        return obj -> {
            try {
                targetConsumer.accept(obj);
            } catch (Exception ex) {
                try {
                    ExObj exObj = exObjClass.cast(ex);
                    System.out.println(EXCEPTION_MSG + exObj.getMessage());
                } catch (ClassCastException ecx) {
                    throw ex;
                }
            }
        };
    }

    static <Target> Consumer<Target> handleCheckedExceptionConsumer(CheckedExceptionHandlerConsumer<Target, Exception> handlerConsumer) {
        return obj -> {
            try {
                handlerConsumer.accept(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}

@FunctionalInterface
interface CheckedExceptionHandlerConsumer<Target, ExObj extends Exception> {

    void accept(Target target) throws ExObj;
}
