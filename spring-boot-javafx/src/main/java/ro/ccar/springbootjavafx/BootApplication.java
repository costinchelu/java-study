package ro.ccar.springbootjavafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        Application.launch(JavafxApplication.class, args);
    }
}
