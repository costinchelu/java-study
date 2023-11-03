package dp_behavioral.chainofresponsibility;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChainOfResponsabilityEverydayDemo {

    private static final Logger logger = Logger.getLogger(ChainOfResponsabilityEverydayDemo.class.getName());

    public static void main(String[] args) {

        // level to log at (it will start the chain of logging from this level further (so finest level will not pass)
        logger.setLevel(Level.FINER);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        //level to publish at
        consoleHandler.setLevel(Level.FINER);
        logger.addHandler(consoleHandler);

        logger.finest("Finest level of logging");       // won't print
        logger.finer("Finer level, but not as fine as finest");
        logger.fine("Fine, but not as fine as finer or finest");
    }
}
/*
- hierarchical
- decoupling of sender and receiver
- process varied requests, each of which may be dealt with by a different handler
- receiver contains reference to next receiver
- promotes loose coupling


java.util.logging.Logger#log()

-- we have no guarantee that someone will process the request
 */