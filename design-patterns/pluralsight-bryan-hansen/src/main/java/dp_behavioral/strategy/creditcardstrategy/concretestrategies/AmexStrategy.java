package dp_behavioral.strategy.creditcardstrategy.concretestrategies;


import dp_behavioral.strategy.creditcardstrategy.client.CreditCard;
import dp_behavioral.strategy.creditcardstrategy.strategybase.ValidationStrategy;

public class AmexStrategy extends ValidationStrategy {

    @Override
    public boolean isValid(CreditCard creditCard) {

        boolean isValid = true;

        isValid = creditCard.getNumber().startsWith("37") || creditCard.getNumber().startsWith("34");

        if (isValid) {
            isValid = creditCard.getNumber().length() == 15;
        }

        if (isValid) {
            isValid = passesLuhn(creditCard.getNumber());
        }

        return isValid;
    }
}
