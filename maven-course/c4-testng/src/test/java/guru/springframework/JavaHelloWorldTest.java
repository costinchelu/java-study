package guru.springframework;

import org.testng.annotations.Test;

public class JavaHelloWorldTest {

    @Test
    public void testGetHello() {
        JavaHelloWorld javaHelloWorld = new JavaHelloWorld();
        assert("Hello World".equals(javaHelloWorld.getHello()));
    }
}