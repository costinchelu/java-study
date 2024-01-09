package structural.command;

import lombok.AllArgsConstructor;

// invoker
class Operator {

    public void invoke(Command command) {
        command.execute();
    }
}



// CommandBase
public interface Command {

    void execute();
}



@AllArgsConstructor
class Booking implements Command {

    private TravelPackage travelPackage;

    @Override
    public void execute() {
        travelPackage.book();
    }
}

@AllArgsConstructor
class Selling implements Command {

    private TravelPackage travelPackage;

    @Override
    public void execute() {
        travelPackage.sell();
    }
}