package module01.question19.proxy.manual;

import module01.question19.dao.PersonDao;
import module01.question19.dao.PersonDaoImpl;
import module01.question19.ds.Person;

public class Runner {
    public static void main(String[] args) {
        //PersonDao personDao = new PersonDaoImpl();
        PersonDao personDao = new PersonDaoProxy(
                new PersonDaoImpl()
        );

        Person person = personDao.findById(5);
        personDao.save(person);
    }
}
