package module01.question06.non.web;

import module01.question06.non.web.beans.SpringBean1;
import module01.question06.non.web.defined.configuration.ConfigurationStatic;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigAppContextEx1b {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigurationStatic.class);

        SpringBean1 bean = context.getBean(SpringBean1.class);

        bean.sayHello();
    }
}
