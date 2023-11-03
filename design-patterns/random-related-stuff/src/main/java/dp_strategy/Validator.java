package dp_strategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Validator {

    private final ValidationStrategy strategy;

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
