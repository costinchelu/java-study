package dp_chain_of_responsability;

public class FooterTextProcessing extends ProcessingObject<String> {

   public String handleWork(String text) {
      return text + "\n\n--- END ---";
   }
}
