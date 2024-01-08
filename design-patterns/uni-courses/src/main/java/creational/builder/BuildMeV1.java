package creational.builder;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class BuildMeV1 implements IBuildMe {

    private String strItem;

    private int intItem;

    private boolean boolItem;

    public BuildMeV1(String strItem, int intItem, boolean boolItem) {
        this.strItem = strItem;
        this.intItem = intItem;
        this.boolItem = boolItem;
    }
}


class V1Builder implements Builder {

    private final BuildMeV1 v1 = new BuildMeV1("default", 0, false);

    @Override
    public BuildMeV1 build() {
        return v1;
    }

    public V1Builder strItem(String strItem) {
        this.v1.setStrItem(strItem);
        return this;
    }

    public V1Builder intItem(int intItem) {
        this.v1.setIntItem(intItem);
        return this;
    }

    public V1Builder boolItem(boolean boolItem) {
        this.v1.setBoolItem(boolItem);
        return this;
    }
}


class V1Demo {

    public static void main(String[] args) {
        BuildMeV1 demo =
                new V1Builder()
                        .strItem("test")
                        .intItem(10)
                        .build();
        System.out.println(demo);
    }
}