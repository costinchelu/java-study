package module01.question19.proxy.manual;

import module01.question19.dao.PersonDao;
import module01.question19.dao.PersonDaoImpl;
import module01.question19.ds.Person;

public class Runner {

    public static void main(String[] args) {
        // no proxy
        PersonDao personDaoImpl = new PersonDaoImpl();
        Person person1 = personDaoImpl.findById(5);
        personDaoImpl.save(person1);

        System.out.println("-    ---     -");

        // proxy
        PersonDao personDaoProxy = new PersonDaoProxy(
                new PersonDaoImpl()
        );
        Person person = personDaoProxy.findById(5);
        personDaoProxy.save(person);
    }
}
/*
Proxy advantages:
- ability to change behaviour of existing beans without changing the original code
- separation of concerns: logging, transactions, security etc

Proxy disadvantages:
- code is harder to debug
- need to use unchecked exceptions for exceptions not declared in the original method
- performance issues if proxy code is using IO
- unexpected == operator results (proxy object and proxied object are really different kind of objects)
 */