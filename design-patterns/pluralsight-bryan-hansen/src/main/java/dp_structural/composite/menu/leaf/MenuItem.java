package dp_structural.composite.menu.leaf;


import dp_structural.composite.menu.component.MenuComponent;

public class MenuItem extends MenuComponent {

    public MenuItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return print(this);
    }
}
