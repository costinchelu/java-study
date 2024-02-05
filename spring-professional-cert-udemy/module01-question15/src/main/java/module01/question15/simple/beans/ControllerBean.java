package module01.question15.simple.beans;

import org.springframework.stereotype.Controller;

@Controller
public class ControllerBean {

    public ControllerBean() {
        System.out.println(getClass().getSimpleName() + "::constructor");
    }
}
