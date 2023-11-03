package module01.question21;

import module01.question21.beans.SpringBean1;
import module01.question21.beans.SpringBean2;
import module01.question21.beans.SpringBean3;
import module01.question21.beans.SpringBean3A;
import module01.question21.beans.SpringBean3B;
import module01.question21.beans.SpringBean3C;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;

@Configuration
public class ApplicationConfig {
    @Bean(initMethod = "init")
    @Autowired
    public SpringBean1 springBean1(SpringBean2 springBean2, SpringBean3 springBean3rd) {
        return new SpringBean1(springBean2, springBean3rd);
    }

    @Bean(destroyMethod = "destroy")
    public SpringBean2 springBean2() {
        return new SpringBean2();
    }

    @Bean(name = "springBean3rd")
    @Autowired
    public SpringBean3 springBean3A(MessageDigest messageDigest) {
        return new SpringBean3A(messageDigest);
    }

    @Bean(name = {"springBean4th", "springBeanNo4", "springBeanNoFour"})
    public SpringBean3 springBean3B() {
        return new SpringBean3B();
    }

    @Bean
    public SpringBean3 springBean3C() {
        return new SpringBean3C();
    }

    @Bean
    public MessageDigest messageDigest() {
        return DigestUtils.getSha384Digest();
    }
}
