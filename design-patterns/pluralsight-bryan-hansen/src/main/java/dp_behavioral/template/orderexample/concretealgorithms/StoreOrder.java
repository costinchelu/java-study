package dp_behavioral.template.orderexample.concretealgorithms;


import dp_behavioral.template.orderexample.algorithmbase.OrderTemplate;

public class StoreOrder extends OrderTemplate {


    @Override
    public void doCheckout() {
        System.out.println("Scan items from the counter");
    }

    @Override
    public void doPayment() {
        System.out.println("Process payment with cash or card");
    }

    @Override
    public void doDelivery() {
        System.out.println("Bag items");
    }

    @Override
    public void doReceipt() {
        System.out.println("Print receipt");
    }
}
