package dp_structural.bridge.moviebridge.abstraction;


import dp_structural.bridge.moviebridge.implementationbase.Formatter;
import dp_structural.bridge.moviebridge.model.Detail;

import java.util.List;

public abstract class Printer {

    public String print(Formatter formatter) {
        return formatter.format(getHeader(), getDetails());
    }

    protected abstract List<Detail> getDetails();

    protected abstract String getHeader();
}
