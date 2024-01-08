package creational.singleton;

import lombok.Getter;

@Getter
public class LazySingle {

    private final String name;

    private final double amount;

    private static LazySingle instance = null;

    private LazySingle(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public static LazySingle getInstance(String name, double amount) {
        if (instance == null) {
            instance = new LazySingle(name, amount);
        }
        return instance;
    }
}
