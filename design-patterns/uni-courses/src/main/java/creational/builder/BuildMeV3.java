package creational.builder;

import lombok.ToString;

@ToString
public class BuildMeV3 implements IBuildMe {

    private final String strItem;

    private final int intItem;

    private final boolean boolItem;

    private BuildMeV3(V3Builder builder) {
        this.strItem = builder.strItem;
        this.intItem = builder.intItem;
        this.boolItem = builder.boolItem;
    }

    public static V3Builder builder() {
        return new V3Builder();
    }

    public static class V3Builder implements Builder {

        private String strItem;

        private int intItem;

        private boolean boolItem;

        @Override
        public BuildMeV3 build() {
            return new BuildMeV3(this);
        }

        public V3Builder strItem(String strItem) {
            this.strItem = strItem;
            return this;
        }

        public V3Builder intItem(int intItem) {
            this.intItem = intItem;
            return this;
        }

        public V3Builder boolItem(boolean boolItem) {
            this.boolItem = boolItem;
            return this;
        }
    }
}


class V3Demo {

    public static void main(String[] args) {
        BuildMeV3 demo =
                BuildMeV3.builder()
                        .strItem("Demo")
                        .intItem(10)
                        .build();
        System.out.println(demo);
    }
}
