package module01.question04.beans;

import org.springframework.stereotype.Component;

@Component
class Engine {
    void start() {
        System.out.println("Engine is started");
    }
}
