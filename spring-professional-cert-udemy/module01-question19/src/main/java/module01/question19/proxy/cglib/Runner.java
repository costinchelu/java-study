package module01.question19.proxy.cglib;

import module01.question19.dao.DepartmentDao;
import module01.question19.ds.Department;
import org.springframework.cglib.proxy.Enhancer;

public class Runner {

    public static void main(String[] args) {

        // no need for the proxied object to implement an interface
        // doesn't work for final classes/methods
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new DepartmentDaoMethodInterceptor());
        enhancer.setSuperclass(DepartmentDao.class);
        DepartmentDao departmentDao = (DepartmentDao) enhancer.create();

        Department department = departmentDao.findById(5);
        departmentDao.save(department);
    }
}
