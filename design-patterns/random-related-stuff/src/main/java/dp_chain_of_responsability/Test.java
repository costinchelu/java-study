package dp_chain_of_responsability;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test {

   public static void main(String[] args) {
      ProcessingObject<String> p1 = new HeaderTextProcessing();
      ProcessingObject<String> p2 = new SpellCheckerProcessing();
      ProcessingObject<String> p3 = new FooterTextProcessing();
      p1.setSuccessor(p2);
      p2.setSuccessor(p3);
      String result = p1.handle("Aren't labdas really useful?!!");
      System.out.println(result);

      /*
       * This pattern looks like chaining (that is, composing) functions! We can
       * represent the processing objects as an instance of Function<String, String>
       * or more precisely a UnaryOperator<String>. To chain them we just need to
       * compose these functions by using the andThen method!
       */

      UnaryOperator<String> headerProcessing = (String text) -> "From Costin: " + text;
      UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
      UnaryOperator<String> footerProcessing = (String text) -> text + "\n\n--- END ---";
      Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing).andThen(footerProcessing);
      String result2 = pipeline.apply("Aren't labdas really sexy?!!");

      System.out.println(result2);
   }
}
