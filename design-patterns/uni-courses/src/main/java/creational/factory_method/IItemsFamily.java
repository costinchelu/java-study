package creational.factory_method;

import lombok.Setter;

public interface IItemsFamily {

    void doSomething();
}

interface ItemFactory {

    IItemsFamily create();
}

class FactoryItem1 implements ItemFactory {

    @Override
    public IItemsFamily create() {
        return new Item1();
    }
}

@Setter
class Item1 implements IItemsFamily {

    private String someString;

    @Override
    public void doSomething() {
        System.out.println("Item 1 type - " + someString);
    }
}

class FactoryItem2 implements ItemFactory {

    @Override
    public Item2 create() {
        return new Item2();
    }
}

@Setter
class Item2 implements IItemsFamily {

    private int someInt;

    @Override
    public void doSomething() {
        System.out.println("Item 2 type - " + someInt);
    }
}

class SimpleFactory {

    enum ItemType {
        ITEM1, ITEM2
    }

    public static IItemsFamily create(ItemType type) throws Exception {
        switch (type) {
            case ITEM1 -> {
                return new Item1();
            }
            case ITEM2 -> {
                return new Item2();
            }
            default -> throw new Exception("Not a correct type");
        }
    }
}


class Demo {

    public static void main(String[] args) {
        Item1 item1 = (Item1) createAnItem(new FactoryItem1());
        item1.setSomeString("Test");
        item1.doSomething();

        Item2 item2 = (Item2) createAnItem(new FactoryItem2());
        item2.setSomeInt(10);
        item2.doSomething();

        try {
            Item1 item3 = (Item1) SimpleFactory.create(SimpleFactory.ItemType.ITEM1);
            item3.setSomeString("Test2");
            item3.doSomething();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static IItemsFamily createAnItem(ItemFactory factory) {
        return factory.create();
    }
}