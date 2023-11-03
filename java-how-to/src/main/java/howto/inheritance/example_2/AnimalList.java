package howto.inheritance.example_2;

import java.util.ArrayList;
import java.util.List;


public class AnimalList {

    private List<Animal> animalList;


    public AnimalList(Animal dog, Animal cat, Animal rooster) {
        this.animalList = new ArrayList<Animal>();
        animalList.add(dog);
        animalList.add(cat);
        animalList.add(rooster);
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void animalParty() {
        System.out.println("Animals are getting noisy:");
        for(Animal animal : animalList) {
            System.out.println(animal.makesNoise());
        }
    }
}
