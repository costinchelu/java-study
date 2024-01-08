package behavioral.decorator;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

interface IInvoice {

    void printInvoice();
}


@AllArgsConstructor
@Getter
@Setter
class Invoice implements IInvoice {

    private int invoiceCode;

    private double invoiceAmount;

    @Override
    public void printInvoice() {
        System.out.println("Invoice " + invoiceCode + ". Amount " + invoiceAmount);
    }
}



abstract class AbstractDecorator implements IInvoice {

    protected Invoice invoice;

    public AbstractDecorator(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void printInvoice() {
        invoice.printInvoice();
        System.out.println("---");
    }

    public abstract void applyDiscount(int percent);
}



class ConcreteDecorator extends AbstractDecorator {

    public ConcreteDecorator(Invoice invoice) {
        super(invoice);
    }

    @Override
    public void applyDiscount(int percent) {
        super.invoice.setInvoiceAmount(super.invoice.getInvoiceAmount() * (100 - percent) / 100);
    }
}



public class Decorator {

    public static void main(String[] args) {
        Invoice invoice = new Invoice(123, 1000);
        invoice.printInvoice();

        AbstractDecorator decoratedInvoice = new ConcreteDecorator(invoice);
        decoratedInvoice.applyDiscount(15);
        decoratedInvoice.printInvoice();
    }
}
