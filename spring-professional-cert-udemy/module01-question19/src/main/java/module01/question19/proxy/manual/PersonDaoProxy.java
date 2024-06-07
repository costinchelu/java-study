package module01.question19.proxy.manual;

import module01.question19.dao.PersonDao;
import module01.question19.ds.Person;

public class PersonDaoProxy implements PersonDao {

    private final PersonDao personDao;

    public PersonDaoProxy(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findById(int id) {
        System.out.println("LOGGING - before findById");
        Person person = personDao.findById(id);
        System.out.println("LOGGING - after findById");
        return person;
    }

    @Override
    public void save(Person person) {
        System.out.println("LOGGING - before save");
        personDao.save(person);
        System.out.println("LOGGING - after save");
    }
}
