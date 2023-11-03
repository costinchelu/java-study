package dp_behavioral.command.lightswitchexample.invoker;


import dp_behavioral.command.lightswitchexample.commandbase.Command;

public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }

}
