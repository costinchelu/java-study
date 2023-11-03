package brianvermeer.exception;

@FunctionalInterface
public interface CheckedFunction<T, R> {

    R apply(T t) throws Exception;
}
