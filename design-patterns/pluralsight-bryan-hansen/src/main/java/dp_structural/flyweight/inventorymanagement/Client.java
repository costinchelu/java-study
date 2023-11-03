package dp_structural.flyweight.inventorymanagement;


import dp_structural.flyweight.inventorymanagement.flyweightfactory.InventorySystem;

public class Client {

    public static void main(String[] args) {

        InventorySystem inventorySystem = new InventorySystem();

        inventorySystem.takeOrder("Roomba", 221);
        inventorySystem.takeOrder("Bose", 361);
        inventorySystem.takeOrder("Samsung", 458);
        inventorySystem.takeOrder("Samsung", 459);
        inventorySystem.takeOrder("Roomba", 582);
        inventorySystem.takeOrder("Bose", 741);
        inventorySystem.takeOrder("Samsung", 841);
        inventorySystem.takeOrder("LG", 842);
        inventorySystem.takeOrder("Roomba", 854);

        inventorySystem.process();

        System.out.println(inventorySystem.report());
    }
}
/*
processed many orders, but in the end we only made 4 objects
when we get an order through the inventory system we are doing a lookup for the name
if it was previously written in the HashMap from the InventoryManagement.FlyweightBase.Catalog, it will only get it from there
the map will of course only hold unique items

 */