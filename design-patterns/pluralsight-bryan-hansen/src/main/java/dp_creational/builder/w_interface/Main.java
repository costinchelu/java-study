package dp_creational.builder.w_interface;

public class Main {

    public static void main(String[] args) {

        Builder concreteBuilder =
                new ConcreteBuider().setBread("White").setMeat("Beef").setSauce("Mayo").setTopping("Onion");

        Product product = concreteBuilder.buildProduct();

        // or

        Product product2 =
                new ConcreteBuider().setBread("Integral").setMeat("Chicken").setSauce("Mustard").buildProduct();



        System.out.println(product);
        System.out.println(product2);
    }
}
