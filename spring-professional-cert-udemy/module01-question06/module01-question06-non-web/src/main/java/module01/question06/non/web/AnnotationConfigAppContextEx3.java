package module01.question06.non.web;

import module01.question06.non.web.beans.SpringBean1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigAppContextEx3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("module01.question06.non.web.beans");
        context.refresh();

        SpringBean1 bean = context.getBean(SpringBean1.class);

        bean.sayHello();
    }
}
