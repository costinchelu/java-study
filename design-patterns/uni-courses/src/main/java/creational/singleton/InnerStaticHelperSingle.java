package creational.singleton;

import lombok.Getter;

@Getter
public class InnerStaticHelperSingle {

    private final String name;

    private final double amount;

    private InnerStaticHelperSingle() {
        this.name = "FOO";
        this.amount = 1;
    }

    private static class InitHelper {

        private static final InnerStaticHelperSingle instance = new InnerStaticHelperSingle();

    }

    public static InnerStaticHelperSingle getInstance() {
        return InitHelper.instance;
    }
}
