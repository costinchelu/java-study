package dp_behavioral.command.lightswitchexample.concretecommand;


import dp_behavioral.command.lightswitchexample.commandbase.Command;
import dp_behavioral.command.lightswitchexample.receiver.Light;

public class ToggleCommand implements Command {

    private Light light;

    public ToggleCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.toggle();
    }
}

/*
First implementation:

public class OnCommand implements Command {

    private Light light;

    public OnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}


 */