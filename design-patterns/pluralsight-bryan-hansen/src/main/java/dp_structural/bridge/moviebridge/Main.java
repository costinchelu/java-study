package dp_structural.bridge.moviebridge;


import dp_structural.bridge.moviebridge.abstraction.Printer;
import dp_structural.bridge.moviebridge.concreteimplementation.HtmlFormatter;
import dp_structural.bridge.moviebridge.concreteimplementation.PrintFormatter;
import dp_structural.bridge.moviebridge.implementationbase.Formatter;
import dp_structural.bridge.moviebridge.model.Movie;
import dp_structural.bridge.moviebridge.refinedabstraction.MoviePrinter;

public class Main {

    public static void main(String[] args) {

        // creating the h2rest.model(data)
        Movie movie = new Movie();
        movie.setClassification("Action");
        movie.setTitle("John Wick");
        movie.setRuntime("2:15");
        movie.setYear("2014");

        // refined abstraction
        Printer moviePrinter = new MoviePrinter(movie);

        // concrete implementation
        Formatter printFormatter = new PrintFormatter();
        String printedMaterial = moviePrinter.print(printFormatter);
        System.out.println(printedMaterial);

        Formatter htmlFormatter = new HtmlFormatter();
        String htmlMaterial = moviePrinter.print(htmlFormatter);
        System.out.println(htmlMaterial);
    }
}
/*
Designed for uncertainty
Complex but provides flexibility

 */