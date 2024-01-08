package creational.builder;

import lombok.ToString;

@ToString
public class BuildMeV2 implements IBuildMe {

    private String strItem;

    private int intItem;

    private boolean boolItem;

    public BuildMeV2(String strItem, int intItem, boolean boolItem) {
        this.strItem = strItem;
        this.intItem = intItem;
        this.boolItem = boolItem;
    }
}

class V2Builder implements Builder {

    private String strItem;

    private int intItem;

    private boolean boolItem;

    @Override
    public BuildMeV2 build() {
        return new BuildMeV2(strItem, intItem, boolItem);
    }

    public V2Builder strItem(String strItem) {
        this.strItem = strItem;
        return this;
    }

    public V2Builder intItem(int intItem) {
        this.intItem = intItem;
        return this;
    }

    public V2Builder boolItem(boolean boolItem) {
        this.boolItem = boolItem;
        return this;
    }
}


class V2Demo {

    public static void main(String[] args) {
        BuildMeV2 demo =
                new V2Builder()
                        .intItem(10)
                        .strItem("Demo")
                        .build();
        System.out.println(demo);
    }
}