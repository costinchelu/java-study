package dp_behavioral.template.orderexample.algorithmbase;

public abstract class OrderTemplate {

    // hook
    public boolean isGift;

    public abstract void doCheckout();
    public abstract void doPayment();
    public abstract void doDelivery();
    public abstract void doReceipt();

    public final void processOrder() {
        doCheckout();
        doPayment();

        if(isGift) {
            wrapGift();
        } else {
            doReceipt();
        }

        doDelivery();
    }

    public final void wrapGift() {
        System.out.println("The gift was wrapped.");
    }
}
