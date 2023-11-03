package sec16b_lambdafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button clickMeButton;

    public void initialize() {

        // action handler added by code:
//        clickMeButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                System.out.println("You clicked me! (action handler added by code)");
//            }
//        });

        // action handler with lambda:
        clickMeButton.setOnAction(actionEvent -> System.out.println("You clicked me! (action handler added with lambda)"));
    }

}
