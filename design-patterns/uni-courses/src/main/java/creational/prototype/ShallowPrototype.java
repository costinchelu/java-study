package creational.prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShallowPrototype {

    public static void main(String[] args) {
        IShallowObj list = new ShallowObj();
        list.loadOperation();

        try {
            IShallowObj copy1 = list.copy();
            IShallowObj copy2 = list.copy();
            IShallowObj copy3 = list.copy();
            System.out.println(list);
            System.out.println(copy1);
            System.out.println(copy2);
            System.out.println(copy3);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}

interface IShallowObj extends Cloneable {

    IShallowObj copy() throws CloneNotSupportedException;

    void loadOperation();
}

class ShallowObj implements IShallowObj {

    private final List<String> items;

    public ShallowObj() {
        items = new ArrayList<>();
    }

    @Override
    public IShallowObj copy() throws CloneNotSupportedException {
        return (IShallowObj) super.clone();
    }

    @Override
    public void loadOperation() {
        Path inputFile = Paths.get("C:/WORK/in-out/offerList.txt");
        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            String item;
            while ((item = reader.readLine()) != null) {
                items.add(item);
            }
        } catch (IOException e) {
            System.out.println("Error reading input file.");
        }
    }

    @Override
    public String toString() {
        return "Items{ "  + items + " }" + "-" + System.identityHashCode(items);
    }
}
