package behavioral.composite;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


interface OptionComponent {

    void deleteNode(OptionComponent o);

    void addNode(OptionComponent o);

    OptionComponent getNode(int index);

    void description();
}



record ItemLeaf(String info) implements OptionComponent {

    @Override
    public void deleteNode(OptionComponent o) {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void addNode(OptionComponent o) {
        throw new RuntimeException("No implementation");
    }

    @Override
    public OptionComponent getNode(int index) {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void description() {
        System.out.println("leaf -> " + info);
    }
}



@ToString
@RequiredArgsConstructor
class CategoryComposite implements OptionComponent {

    private List<OptionComponent> list = new ArrayList<>();

    @Getter
    private final String info;

    @Override
    public void deleteNode(OptionComponent o) {
        list.remove(o);
    }

    @Override
    public void addNode(OptionComponent o) {
        list.add(o);
    }

    @Override
    public OptionComponent getNode(int index) {
        return list.get(index);
    }

    @Override
    public void description() {
        System.out.println("category -> " + info);
        list.forEach(OptionComponent::description);
    }
}




public class Composite {

    public static void main(String[] args) {
        OptionComponent option1 = new CategoryComposite("option1");
        OptionComponent option2 = new CategoryComposite("option2");
        OptionComponent option3 = new CategoryComposite("option3");
        OptionComponent leaf1 = new ItemLeaf("leaf1");
        OptionComponent leaf2 = new ItemLeaf("leaf2");
        OptionComponent leaf3 = new ItemLeaf("leaf3");
        OptionComponent leaf4 = new ItemLeaf("leaf4");
        OptionComponent leaf5 = new ItemLeaf("leaf5");
        OptionComponent leaf6 = new ItemLeaf("leaf6");

        option1.addNode(leaf1);
        option1.addNode(leaf2);
        option2.addNode(leaf3);
        option2.addNode(leaf4);
        option3.addNode(leaf5);
        option3.addNode(leaf6);
        option1.addNode(option2);
        option1.addNode(option3);
        option1.deleteNode(leaf2);
        option1.addNode(leaf2);
        option1.description();
    }
}
