package dp_creational.builder.no_interface;

public class UsingTelescopingConstructors {

    private String bread;
    private String condiments;
    private String dressing;
    private String meat;

    public UsingTelescopingConstructors(String bread) {
        this.bread = bread;
    }

    public UsingTelescopingConstructors(String bread, String condiments) {
        this(bread);
        this.condiments = condiments;
    }

    public UsingTelescopingConstructors(String bread, String condiments, String dressing) {
        this(bread, condiments);
        this.dressing = dressing;
    }

    public UsingTelescopingConstructors(String bread, String condiments, String dressing, String meat) {
        this(bread, condiments, dressing);
        this.meat = meat;
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
        return "No_interface.UsingTelescopingConstructors{" +
                "bread='" + bread + '\'' +
                ", condiments='" + condiments + '\'' +
                ", dressing='" + dressing + '\'' +
                ", meat='" + meat + '\'' +
                '}';
    }
}
