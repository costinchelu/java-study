package dp_behavioral.strategy.comparatorexample;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String phoneNumber;

    public Person() {
    }

    public Person(String name, int age, String phoneNumber) {
        this.age = age;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // this is the template method (used for 10_Template design pattern)
    @Override
    public int compareTo(Person o) {
        if(this.age > o.getAge()) {
            return 1;
        }
        if (this.age < o.getAge()) {
            return -1;
        }
        return 0;
    }
}
