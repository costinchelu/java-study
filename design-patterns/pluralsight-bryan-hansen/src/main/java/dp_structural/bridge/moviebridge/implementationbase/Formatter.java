package dp_structural.bridge.moviebridge.implementationbase;


import dp_structural.bridge.moviebridge.model.Detail;

import java.util.List;

public interface Formatter {

    String format(String header, List<Detail> details);

}
