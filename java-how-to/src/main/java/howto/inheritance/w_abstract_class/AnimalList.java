package howto.inheritance.w_abstract_class;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AnimalList {

    private final List<Animal> animalList;

    public AnimalList(Animal dog, Animal cat, Animal rooster) {
        this.animalList = new ArrayList<>();
        animalList.add(dog);
        animalList.add(cat);
        animalList.add(rooster);
    }

    public void animalParty() {
        System.out.println("Animals are getting noisy:");
        for (Animal animal : animalList) {
            System.out.println(animal.makesNoise());
        }
    }
}
