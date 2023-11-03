import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Journal {

   private final List<String> entries = new ArrayList<>();

   private static int count = 0;

   public void addEntry(String text) {
      entries.add("" + (++count) + ": " + text);
   }

   public void removeEntry(int index) {
      entries.remove(index);
   }

   @Override
   public String toString() {
      return String.join(System.lineSeparator(), entries);
   }

   // --------------------- from here on, we break SRP
   public void save(String filename) throws Exception {
      try (PrintStream out = new PrintStream(filename)) {
         out.println(toString());
      }
   }

   public void load(String filename) {
   }

   public void load(URL url) {
   }
}

// a new class to handle the responsibility of persisting objects would let our
// Journal class to handle just the journal keeping, as it should
class Persistence {

   public void saveToFile(Journal journal, String filename, boolean overwrite) throws Exception {
      if (overwrite || new File(filename).exists())
         try (PrintStream out = new PrintStream(filename)) {
            out.println(journal.toString());
         }
   }

   // other methods that refer to persistence
   public void load(Journal journal, String filename) {
   }

   public void load(Journal journal, URL url) {
   }
}

public class SingleResponsibilityPrinciple {

   public static void main(String[] args) throws Exception {
      Journal j = new Journal();
      j.addEntry("I cried today");
      j.addEntry("I ate a bug");
      System.out.println(j);

      Persistence p = new Persistence();
      String filename = "c:\\temp\\journal.txt";
      p.saveToFile(j, filename, true);

      // windows!
      Runtime.getRuntime().exec("notepad.exe " + filename);
   }
}
