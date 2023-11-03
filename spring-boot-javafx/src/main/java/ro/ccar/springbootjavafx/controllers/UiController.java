package ro.ccar.springbootjavafx.controllers;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class UiController {

    private final HostServices hostServices;

    @FXML
    public Label lb1;

    @FXML
    public Button bt1;

    public UiController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public void initialize() {
        this.bt1.setOnAction( actionEvent -> this.lb1.setText(this.hostServices.getDocumentBase()));
    }


}
