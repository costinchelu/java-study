package dp_behavioral.strategy.creditcardstrategy;


import dp_behavioral.strategy.creditcardstrategy.client.CreditCard;
import dp_behavioral.strategy.creditcardstrategy.concretestrategies.AmexStrategy;
import dp_behavioral.strategy.creditcardstrategy.concretestrategies.VisaStrategy;

public class Main {

    public static void main(String[] args) {

        CreditCard amexCard = new CreditCard(new AmexStrategy());
        amexCard.setNumber("379185883464283");
        amexCard.setDate("04/2020");
        amexCard.setCvv("123");

        System.out.println("Is Amex valid: " + amexCard.isValid());

        CreditCard amexCard2 = new CreditCard(new AmexStrategy());
        amexCard2.setNumber("379185883464123");
        amexCard2.setDate("04/2017");
        amexCard2.setCvv("234");

        System.out.println("Is Amex2 valid: " + amexCard2.isValid());

        CreditCard visaCard = new CreditCard(new VisaStrategy());
        visaCard.setNumber("4539589763663402");
        visaCard.setDate("05/2018");
        visaCard.setCvv("324");

        System.out.println("Is Visa valid: " + visaCard.isValid());
    }
}
