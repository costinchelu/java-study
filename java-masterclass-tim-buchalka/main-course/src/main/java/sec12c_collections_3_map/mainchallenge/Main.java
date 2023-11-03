/*
Modify the program so that adding items to the shopping basket doesn't
actually reduce the stock count but, instead, reserves the requested
number of items.

You will need to add a "reserved" field to the StockItem class to store the
number of items reserved.

items can continue to be added to the basket, but it should not be possible to
reserve more than the available stock of any item. An item's available stock
is the stock count less the reserved amount.

The stock count for each item is reduced when a basket is checked out, at which
point all reserved items in the basket have their actual stock count reduced.

Once checkout is complete, the contents of the basket are cleared.

It should also be possible to "unreserve" items that were added to the basket
by mistake.

The program should prevent any attempt to unreserve more items than were
reserved for a basket.

Add code to Example1 that will unreserve items after they have been added to the basket,
as well as un reserving items that have not been added to make sure that the code
can cope with invalid requests like that.

After checking out the baskets, display the full stock list and the contents of each
basket that you created.
 */

package sec12c_collections_3_map.mainchallenge;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    //changing HashMap to LinkedHashMap in StockList class, will store the items in alphabetical order

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.1, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12500.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.print("INITIAL STOCK:");
        System.out.println(stockList);

        Basket userTim = new Basket("TimPurchase1");
        sellItem(userTim, "car", 1);
        sellItem(userTim, "door", 2);
        System.out.println(userTim);
        System.out.println(stockList);


        sellItem(userTim, "car", 1);
        sellItem(userTim, "spanner", 1);
        sellItem(userTim, "juice", 4);
        sellItem(userTim, "cup", 12);
        sellItem(userTim, "bread", 1);
        System.out.println(userTim);
        System.out.println(stockList);

        Basket userJohn = new Basket("JohnPurchase1");
        sellItem(userJohn, "cup", 100);
        sellItem(userJohn, "juice", 5);
        removeItem(userJohn, "cup", 1);
        System.out.println(userJohn);

        removeItem(userTim, "car", 1);
        removeItem(userTim, "cup", 9);
        removeItem(userTim, "car", 1);
        System.out.println("Cars removed: " + removeItem(userTim, "car", 1));   //should not remove any
        System.out.println(userTim);

        //remove all items
        removeItem(userTim, "bread", 1);
        removeItem(userTim, "cup", 3);
        removeItem(userTim, "juice", 4);
        removeItem(userTim, "cup", 3);
        removeItem(userTim, "door", 2);
        System.out.println(userTim);
        System.out.println("\nDisplay stock list before and after checkout (for JohnPurchase1):");
        System.out.println(userJohn);
        System.out.println(stockList);
        checkOut(userJohn);
        System.out.println(userJohn);
        System.out.println(stockList);

        checkOut(userTim);
        System.out.println(userTim);

    }

    public static int sellItem(Basket basket, String itemName, int quantityToSell) {
        //retrieve the item from the stock list:
        StockItem itemInStock = stockList.get(itemName);
        if(itemInStock == null) {
            System.out.println("Product " + itemName + " is not in stock");
            return 0;
        }
        if(stockList.reserveStock(itemName, quantityToSell) != 0) {
            //in case it would be 0 that means product is not reserved
            return basket.addToBasket(itemInStock, quantityToSell);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String itemName, int quantityToRemove) {
        // retrieve the item from StockList
        StockItem itemInStock = stockList.get(itemName);
        if(itemInStock == null) {
            System.out.println("Item " + itemName + " is not in stock.");
            return 0;
        }
        if(basket.removeFromBasket(itemInStock, quantityToRemove) == quantityToRemove) {
            return stockList.unreserveStock(itemName, quantityToRemove);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        System.out.println("\nCHECKOUT " + basket.getName());
        for(Map.Entry<StockItem, Integer> itemInBasket : basket.allItemsInBasket().entrySet()) {
            stockList.sellStock(itemInBasket.getKey().getName(), itemInBasket.getValue());
        }
        basket.clearBasket();
    }
}
