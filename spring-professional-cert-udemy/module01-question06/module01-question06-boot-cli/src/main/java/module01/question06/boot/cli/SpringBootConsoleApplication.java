package module01.question06.boot.cli;

import module01.question06.boot.cli.beans.SpringBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    private final SpringBean1 springBean1;

    public SpringBootConsoleApplication(SpringBean1 springBean1) {
        this.springBean1 = springBean1;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        springBean1.sayHello();
    }
}
