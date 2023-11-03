package dp_chain_of_responsability;

public class SpellCheckerProcessing extends ProcessingObject<String> {

   public String handleWork(String text) {
      return text.replaceAll("labda", "lambda");
   }
}
