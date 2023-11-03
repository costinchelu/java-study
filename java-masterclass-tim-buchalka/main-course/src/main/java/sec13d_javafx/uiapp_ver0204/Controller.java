package sec13d_javafx.uiapp_ver0204;

import sec13d_javafx.uiapp_ver0204.datamodel.TodoItem;
import sec13d_javafx.uiapp_ver0204.datamodel.ToDoData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;

    //only for first part of the tutorial (hardcoded values):
    private List<TodoItem> todoItems;


    @FXML
    private void initialize() {
/*
** OLD CODE: to hardcode values at the initialization no longer needed
**    because we have methods to save and load items in the singleton class
**
**        todoItems = new ArrayList<>();
**
**        TodoItem item1 = new TodoItem("Mail birthday card",
**                "Buy a 30th birthday card for John",
**                LocalDate.of(2016, Month.APRIL, 25));
**
**        TodoItem item2 = new TodoItem("Doctor's Appointment",
**                "See Dr. Smith at 123 Example1 Street.  Bring paperwork",
**                LocalDate.of(2016, Month.MAY, 23));
**
**        TodoItem item3 = new TodoItem("Finish design proposal for client",
**                "I promised Mike I'd email website mockups by Friday 22nd April",
**                LocalDate.of(2016, Month.APRIL, 22));
**
**        TodoItem item4 = new TodoItem("Pickup Doug at the train station",
**                "Doug's arriving on March 23 on the 5:00 train",
**                LocalDate.of(2016, Month.MARCH, 23));
**
**        TodoItem item5 = new TodoItem("Pick up dry cleaning",
**                "The clothes should be ready by Wednesday",
**                LocalDate.of(2016, Month.APRIL,20));
**
**        todoItems.add(item1);
**        todoItems.add(item2);
**        todoItems.add(item3);
**        todoItems.add(item4);
**        todoItems.add(item5);
**
**        ToDoData.getInstance().setTodoItems(todoItems);
*/
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMMM yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

/*
**  OLD version for loading dataset was:
**
**          todoListView.getItems().setAll(todoItems);
**
**  It was used to preload the hardcoded list made on previous commented lines
*/

        todoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());  //will load from singleton class
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }


//uses for the New... menu option
    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e){
            System.out.println("Couldn't load the dialog!");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // an optional part to test signal for pressing buttons in the dialog window
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            todoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());
            System.out.println("OK pressed");
            todoListView.getSelectionModel().select(newItem);
        }
        else {
            System.out.println("CANCEL pressed");
        }
    }


    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();

        itemDetailTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());

/*        System.out.println("The selected item is " + item);
**        StringBuilder sb = new StringBuilder(item.getDetails());
**        sb.append("\n\n\n\n");
**        sb.append("Due: ");
**        sb.append(item.getDeadline().toString());
**        itemDetailTextArea.setText(sb.toString()); */
    }


}
