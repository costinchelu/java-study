package dp_creational.builder.w_interface;

public class ConcreteBuider implements Builder {

    // just an object of type to be built
    private Product product;

    public ConcreteBuider() {
        product = new Product("none", "none", "none", "none");
        // the constructor will initialize the object to be built with empty or default values
    }

    @Override
    public Product buildProduct() {
        return product;
        // the buld method only returns the created object
    }

    // setter public methods for the object to be built:

    public ConcreteBuider setBread(String bread) {
        this.product.setBread(bread);
        return this;
    }

    public ConcreteBuider setMeat(String meat) {
        this.product.setMeat(meat);
        return this;
    }

    public ConcreteBuider setSauce(String sauce) {
        this.product.setSauce(sauce);
        return this;
    }

    public ConcreteBuider setTopping(String topping) {
        this.product.setTopping(topping);
        return this;
    }
}
