package sec9c_abstractclass.animal;

/*
 * Differences between interfaces and abstract classes can be seen as  
 * differences between HAS A and IS A concepts
 * 
 * For example Dog is a animal and Parrot IS A bird (we can extend
 * an abstract Animal class)
 * 
 * But a bird "HAS A" fly ability (not all of them)(and some animals
 * can fly too and they are not birds - some insects, bats etc...) so in
 * this case we better implement an interface IcanFly for example.
 * 
 * if interfaces have variables then they are all public static final.
 * They are all static because you cannot instantiate them
 * 
 * 
 * Both interfaces and abstract classes cannot be instantiate
 */

public class Main {

    public static void main(String[] args) {
	    Dog dog = new Dog("Yorkie");
        dog.breathe();
        dog.eat();
        System.out.println("---------------------------------");

        Parrot parrot = new Parrot("Australian ringneck");
        parrot.breathe();
        parrot.eat();
        parrot.fly();
        System.out.println("---------------------------------");

        Penguin penguin = new Penguin("Emperor");
        penguin.eat();
        penguin.fly();
    }
}
