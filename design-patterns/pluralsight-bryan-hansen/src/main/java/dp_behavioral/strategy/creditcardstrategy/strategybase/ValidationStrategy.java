package dp_behavioral.strategy.creditcardstrategy.strategybase;


import dp_behavioral.strategy.creditcardstrategy.client.CreditCard;

public abstract class ValidationStrategy {

    public abstract boolean isValid(CreditCard creditCard);

    // verify that the card is legitimate
    protected boolean passesLuhn(String ccNumber) {

        int sum = 0;
        boolean alternate = false;

        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if(alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
