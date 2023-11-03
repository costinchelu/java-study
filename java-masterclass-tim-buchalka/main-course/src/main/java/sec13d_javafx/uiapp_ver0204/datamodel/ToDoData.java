package sec13d_javafx.uiapp_ver0204.datamodel;

import javafx.collections.FXCollections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;


// a Singleton class

public class ToDoData {
    private static String filename = "TodoListItems.txt";
    private static ToDoData instance = new ToDoData();

    private List<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance() {
        return instance;
    }

    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

// It was temporary used just to set hardcoded items from Controller when first running program with IO features
//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

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
