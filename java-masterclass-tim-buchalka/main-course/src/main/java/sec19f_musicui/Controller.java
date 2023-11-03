package sec19f_musicui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sec19f_musicui.model.Album;
import sec19f_musicui.model.Artist;
import sec19f_musicui.model.Datasource;

public class Controller {
    // any code that touches the UI has to run in the UI thread
    // we should always run a query on a background Task
    // data binding will run on the UI Thread

    // (property="name" from the .fxml) works either with name from Artist or the one from Album
    @FXML
    private TableView artistTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void listArtists() {
        // the method will start when the stage is shown and also when List Artist Button is pressed
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());

        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    // this will get all albums from an artist
    @FXML
    public void listAlbumsForArtist() {
        // the method is accessed when pressing Show Albums Button
        // get the selected list item
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null) {
            System.out.println("NO ARTIST SELECTED");
            return;
        }
        // get the list (another background thread for query)
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());
        // start the thread
        new Thread(task).start();
    }

    @FXML
    public void updateArtist() {
        // this is how normally we would select the artist:
//        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();

        // but we will hardcode the selection because it's not the scope of the lesson to create a new dialog
        // so that the user can actually update with a chosen value
        final Artist artist = (Artist) artistTable.getItems().get(2);

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            if(task.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });

        new Thread(task).start();
    }
}

// that is a background task that will run the query which will get all Artists
class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artist> call() {
        return FXCollections.observableArrayList(Datasource.getInstance().queryArtistOrder(Datasource.ORDER_BY_ASC));
    }
}
