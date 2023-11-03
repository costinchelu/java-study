package sec19f_musicui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Artist {

    // we are changing the type because we want to use data binding
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    // in the constructor we will initialize SimpleIntegerProperty/SimpleStringProperty objects
    public Artist() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    // we also convert from int/String to SimpleStringProperty/SimpleIntegerProperty in getters and setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
