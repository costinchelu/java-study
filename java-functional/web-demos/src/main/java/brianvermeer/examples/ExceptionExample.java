package brianvermeer.examples;


import brianvermeer.exception.MyException;
import brianvermeer.types.Beer;
import brianvermeer.types.Either;

import java.util.List;

public class ExceptionExample {

    public Beer doSomething(Beer beer) throws MyException {
        if (Math.random() * 10 > 5) throw new MyException("aaaaah");
        return beer;
    }

    public void execute() {
        var beerLib = List.of(
                new Beer("Heineken", 5.2),
                new Beer("Amstel", 5.1),
                new Beer("Grolsch", 4.8)
        );

        beerLib.stream()
                .map(Either.liftWithValue(this::doSomething))
                .forEach(System.out::println);
    }



    public static void main(String[] args) {
        ExceptionExample ee = new ExceptionExample();
        ee.execute();
    }

}
