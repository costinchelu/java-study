package money_01;

// some immutable class

public class Money {

   private final int amount;

   private final String currency;

   public Money(int amount, String currency) {
      if (amount < 0) {
         throw new IllegalArgumentException("illegal negative amount: [" + amount + "]");
      }
      if (currency == null || currency.isEmpty()) {
         throw new IllegalArgumentException("illegal currency: [" + currency + "], it can not be null or empty");
      }
      this.amount = amount;
      this.currency = currency;
   }

   public int getAmount() {
      return amount;
   }

   public String getCurrency() {
      return currency;
   }

   public boolean equals(Object o) {
      if (o instanceof Money) {
         Money money = (Money) o;
         return money.getCurrency().equals(getCurrency()) && getAmount() == money.getAmount();
      }
      return false;
   }
}
