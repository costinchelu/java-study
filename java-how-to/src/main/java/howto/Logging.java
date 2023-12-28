package howto;

import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class Logging {

    private static final Logger logger = Logger.getLogger(Logging.class.getName());

    public static void main(String[] args) {
        logger.info("The current date and time is: " + ZonedDateTime.now());
        logger.warning("This is a warning message");
        logger.severe("This is an error message");
    }
}

