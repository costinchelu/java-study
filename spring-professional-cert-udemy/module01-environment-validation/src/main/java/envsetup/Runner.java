package envsetup;

import envsetup.beans.HelloBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        HelloBean helloBean = context.getBean(HelloBean.class);

        helloBean.sayHello();
    }
}
