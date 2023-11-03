package algo;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class LinkedListExample {

   public static void main(String[] args) {
        LL a = new LL(12);
        LL b = new LL(13);
        LL c = new LL(14);
        LL.link(a, b);
        LL.link(a, c);

       LL d = new LL(15);
       LL e = new LL(16);
       LL f = new LL(17);
       d.link(e);
       e.link(f);

       LL g = new LL(18);
       LL h = new LL(19);
       LL i = new LL(20);
       LL.link(g, h);
       LL.link(g, i);

       System.out.println(a);
   }
}

@AllArgsConstructor
@ToString
class LL {

   private int value;

   private LL next;

   public LL(int value) {
       this.value = value;
   }

   public void link(LL add) {
       this.next = add;
   }

   public static LL link(LL a, LL b) {
      if (a == null)
         return b;

      LL last = a;
      while (last.next != null) {
         last = last.next;
      }
      last.next = b;
      return a;
   }

   public static LL append(LL a, LL b) {
       return a == null ? b : new LL(a.value, append(a.next, b));
   }
}
