package sec12c_collections_3_map.sortedcollections;

public class Main {
    private static StockList stockList = new StockList();

    //changing HashMap to LinkedHashMap in StockList class, will store the items in alphabetical order

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.1, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12500.50, 1);
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

        System.out.println(stockList);

        Basket someBasket = new Basket("SomeBasket");
        sellItem(someBasket, "car", 1);
        sellItem(someBasket, "door", 2);
        System.out.println(stockList);
        System.out.println(someBasket);

        sellItem(someBasket, "car", 1);
        sellItem(someBasket, "spanner", 1);


    }

    public static int sellItem(Basket basket, String item, int quantity) {
        //retrieve the item from the stock list:
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("Product " + item + " is not in stock");
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0) {
            //in case it would be 0 that means we don't have a positive stock or stock is insufficient
            basket.addToBasket(stockItem, quantity);
            System.out.println("Sold " + quantity + ((quantity == 1) ? " unit" : " units") + " with name " + item);
            return quantity;
        }
        System.out.println("Product stock for " + item + " is 0");
        return 0;
    }
}
