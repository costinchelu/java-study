package dp_strategy;

public class IsAllNumericStrategy implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
