package module01.question21;

import module01.question21.beans.SpringBean1;
import module01.question21.beans.SpringBean2;
import module01.question21.beans.SpringBean3;
import module01.question21.beans.SpringBean3A;
import module01.question21.beans.SpringBean3B;
import module01.question21.beans.SpringBean3C;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;

@Configuration
public class ApplicationConfig {

    // better to qualify one of the SpringBean3 beans so Spring will know which one to wire
    @Bean(initMethod = "init")
    @Autowired
    public SpringBean1 springBean1(
            SpringBean2 springBean2,
            @Qualifier("springBean3rd") SpringBean3 springBean3) {
        return new SpringBean1(springBean2, springBean3);
    }

    @Bean(destroyMethod = "destroy")
    public SpringBean2 springBean2() {
        return new SpringBean2();
    }

    // autowires MessageDigest, an external library but managed by Spring with @Bean
    // having multiple SpringBean3 types defined - we can qualify a primary bean to be injected in springBean1
    @Bean(name = "springBean3rd")
    @Autowired
    public SpringBean3 springBean3A(MessageDigest messageDigest) {
        return new SpringBean3A(messageDigest);
    }

    // using bean naming we can define multiple beans with the same type
    @Bean(name = {"springBean4th", "springBeanNo4", "springBeanNoFour"})
    public SpringBean3 springBean3B() {
        return new SpringBean3B();
    }

    @Bean(name = "springBean5th")
    public SpringBean3 springBean3C1() {
        return new SpringBean3C();
    }

    @Bean(name = "springBean6th")
    public SpringBean3 springBean3C2() {
        return new SpringBean3C();
    }

    @Bean
    public MessageDigest messageDigest() {
        return DigestUtils.getSha384Digest();
    }
}
