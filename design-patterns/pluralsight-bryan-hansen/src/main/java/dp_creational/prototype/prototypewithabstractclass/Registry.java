package dp_creational.prototype.prototypewithabstractclass;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private Map<String, Item> items = new HashMap<>();

    public Registry() {
        loadItems();
    }

    public Item createItem(String key) {
        Item item = null;

        try {
            item = (Item)(items.get(key)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    private void loadItems() {
        Movie movie = new Movie();
        movie.setTitle("Basic PrototypeWithAbstractClass.Movie");
        movie.setPrice(1);
        movie.setRuntime("120 minutes");
        items.put("PrototypeWithAbstractClass.Movie Prototype", movie);

        Book book = new Book();
        book.setTitle("Basic PrototypeWithAbstractClass.Book");
        book.setPrice(1);
        book.setNumberOfPages(100);
        items.put("PrototypeWithAbstractClass.Book Prototype", book);
    }
}
