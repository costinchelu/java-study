package howto;

import java.util.ArrayList;
import java.util.List;

public class WorkWithGenerics {

    // Upper bound wildcard
    // in category
    public static void deleteCat(List<? extends Cat> catList, Cat cat) {
        catList.remove(cat);
        System.out.println("Cat Removed");
    }

    // Lower bound wildcard
    // out category
    public static void addCat(List<? super RedCat> catList) {
        catList.add(new RedCat("Red Cat"));
        System.out.println("Cat Added");
    }

    public static void addCat2(List<? super Cat> catList) {
        catList.add(new RedCat("Cat"));
        System.out.println("Cat Added");
    }

    // Unbounded wildcard
    // Using Object method toString()
    public static void printAll(List<?> list) {
        for (Object item : list)
            System.out.println(item + " ");
    }

    public static void main(String[] args) {

        List<Animal> animalList= new ArrayList<>();
        List<RedCat> redCatList= new ArrayList<>();

        //add list of super class Animal of Cat class
        addCat(animalList);
        //add list of Cat class
        addCat(redCatList);
        addCat(redCatList);
        //addCat2(redCatList); // not working

        //print all animals
        printAll(animalList);
        printAll(redCatList);

        Cat cat = redCatList.get(0);
        //delete cat
        deleteCat(redCatList, cat);
        // deleteCat(animalList, cat); // not working
        printAll(redCatList);
    }
}

class Animal {
    String name;
    Animal(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}


class Cat extends Animal {
    Cat(String name) {
        super(name);
    }
}


class RedCat extends Cat {
    RedCat(String name) {
        super(name);
    }
}


class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
}
