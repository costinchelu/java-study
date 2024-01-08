package creational.singleton;

import lombok.Getter;

@Getter
public class StaticBlockSingle {

    private final String name;

    private final double amount;

    @Getter
    private static StaticBlockSingle instance;

    static {
        try {
            instance = new StaticBlockSingle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StaticBlockSingle() {
        this.name = "FOO";
        this.amount = 1;
    }
}
