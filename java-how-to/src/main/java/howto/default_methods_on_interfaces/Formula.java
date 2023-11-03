package howto.default_methods_on_interfaces;

// Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the
// default keyword. This feature is also known as Extension Methods.
interface Formula {

   double calculate(int a);

   default double sqrt(int a) {
      return Math.sqrt(a);
   }
}

class Demo {

   public static void main(String[] args) {

      Formula formula = new Formula() {

         @Override
         public double calculate(int a) {
            return sqrt(a);
         }
      };

      System.out.println(formula.calculate(100));

      // Default methods cannot be accessed from within howto.stream_api.lambda expressions.
   }
}