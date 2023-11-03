package sec7d_polymorphismexercise.finalchallenge;

public class DeluxeHamburger extends Hamburger {
    private boolean hasChips;
    private boolean hasDrink;

    public DeluxeHamburger() {
        super("Standard bread", "Beef");
        this.name = "Deluxe burger";
        this.price = 30;
        this.hasChips = true;
        this.hasDrink = true;
    }

    @Override
    public void showPrice() {
        System.out.println(name + " 5€");
        System.out.println("Normal bread 3€");
        System.out.println("Beef 7€");
        System.out.println("Chips 8€");
        System.out.println("Drinks 7€");
        System.out.println("------ Total price = " + price + "€");
    }
}
