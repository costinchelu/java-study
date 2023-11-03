package dp_strategy;

public class Test {

    public static void main(String[] args) {
        Validator lowerCaseValidator = new Validator(new IsAllLowerCaseStrategy ());
        boolean a1 = lowerCaseValidator.validate("bbbb");
        Validator numericValidator = new Validator(new IsAllNumericStrategy());
        boolean a2 = numericValidator.validate("aaaa");

        System.out.println(a1 + " " + a2);

        // ValidationStrategy is a functional interface (in addition, it has the same function descriptor as Predicate<String>).
        // This means that instead of declaring new classes to implement different strategies, we can pass lambda expressions directly,
        // which are more concise:

        Validator numericValidatorLambda = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b1 = numericValidatorLambda.validate("aaaa");
        Validator lowerCaseValidatorLambda = new Validator((String s) -> s.matches("\\d+"));
        boolean b2 = lowerCaseValidatorLambda.validate("bbbb");

        System.out.println(b1 + " " + b2);
    }
}
