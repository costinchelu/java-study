package structural.strategy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

interface PaymentOption {

    void pay(String clientName, double amount);
}

// Concrete strategies
class Card implements PaymentOption {

    @Override
    public void pay(String clientName, double amount) {
        System.out.println(clientName + " is paying with the card, an amount of " + amount);
    }
}

class Cash implements PaymentOption {

    @Override
    public void pay(String clientName, double amount) {
        System.out.println(clientName + " is paying with cash, an amount of " + amount);
    }
}

class PayPal implements PaymentOption {

    @Override
    public void pay(String clientName, double amount) {
        System.out.println(clientName + " is paying with Paypal, an amount of " + amount);
    }
}

@AllArgsConstructor
@Setter
@Getter
class Client {

    private String name;

    private PaymentOption paymentOption;

    // to change the strategy we can use the setter of the paymentOption

    // to actually do an action selecting the strategy:
    public void pay(double amount) {
        paymentOption.pay(this.name, amount);
    }
}



public class Strategy {

    public static void main(String[] args) {
        Client c1 = new Client("John", new Card());
        Client c2 = new Client("Sarah", new PayPal());
        c1.pay(25.5);
        c2.pay(158.25);
    }
}
