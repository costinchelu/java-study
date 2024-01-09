package creational.prototype;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



interface IDeepObj {

    IDeepObj copy();

    void loadOperation();

}


@Setter
class DeepObj implements IDeepObj {

    private List<String> items;

    public DeepObj() {
        items = new ArrayList<>();
    }

    @Override
    public IDeepObj copy() {
        List<String> tempItem = new ArrayList<>();
        tempItem.addAll(this.items);
        DeepObj copy = new DeepObj();
        copy.setItems(tempItem);
        return copy;
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
        return "Items{ "  + items + " }" + "-" + Objects.hashCode(items);
    }

}



public class DeepPrototype {
    public static void main(String[] args) {
        IDeepObj obj = new DeepObj();
        obj.loadOperation();

        IDeepObj copy1 = obj.copy();
        IDeepObj copy2 = obj.copy();
        IDeepObj copy3 = obj.copy();
        System.out.println(obj);
        System.out.println(copy1);
        System.out.println(copy2);
        System.out.println(copy3);
    }
}

