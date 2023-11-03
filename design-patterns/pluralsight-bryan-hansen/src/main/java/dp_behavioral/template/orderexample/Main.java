package dp_behavioral.template.orderexample;


import dp_behavioral.template.orderexample.algorithmbase.OrderTemplate;
import dp_behavioral.template.orderexample.concretealgorithms.StoreOrder;
import dp_behavioral.template.orderexample.concretealgorithms.WebOrder;

public class Main {

    public static void main(String[] args) {

        System.out.println("Web Order:");
        OrderTemplate webOrder = new WebOrder();
        webOrder.processOrder();

        System.out.println("\nStore order:");
        OrderTemplate storeOrder = new StoreOrder();
        storeOrder.processOrder();
    }
}
