package dp_creational.builder.no_interface;

public class UsingBuilder {

    public static class SandwichBuilder {

        private String bread;
        private String condiments;
        private String dressing;
        private String meat;

        public SandwichBuilder() {

        }

        public UsingBuilder makeASandwich() {
            return new UsingBuilder(this);
        }

        public SandwichBuilder buildBread(String bread) {
            this.bread = bread;
            return this;
        }

        public SandwichBuilder BuildCondiments(String condiments) {
            this.condiments = condiments;
            return this;
        }

        public SandwichBuilder buildDressing(String dressing) {
            this.dressing = dressing;
            return this;
        }

        public SandwichBuilder buildMeat(String meat) {
            this.meat = meat;
            return this;
        }
    }

    private final String bread;
    private final String condiments;
    private final String dressing;
    private final String meat;

    private UsingBuilder(SandwichBuilder sandwichBuilder) {
        this.bread = sandwichBuilder.bread;
        this.condiments = sandwichBuilder.condiments;
        this.dressing = sandwichBuilder.dressing;
        this.meat = sandwichBuilder.meat;
    }

    public String getBread() {
        return bread;
    }

    public String getCondiments() {
        return condiments;
    }

    public String getDressing() {
        return dressing;
    }

    public String getMeat() {
        return meat;
    }

    @Override
    public String toString() {
        return "No_interface.UsingBuilder{" +
                "bread='" + bread + '\'' +
                ", condiments='" + condiments + '\'' +
                ", dressing='" + dressing + '\'' +
                ", meat='" + meat + '\'' +
                '}';
    }
}
/*
Our constructor will get attribute values initialized from the builder object we get
 (they are also final so they will be immutable)

Our Builder (inner class) will have a constructor with no parameters (but it can have a constructor
with any number of parameters as long as the built objects will need those attributes by default)

A mandatory aspect is that we will have a build() method (in this case makeASandwith() )
that will only return a new required instance. The instance will be built attribute by attribute using methods
for every attribute that can be built.

We have no setters so all attributes will be immutable.

 */