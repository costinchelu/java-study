package dp_structural.composite.menu.composite;


import dp_structural.composite.menu.component.MenuComponent;

import java.util.Iterator;

public class Menu extends MenuComponent {

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

//    @Override
    public MenuComponent addChildren(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
        return menuComponent;
    }

//    @Override
    public MenuComponent removeChildren(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
        return menuComponent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(print(this));

        Iterator<MenuComponent> itr = menuComponents.iterator();
        while (itr.hasNext()) {
            MenuComponent menuComponent = itr.next();
            builder.append(menuComponent.toString());
        }

        return builder.toString();
    }
}
