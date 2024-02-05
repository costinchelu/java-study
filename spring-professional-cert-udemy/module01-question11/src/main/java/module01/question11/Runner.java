package module01.question11;

import module01.question11.beans.SpringBean3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        context.registerShutdownHook();

        // for a prototype scoped bean to be instantiated:
//        context.getBean(SpringBean3.class);
    }
}
