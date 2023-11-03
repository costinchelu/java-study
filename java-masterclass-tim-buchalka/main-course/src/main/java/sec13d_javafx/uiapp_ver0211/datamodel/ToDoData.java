package sec13d_javafx.uiapp_ver0211.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;


// a Singleton class

public class ToDoData {
    //the only instance of itself:
    private static ToDoData instance = new ToDoData();

    // using an observable list provides us with data binding
    private ObservableList<TodoItem> todoItems;
    private static String filename = "TodoListItems.txt";
    private DateTimeFormatter formatter;


    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static ToDoData getInstance() {
        return instance;
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

    public void deleteTodoItem(TodoItem item) {
        todoItems.remove(item);
    }


    //writing and loading to/from txt file
    public void storeTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<TodoItem> iter = todoItems.iterator();
            while (iter.hasNext()) {
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
                //after writing object attributes on same line (separated by tabs) get on the next line
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void loadToDoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        //need to be observable because of the reading buffer

        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;   //for reading line by line from txt file
        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

}
