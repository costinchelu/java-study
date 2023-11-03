package howto.implement_clone;

// (I) it should implement Cloneable
public class Person implements Cloneable {

    private String name;
    private int income;
    private City city;          // deep copy
    private Country country;    // shalow copy


    public Person(String name, int income, City city, Country country) {
        this.name = name;
        this.income = income;
        this.city = city;
        this.country = country;
    }

    // no @Override, means we are not overriding clone
    // (II) it should throw CloneNotSupported
    public Person clone() throws CloneNotSupportedException {
        Person clonedPerson = (Person) super.clone();   // (III) calls clone() from Object class

        // all other primitive fields are cloned by default
        // by default it will shallow copy all object references,
        // but we can still make deep copy by implementing clone() in those classes:

        clonedPerson.city = (City) this.city.clone();

        // to make a deep copy of city we need to call clone that will also be implemented in City class
        // and let's not forget that clone() will return an Object so we need to cast it to City

        // if we are not implementing clone also for Country, we will just shallow copy it

        return clonedPerson;
    }

    // but we can implement a copy constructor (and also implement copy constructors in aggregated classes):
    public Person(Person original) {
        this.name = original.name;
        this.income = original.income;
        this.city = new City(original.city);
        this.country = new Country(original.country);
    }



    public String getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }
}
