package module01.question01.spring.dependency.injection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    private static final String REPORT = "pdf-reports";
//    private static final String REPORT = "xls-reports";

    public static void main(String... args) {
        AnnotationConfigApplicationContext context = getSpringContext(REPORT);

        EmployeesSalariesReportService employeesSalariesReportService = context.getBean(EmployeesSalariesReportService.class);
        employeesSalariesReportService.generateReport();

        context.close();
    }

    private static AnnotationConfigApplicationContext getSpringContext(String profile) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(profile);
        context.register(Configuration.class);
        context.refresh();
        return context;
    }
}
