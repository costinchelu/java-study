package module01.question17;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:/app.properties")
public class ApplicationConfig {
}
