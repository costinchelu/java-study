package module01.question19.proxy.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class ApplicationConfig {

    // Spring is actually creating a CGLib proxy for this config class and uses the proxy under the hood
    // whenever the class is invoked.
    // Because this bean is a singleton we don't want this method to be invoked multiple times (construct only one object)
    // and the proxy will only allow this method to execute once.
    @Bean
    SomeSpringBean springBean() {
        System.out.println("Creating SpringBean in " + getClass().getSimpleName());
        return new SomeSpringBean();
    }
}
