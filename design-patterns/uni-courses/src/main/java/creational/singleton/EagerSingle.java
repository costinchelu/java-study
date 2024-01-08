package creational.singleton;

import lombok.Getter;

@Getter
public class EagerSingle {

    private final String name;

    private final double amount;

    private static final EagerSingle instance = new EagerSingle();

    private EagerSingle() {
        this.name = "EAGER";
        this.amount = 1;
    }
}


