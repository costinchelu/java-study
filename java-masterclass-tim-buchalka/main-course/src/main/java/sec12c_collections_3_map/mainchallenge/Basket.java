package sec12c_collections_3_map.mainchallenge;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> basketList;
                                    //Integer value will represent the quantity of that item that is reserved

    // treeMap is to get all items sorted (compareTo method from StockItem class will sort items by name)
    // if the order is not required, then a HashMap will be faster
    public Basket(String name) {
        this.name = name;
        this.basketList = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    // we get a default value of 0 if the item to add in basket has not been previously added
    // in case the item we are adding is already present, we will just add the new quantity to the
    //old quantity (that's why we need a default value
    public int addToBasket(StockItem itemToAdd, int quantityToAdd) {
        if(itemToAdd != null && quantityToAdd > 0) {
            int quantityInBasket = basketList.getOrDefault(itemToAdd, 0);
            basketList.put(itemToAdd, quantityInBasket + quantityToAdd);
        }
        return 0;
    }

    public int removeFromBasket(StockItem itemToRemove, int quantityToRemove) {
        if((itemToRemove != null) && (quantityToRemove > 0)) {
            int quantityInBasket = basketList.getOrDefault(itemToRemove, 0);
            int newQuantity = quantityInBasket - quantityToRemove;

            if(newQuantity > 0) {
                basketList.put(itemToRemove, newQuantity);
                return quantityToRemove;
            }
            else if(newQuantity == 0) {
                basketList.remove(itemToRemove);    //no need to be in the basket if quantity = 0
                return quantityToRemove;
            }
        }
        return 0;
    }

    public Map<StockItem, Integer> allItemsInBasket() {
        return Collections.unmodifiableMap(basketList);
    }

    public void clearBasket() {
        this.basketList.clear();
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + basketList.size()
                + ((basketList.size() == 1) ? " item:" : " items:") + "\n";
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> itemInBasket : basketList.entrySet()) {
            s = s + itemInBasket.getKey() + " Reserved by user: " + itemInBasket.getValue() +
                    ((itemInBasket.getValue() == 1) ? " unit" : " units") + ".\n";
            totalCost += itemInBasket.getKey().getPrice() * itemInBasket.getValue();
        }
        return s + "\tTotal cost " + String.format("%.2f", totalCost) + " $";
    }
}
