package howto.implement_clone;

public class Demo {

    public static void main(String[] args) throws CloneNotSupportedException {

        Country country1 = new Country("Italy");
        Country country2 = new Country("France");
        City city1 = new City("Rome");
        City city2 = new City("Milan");
        City city3 = new City("Paris");
        City city4 = new City("Lyon");

        Person person1 = new Person("name1", 100, city1, country1);

        Person person2 = person1.clone();

        // both persons will have identical attribute values (in case of City attribute is actually
        // the same reference). Still, both person's hashcodes are different (because both persons have
        // different addreses in heap. To be correct, hashCode() method should be overriden.
        System.out.println(person1.getName());
        System.out.println(person1.getIncome());
        System.out.println(person1.getCountry().getCountryName());
        System.out.println(person1.getCountry().hashCode());
        System.out.println(person1.getCity().getCityName());
        System.out.println(person2.getName());
        System.out.println(person2.getIncome());
        System.out.println(person2.getCountry().getCountryName());
        System.out.println(person2.getCountry().hashCode());
        System.out.println(person2.getCity().getCityName());

        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());


    }
}
