package module01.question32;

import module01.question32.beans.SpringBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    private final SpringBean springBean;

    public SpringBootConsoleApplication(SpringBean springBean) {
        this.springBean = springBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("getAppDefaultProperty: " + springBean.getAppDefaultProperty());
        System.out.println("getAppDevProperty: " + springBean.getAppDevProperty());
        System.out.println("getAppVmProperty: " + springBean.getAppVmProperty());
        System.out.println("getAppEnvProperty: " + springBean.getAppEnvProperty());
        System.out.println("getAppJsonProperty: " + springBean.getAppJsonProperty());
        System.out.println("getAppCliProperty: " + springBean.getAppCliProperty());
        System.out.println("getAppRandomProperty: " + springBean.getAppRandomProperty());
    }
}
/*
VM options: -Dapp.vm.property=appVmProperty
program arguments: --app.cli.property=appCliProperty
environment variables: app.env.property=appEnvProperty;SPRING_APPLICATION_JSON={"app":{"json":{"property":"test"}}}
 */
