package sec15n_task_javafx.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {

//    private Task<ObservableList<String>> task;

    @FXML
    private ListView listView;

    @FXML
    public ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    private Service<ObservableList<String>> service;

    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
////                String[] names = {
////                        "Tim Buchalka",
////                        "Bill Rogers",
////                        "Jack Jill",
////                        "Joan Andrews",
////                        "Mary Johnson",
////                        "Bob McDonald"};
////
////                ObservableList<String> employees = FXCollections.observableArrayList();
////
////                for(int i = 0; i < names.length; i++) {
////                    employees.add(names[i]);
////                    updateMessage("Added " + names[i]);
////                    updateProgress(i + 1, names.length);
////                    Thread.sleep(200);
////                }
////
////                return employees;
////            }
//        };

//        progressBar.progressProperty().bind(task.progressProperty());
//        progressLabel.textProperty().bind(task.messageProperty());
//        listView.itemsProperty().bind(task.valueProperty());

        service = new EmployeeService();

        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());

        // tying the visibility of the progress bar and label with the state of the service
        // first we are doing with event handlers, or much simpler, with visibleProperty method
//        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(true);
//                progressLabel.setVisible(true);
//            }
//        });
//
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(false);
//                progressLabel.setVisible(false);
//            }
//        });
//
//        progressBar.setVisible(false);
//        progressLabel.setVisible(false);

        // the more "elegant" sollution
        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
    }

    @FXML
    public void buttonPressed() {
//        new Thread(service).start();

        /*
        * we are verifying the state of the service because after the service is started, the state is
        * switched to SUCCEEDED. Because of that, if we press again the button, we are getting an exception
        * So we better reset the state to avoid the exception.
        * In case the state is FAILED or SCHEDULED, we wouldn't wanna start it again.
        *
         */
        if(service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        } else if(service.getState() == Service.State.READY) {
            service.start();
        }
    }
}

/*
 *
 * We will simulate fetching data from a database on a background thread (using a task). Because we didn't covered
 * databases yet, we will simulate by hard coding some Strings instead.
 *
 * The ObservableList is fed from the String array. Simultaneously we update the progress bar and progress label
 * with the corresponding info. For that we are using methods updateMessage and updateProgress. We are also
 * introducing a small delay to simulate a slow database fetch.
 * We need to bind the different controls to the operations (with the bind method)
 *
 * On the service course we are implementing an EmployeeService class.
 * In this case, we need to implement the service (instead of method).
 * So all of the content regarding Task and ObservableList will be moved in the EmployeeService class
 *
 * In the end we will use a service to start the Task rather than using a Thread
 *
 * This is the recommended way to work with tasks in javafx. We can use Tasks directly but using a service allows the
 * javafx runtime to manage threads for us
 *
 * The Service class has additional methods we can use to hide the progress bar in different states
 *
 */