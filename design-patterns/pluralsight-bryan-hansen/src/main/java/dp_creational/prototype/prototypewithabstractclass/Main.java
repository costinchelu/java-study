package dp_creational.prototype.prototypewithabstractclass;

public class Main {

    public static void main(String[] args) {

        Registry registry = new Registry();

        Movie movie1 = (Movie) registry.createItem("PrototypeWithAbstractClass.Movie Prototype");
        movie1.setTitle("Creational Patterns in Java");
        movie1.setPrice(24.99);

        Movie movie2 = (Movie) registry.createItem("PrototypeWithAbstractClass.Movie Prototype");
        movie2.setTitle("Gang of Four");
        movie2.setUrl("www.movie2.com");

        System.out.println(movie1);
        System.out.println(movie1.getTitle());
        System.out.println(movie1.getPrice());
        System.out.println(movie1.getRuntime());
        System.out.println(movie1.getUrl());
        System.out.println("-----------------");
        System.out.println(movie2);
        System.out.println(movie2.getTitle());
        System.out.println(movie2.getPrice());
        System.out.println(movie2.getRuntime());
        System.out.println(movie2.getUrl());

        /*
        as a prototype we had a PrototypeWithAbstractClass.Movie with some default values set into
        the PrototypeWithAbstractClass.Registry class first we are creating some clones and then
        we are free to change some of their attributes but every time we are creating
        a new PrototypeWithAbstractClass.Movie or PrototypeWithAbstractClass.Book instance,
        we are getting all info from the clone - that means we are getting a unique instance every time (shallow)
         */
    }
}

/*
- avoids costly creation (performance)
- avoids subclassing
- doesn't use new (unless it's the first creation)
- often utilizes an interface
- usually implemented with a PrototypeWithAbstractClass.Registry
- uses Clone/Cloneable()
- although a copy, each instance is unique
- can use parameters for construction (but tipically don't)
- we can choose to do a shallow or deep copy

java.lang.Object#clone()
 */