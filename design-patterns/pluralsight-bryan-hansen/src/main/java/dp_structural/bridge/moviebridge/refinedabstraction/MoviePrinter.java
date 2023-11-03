package dp_structural.bridge.moviebridge.refinedabstraction;


import dp_structural.bridge.moviebridge.abstraction.Printer;
import dp_structural.bridge.moviebridge.model.Detail;
import dp_structural.bridge.moviebridge.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviePrinter extends Printer {

    private Movie movie;

    public MoviePrinter(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected List<Detail> getDetails() {
        List<Detail> details = new ArrayList<>();

        details.add(new Detail("Title", movie.getTitle()));
        details.add(new Detail("Year", movie.getYear()));
        details.add(new Detail("Runtime", movie.getRuntime()));

        return details;
    }

    @Override
    protected String getHeader() {
        return movie.getClassification();
    }
}
/*
gathers data from the h2rest.model
 */