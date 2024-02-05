package module01.question15.simple.beans;

import org.springframework.stereotype.Component;

@Component
public class ComponentBean {

    public ComponentBean() {
        System.out.println(getClass().getSimpleName() + "::constructor");
    }
}
