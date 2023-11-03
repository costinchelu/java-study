package dp_chain_of_responsability;

import lombok.Setter;

@Setter
public abstract class ProcessingObject<T> {

   protected ProcessingObject<T> successor;

   public T handle(T input) {
      T r = handleWork(input);
      if (successor != null) {
         return successor.handle(r);
      }
      return r;
   }

   abstract protected T handleWork(T input);
}