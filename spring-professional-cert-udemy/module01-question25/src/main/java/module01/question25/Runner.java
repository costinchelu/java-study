package module01.question25;

import module01.question25.beans.mappers.DataMapper;
import module01.question25.beans.processors.DataProcessor;
import module01.question25.beans.readers.DataReader;
import module01.question25.beans.writer.DataWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerShutdownHook();

        // Activate profile
        context.getEnvironment().setActiveProfiles("database", "prod");
        context.register(ApplicationConfiguration.class);
        context.refresh();

        System.out.println(context.getBean(DataMapper.class).getClass().getSimpleName());
        System.out.println(context.getBean(DataProcessor.class).getClass().getSimpleName());
        System.out.println(context.getBean(DataReader.class).getClass().getSimpleName());
        System.out.println(context.getBean(DataWriter.class).getClass().getSimpleName());
    }
}
