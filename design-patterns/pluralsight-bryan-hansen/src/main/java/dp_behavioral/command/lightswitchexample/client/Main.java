package dp_behavioral.command.lightswitchexample.client;


import dp_behavioral.command.lightswitchexample.commandbase.Command;
import dp_behavioral.command.lightswitchexample.concretecommand.AllLightsCommand;
import dp_behavioral.command.lightswitchexample.concretecommand.ToggleCommand;
import dp_behavioral.command.lightswitchexample.invoker.Switch;
import dp_behavioral.command.lightswitchexample.receiver.Light;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Light bedroomLight = new Light();
        Light kitchenLight = new Light();
        Light livingRoomLight = new Light();

        List<Light> lights = new ArrayList<>();
        lights.add(kitchenLight);
        lights.add(bedroomLight);
        lights.add(livingRoomLight);

        Switch lightSwitch = new Switch();

        Command toggleCommand = new ToggleCommand(bedroomLight);
        lightSwitch.storeAndExecute(toggleCommand);

        Command allLightsCommand = new AllLightsCommand(lights);
        lightSwitch.storeAndExecute(allLightsCommand);
    }
}
