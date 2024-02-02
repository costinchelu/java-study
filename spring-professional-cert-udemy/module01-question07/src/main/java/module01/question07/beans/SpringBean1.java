package module01.question07.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SpringBean1 implements InitializingBean, DisposableBean {

    private SpringBean2 springBean2;

    public SpringBean1() {
        System.out.println("\nCreating (using constructor) " + getClass().getSimpleName());
    }

    public void sayHello() {
        System.out.println("\nHello! " + getClass().getSimpleName() + " is now used." + "\n");
    }

    @Autowired
    public void setSpringBean2(SpringBean2 springBean2) {
        System.out.println("settingProperty (Autowired setter) on " +
                getClass().getSimpleName() +
                " to inject " +
                springBean2.getClass().getSimpleName());
        this.springBean2 = springBean2;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct " + getClass().getSimpleName());
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean::afterPropertiesSet " + getClass().getSimpleName());
    }

    private void initMethod() {
        System.out.println("@Bean(initMethod) " + getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy " + getClass().getSimpleName());
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean::destroy " + getClass().getSimpleName());
    }

    private void destroyMethod() {
        System.out.println("@Bean(destroyMethod) " + getClass().getSimpleName());
    }
}
