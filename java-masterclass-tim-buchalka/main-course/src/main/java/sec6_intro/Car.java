package sec6_intro;

public class Car {
    private int doors;      //fields (attributes)
    private int wheels;
    private String model;
    private String engine;
    private String colour;

    public void setModel(String model)      //methods
    {
        // optional putem folosi o validare. Nu ne permite sa folosim alte denumiri in afara
        // celor deja permise (carrera sau commodore)
        String validModel = model.toLowerCase();
        if (validModel.equals("carrera") || validModel.equals("commodore")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getModel() {
        return this.model;
    }
}
