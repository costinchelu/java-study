package dp_behavioral.command.lightswitchexample.concretecommand;


import dp_behavioral.command.lightswitchexample.commandbase.Command;
import dp_behavioral.command.lightswitchexample.receiver.Light;

import java.util.List;

public class AllLightsCommand implements Command {

    private List<Light> lights;

    public AllLightsCommand(List<Light> lights) {
        this.lights = lights;
    }

    @Override
    public void execute() {
        for(Light light : lights) {
            if (light.isOn()) {
                light.toggle();
            }
        }
    }
}
