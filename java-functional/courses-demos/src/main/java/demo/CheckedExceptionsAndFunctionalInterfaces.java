package demo;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class CheckedExceptionsAndFunctionalInterfaces {

    // The create() method throws a checked exception. The calling method handles or declares it.
    public void good() throws IOException {
        ExceptionCaseStudy.create().stream().count();
    }

    // unhandled exception type IOException
//    public void bad() throws IOException {
//        Supplier<List<String>> s = ExceptionCaseStudy::create; // DOES NOT COMPILE
//    }

    // The problem is that the lambda to which this method reference expands does not declare an exception.
    // The Supplier interface does not allow checked exceptions.


    // we can catch the exception and turn it into an unchecked exception
    // This works. But the code is ugly.
    public void ugly() {
        Supplier<List<String>> s = () -> {
            try {
                return ExceptionCaseStudy.create();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    // We can use the safe wrapper in our Supplier without issue.
    public void wrapped() {
        Supplier<List<String>> s2 = ExceptionCaseStudy::createSafe;
    }
}

class ExceptionCaseStudy {

    // Suppose that we have a class with a method that throws a checked exception
    static List<String> create() throws IOException {
        throw new IOException();
    }

    // Another alternative is to create a wrapper method with try/catch.
    static List<String> createSafe() {
        try {
            return ExceptionCaseStudy.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

