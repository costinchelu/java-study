package module01.question19.dao;

import module01.question19.ds.Person;

public interface PersonDao {

    Person findById(int id);

    void save(Person person);
}
