package problems.strings;

public class CheckIfArrOfStringsIsSorted {

   public boolean isSorted(String[] a) {
      for (int i = 1; i < a.length; i++) {
         if (a[i - 1].compareTo(a[i]) > 0)
            return false;
      }
      return true;
   }
}
