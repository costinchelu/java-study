package module01.question19.proxy.jdk;

import module01.question19.dao.PersonDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonDaoInvocationHandler implements InvocationHandler {

    private final PersonDao target;

    public PersonDaoInvocationHandler(PersonDao target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("LOGGING - before " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("LOGGING - after " + method.getName());
        return result;
    }
}
