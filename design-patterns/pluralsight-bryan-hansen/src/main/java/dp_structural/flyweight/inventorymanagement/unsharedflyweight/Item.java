package dp_structural.flyweight.inventorymanagement.unsharedflyweight;// Instances of InventoryManagement.UnsharedFlyweight.Item will be Flyweights

public class Item {

    // every attribute is immutable
    private final  String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" + "name = '" + name + '\'' + '}';
    }
}
/*
Like on the Amazon store, if we would pull out every complete object that we want to view, that
would be very memory expensive. So in this case we would only pull say image, name, price.
 */