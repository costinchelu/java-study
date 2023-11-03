package sec12c_collections_3_map.mainchallenge;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> mapList;
    //item name is the key, actual item is the content

    public StockList() {
        this.mapList = new LinkedHashMap<>();
    }

    // Check if already have quantities of this item. getOrDefault will get the item from map
    // by searching the key (his name), but if no object with this name is found,
    // inStock will get info from item object (default).
    // If there are already stocks on this item, inStock will be different than item and we can
    // adjust its quantity based on the method adjustStock.
    // After adjusting quantity we can insert the item in the map, or overwrite the old quantity.
    public int addStock(StockItem item) {
        if(item != null) {
            StockItem itemInStock = mapList.getOrDefault(item.getName(), item);
            if(itemInStock != item) {
                item.adjustStock(itemInStock.availableQuantity());  // call method to check available quantity
            }
            mapList.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    // uses reserveStock(int) method from StockItem to set a quantity to reserve for a given item name
    public int reserveStock(String itemName, int quantityToReserve) {
        StockItem  itemInStock = mapList.get(itemName);
        if((itemInStock != null) && (quantityToReserve > 0)) {
            return itemInStock.reserveStock(quantityToReserve);
        }
        return 0;
    }

    // uses unreserveStock(int) method from StockItem to set a quantity to unreserve a quantity for a given item name
    public int unreserveStock(String itemName, int quantityToUnreserve) {
        StockItem itemInStock = mapList.get(itemName);
        if((itemInStock != null) && (quantityToUnreserve > 0)) {
            return itemInStock.unreserveStock(quantityToUnreserve);
        }
        return 0;
    }

    //now uses finaliseStock to finally sell the quantity that was previously reserved (put in basket)
    public int sellStock(String itemName, int quantityToSell) {
        StockItem itemInStock = mapList.get(itemName);
        if((itemInStock != null) && (quantityToSell >= 0)) {
            return itemInStock.finalizeStock(quantityToSell);
        }
        return 0;

        //old code kept for comparison:
//        StockItem itemInStock = mapList.getOrDefault(item, null);
//        if((itemInStock != null)  && (quantityToSell > 0) && (itemInStock.availableQuantity() >= quantityToSell)) {
//            itemInStock.adjustStock(-quantityToSell);
//            return quantityToSell;
//        }
//        return 0;

    }

    public StockItem get(String key) {
        return mapList.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(mapList);
    }

    @Override
    public String toString() {
        String s = "\nStock List:\n";
        double totalCost = 0.0;

        for(Map.Entry<String, StockItem> itemInStock : mapList.entrySet()) {
            StockItem stockItem = itemInStock.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + " There are:\t" + stockItem.availableQuantity() + " available ";
            s = s + "+ " + stockItem.getQuantityReserved() + " reserved.\t" + "Value of available stock: ";
            s = s + String.format("%.2f", itemValue) + " $\n";
            totalCost += itemValue;
        }
        return s + "\tTotal stock value = " + String.format("%.2f", totalCost) + " $";
    }
}
