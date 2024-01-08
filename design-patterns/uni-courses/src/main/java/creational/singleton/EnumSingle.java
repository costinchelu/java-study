package creational.singleton;

import lombok.Getter;

@Getter
public enum EnumSingle {

    INSTANCE;

    private final String name;

    private final double amount;

    EnumSingle() {
        this.name = "FOO";
        this.amount = 1;
    }
}
