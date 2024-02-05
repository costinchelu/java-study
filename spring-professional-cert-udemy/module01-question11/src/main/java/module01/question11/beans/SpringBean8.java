package module01.question11.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
@Lazy(false)
public class SpringBean8 {

    public SpringBean8() {
        System.out.println("Creating " + getClass().getSimpleName() + " - Bean from Configuration class");
    }
}
