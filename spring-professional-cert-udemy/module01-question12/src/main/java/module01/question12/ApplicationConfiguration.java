package module01.question12;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan
@PropertySources({
        @PropertySource("file:${app-home}/app-db.properties"),
        @PropertySource("classpath:/app-defaults.properties")
})
public class ApplicationConfiguration {
}