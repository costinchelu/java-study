package sec13e_css;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


public class Controller {

    @FXML
    private Label label;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;
    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter() {
        label.setScaleX(1.5);
        label.setScaleY(1.5);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save App Filter");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("All Files", "."),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif")
        );

//opening a file from file chooser:
        //chooser.showOpenDialog(gridPane.getScene().getWindow());

//opening a folder from directory chooser:
        //DirectoryChooser chooser = new DirectoryChooser();
        //File file = chooser.showDialog(gridPane.getScene().getWindow());

//file save chooser:
//        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
//        if(file != null) {
//            System.out.println(file.getPath());
//        }
//        else {
//            System.out.println("Chooser was cancelled");
//        }

//multiple file chooser:
        List<File> file = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());

        //for test:
        if (file != null) {
            for (int i = 0; i < file.size(); i++) {
                System.out.println(file.get(i));
            }
        } else {
            System.out.println("Chooser was cancelled");
        }
    }

    @FXML
    public void handleLinkClick() {
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.oracle.com"));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        WebEngine engine = webView.getEngine();
        engine.load("https://www.oracle.com/ro/INDEX.HTML");
    }


}


