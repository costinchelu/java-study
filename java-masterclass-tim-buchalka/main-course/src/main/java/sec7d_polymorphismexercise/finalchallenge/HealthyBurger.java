package sec7d_polymorphismexercise.finalchallenge;

public class HealthyBurger extends Hamburger{
    private boolean hasLettuce;
    private boolean hasTomato;


    public HealthyBurger() {
        super("Rye Bread", "Lean meat");
        this.name = "Healthy Burger";
        this.price = 20;
    }

    public void addLetuce() {
        this.hasLettuce = true;
        price += 3;
    }

    public void addTomato() {
        this.hasTomato = true;
        price += 3;
    }

    @Override
    public void showPrice() {
        System.out.println(name + " with rye bread and lean meat 20€");
        showOptionals();
        System.out.println("------ Total price = " + price + "€");
    }

    @Override
    public void showOptionals() {
        super.showOptionals();
        if(hasLettuce) System.out.println("Lettuce " + " 3€");
        if(hasTomato) System.out.println("Tomato " + " 3€");
    }
}

