package dp_behavioral.command.lightswitchexample.receiver;

public class Light {

    private boolean isOn = false;

    public void toggle() {
        if(isOn) {
            off();
            isOn = false;
        } else {
            on();
            isOn = true;
        }
    }

    public void on() {
        System.out.println("Light switched on.");
    }

    public void off() {
        System.out.println("Light switched off.");
    }

    public boolean isOn() {
        return isOn;
    }
}
