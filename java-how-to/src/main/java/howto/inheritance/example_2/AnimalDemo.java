package howto.inheritance.example_2;

public class AnimalDemo {

    public static void main(String[] args) {

        // I
        Animal dog = new Dog();
        Animal cat = new Cat();
        Animal rooster = new Rooster();

        AnimalList list = new AnimalList(dog, cat, rooster);
        list.animalParty();

        // II - also:
//        for (animal animal : list.getAnimalList()) {
//            System.out.println(animal.makesNoise());
//        }

        // III - also we can manually make an animal list, populate the list and iterate
    }
}
