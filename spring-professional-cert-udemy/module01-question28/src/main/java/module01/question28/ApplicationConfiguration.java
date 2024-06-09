package module01.question28;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class ApplicationConfiguration {

    // need a ConversionService bean so that arrays from the property file in the form: 1, 2, 3
    // to be correctly loaded into a List or a set structure
    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }
}
