package brianvermeer.examples;


import brianvermeer.types.Beer;

import java.util.List;

public class MutateStream {

    private final List<Beer> beers = List.of(new Beer("Heineken", 5.2), new Beer("Amstel", 5.1));

    public void execute() {
        var beers = List.of(
                new Beer("Heineken", 5.2),
                new Beer("Amstel", 5.1));

        var beersNew = beers.stream()
                .map(beer -> beer.withName("AWESOME " + beer.getName()))
                .toList();

        beersNew.forEach(System.out::println);
        System.out.println("------");
        beers.forEach(System.out::println);
    }


    public static void main(String[] args) {
        MutateStream sm = new MutateStream();
        sm.execute();
    }
}
