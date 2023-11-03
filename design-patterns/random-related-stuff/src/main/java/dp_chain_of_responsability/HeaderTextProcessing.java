package dp_chain_of_responsability;

public class HeaderTextProcessing extends ProcessingObject<String> {

   public String handleWork(String text) {
      return "From Costin: " + text;
   }
}
