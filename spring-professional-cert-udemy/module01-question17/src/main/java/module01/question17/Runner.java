package module01.question17;

import module01.question17.service.ReportService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.registerShutdownHook();

        ReportService bean = context.getBean(ReportService.class);
        bean.execute();
    }
}
