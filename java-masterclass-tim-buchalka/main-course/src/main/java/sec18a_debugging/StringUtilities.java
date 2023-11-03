package sec18a_debugging;

public class StringUtilities {

    private StringBuilder sBuilder = new StringBuilder();
    private int charsAdded = 0;

    public void addChar(StringBuilder sBuilder, char c) {
        this.sBuilder.append(c);
        charsAdded++;
    }

    public String upperAndPrefix(String str) {
        String upper = str.toUpperCase();
        return "Prefix_".toUpperCase() + upper;
    }

    public String addSuffix(String str) {
        return str + "_Suffix";
    }

    public StringBuilder getsBuilder() {
        return sBuilder;
    }
}
