package sec7d_polymorphismexercise.finalchallenge;

public class Hamburger {
    protected String name;
    private String breadType;
    private String meat;
    protected double price;
    protected boolean hasSauce;
    protected boolean hasPickle;
    protected boolean hasBacon;
    protected boolean hasCheese;

    public Hamburger(String breadType, String meat) {
        this.price = 5;
        this.name = "Base Burger";
        this.breadType = breadType;
        switch(breadType){
            case "Normal Bread": price += 3; break;
            case "Rye Bread": price += 5; break;
            default: break;
        }
        this.meat = meat;
        switch(meat) {
            case "Beef": price += 7; break;
            case "Lean meat": price += 10; break;
            default: break;
        }
    }

    public void addSauce() {
       hasSauce = true;
       price += 2;
    }

    public void addPicke() {
        hasPickle = true;
        price += 3;
    }

    public void addBacon() {
        hasBacon = true;
        price += 4;
    }

    public void addCheese() {
        hasCheese = true;
        price += 4;
    }

    public void showPrice() {
        System.out.println(name + " 5€");
        System.out.print(breadType);
        switch(breadType){
            case "Normal Bread":
                System.out.print(" 3€"); break;
            case "Rye Bread":
                System.out.print(" 5€"); break;
            default: break;
        }
        System.out.println();
        System.out.print(meat);
        switch(meat) {
            case "Beef":
                System.out.print(" 7€"); break;
            case "Lean meat":
                System.out.print(" 10€"); break;
            default: break;
        }
        System.out.println();
        showOptionals();
        System.out.println("------ Total price = " + price +"€");
    }

    public void showOptionals() {
        if(hasSauce) System.out.println("Sauce " + " 2€");
        if(hasPickle) System.out.println("Pickle " + " 3€");
        if(hasBacon) System.out.println("Bacon " + " 4€");
        if(hasCheese) System.out.println("Cheese " + " 4€");
    }
}
