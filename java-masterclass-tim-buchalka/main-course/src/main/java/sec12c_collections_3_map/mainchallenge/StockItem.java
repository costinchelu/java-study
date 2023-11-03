package sec12c_collections_3_map.mainchallenge;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityInStock = 0;
    private int quantityReserved = 0;

    public StockItem(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityReserved() {
        return quantityReserved;
    }

    //checks the actual quantity available from stock
    public int availableQuantity() {
        return quantityInStock - quantityReserved;
    }

    // modify the quantity of the actual stock
    public void adjustStock(int quantityToAdjust) {
        int newQuantity = this.quantityInStock + quantityToAdjust;
        if(newQuantity >= 0) {
            this.quantityInStock = newQuantity;
        }
    }

    //checks if quantity can be reserved(enough in quantityInStock) then sets the quantityReserved
    public int reserveStock(int quantityToReserve) {
        if(quantityToReserve <= availableQuantity()) {    // !! using the method (that returns int) to check out availability
            quantityReserved += quantityToReserve;
            return quantityToReserve;
        }
        return 0;
    }

    //checks if quantity can be unreserved(enough in quantityReserved) then withdraw from quantityReserved
    public int unreserveStock(int quantityToUnreserve) {
        if(quantityToUnreserve <= quantityReserved) {
            quantityReserved -= quantityToUnreserve;
            return quantityToUnreserve;
        }
        return 0;
    }

    public int finalizeStock(int quantityFromReserved) {
        if(quantityFromReserved <= quantityReserved) {
            quantityInStock -= quantityFromReserved;
            quantityReserved -= quantityFromReserved;
            return quantityFromReserved;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        //System.out.println("Entering stockItem.equals");
        if(obj == this) {
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    // TreeMap is using compareTo
    @Override
    public int compareTo(StockItem obj) {
        //System.out.println("Entering stockItem.compareTo");
        if(this == obj) {
            return 0;
        }
        if(obj != null) {
            return this.name.compareTo(obj.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " = " + this.price + " $.";
    }
}
