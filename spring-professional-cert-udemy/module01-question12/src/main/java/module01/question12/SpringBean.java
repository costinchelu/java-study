package module01.question12;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {

    @Value("${db.host}")
    private String dbHost;

    @Value("${app.envid}")
    private String appEndId;

    @Value("${external.service}")
    private String externalService;

    @Value("${JAVA_HOME}")
    private String javaHome;

    public void printProperties() {
        System.out.println("db.host = " + dbHost);
        System.out.println("app.envid = " + appEndId);
        System.out.println("external.service = " + externalService);
        System.out.println("JAVA_HOME = " + javaHome);
    }
}

/*
VM options:
-Dapp-home=spring-professional-cert-udemy\\app-home -Dexternal.service=service01

to access app-home folder and create a variable called external.service

JAVA_HOME is an Environment Variable
 */