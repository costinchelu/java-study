package dp_structural.composite.menu.component;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuComponent {

    protected String name;
    protected String url;
    protected List<MenuComponent> menuComponents = new ArrayList<>();


    public String print(MenuComponent menuComponent) {
        StringBuilder builder = new StringBuilder(name);
        builder.append(": ");
        builder.append(url);
        builder.append("\n");
        return builder.toString();
    }

    public abstract String toString();

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

/*
root (start) of hierarchy
 */