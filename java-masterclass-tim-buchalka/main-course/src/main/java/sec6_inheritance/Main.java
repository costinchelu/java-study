package sec6_inheritance;

public class Main {

    public static void main(String[] args) {
	    //Animal animal = new Animal("Animal", 1, 1, 5, 5);

        Dog dog = new Dog("Yorkie", 8, 20, 2, 4, 1, 20, "long silky");
        
        Fish fish = new Fish("Nemo", 2, 10, 40, 2, 6);
        
        dog.eat();
        System.out.println("--------------");
        dog.walk();
        System.out.println("--------------");
        dog.run();
        System.out.println("--------------");
        fish.move(3);
    }
}
