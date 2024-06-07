package module01.question21;

import module01.question21.beans.SpringBean1;
import module01.question21.beans.SpringBean3C;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        SpringBean1 springBean1 = context.getBean(SpringBean1.class);
        springBean1.printDependencies();

        SpringBean3C springBean5th = (SpringBean3C) context.getBean("springBean5th");
        springBean5th.printHash();
        SpringBean3C springBean6th = (SpringBean3C) context.getBean("springBean6th");
        springBean6th.printHash();
    }
}
