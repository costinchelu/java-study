package howto;

import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class Logging {

    private static final Logger log = Logger.getLogger(Logging.class.getName());

    public static void main(String[] args) {
        log.info("The current date and time is: " + ZonedDateTime.now());
        log.warning("This is a warning message");
        log.severe("This is an error message");
    }
}

