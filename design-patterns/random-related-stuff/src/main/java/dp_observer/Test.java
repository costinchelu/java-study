package dp_observer;

public class Test {

   public static void main(String[] args) {
      Feed f = new Feed();

      f.registerObserver(new NYTimes());
      f.registerObserver(new Guardian());
      f.registerObserver(new LeMonde());

      f.notifyObservers("The queen said her favourite wine is Merlot!");

      /*
       * different classes implementing the Observer interface are all providing
       * implementation for a single method: notify. They’re all just wrapping a piece
       * of behavior to execute when a tweet arrives! Lambda expressions are designed
       * specifically to remove that boilerplate. Instead of instantiating three
       * observer objects explicitly, we can pass a lambda expression directly to
       * represent the behavior to execute:
       */

      Feed f2 = new Feed();

      f2.registerObserver((String tweet) -> {
         if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
         }
         if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet another news in London... " + tweet);
         }
         if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
         }
      });

      f2.notifyObservers("The queen said her favourite wine is Merlot!");
   }
}
