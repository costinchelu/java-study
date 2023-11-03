public class InterfaceSegregationPrinciple {

}

class Document {
}

interface Machine {

   void print(Document d);

   void fax(Document d) throws Exception;

   void scan(Document d) throws Exception;
}

// ok if you need a multifunction machine
class MultiFunctionPrinter implements Machine {

   public void print(Document d) {
      // implementation
   }

   public void fax(Document d) {
      // implementation
   }

   public void scan(Document d) {
      // implementation
   }
}

class OldFashionedPrinter implements Machine {

   public void print(Document d) {
      // this method can be useful indeed, but the other methods of the interface are not
   }

   // YAGNI = You ain't gonna need it
   public void fax(Document d) throws Exception {
      throw new Exception();
      // old fashioned printer can't fax documents...
   }

   public void scan(Document d) throws Exception {
      throw new Exception();
      // old fashioned printer can't scan documents...
   }
}

// HOW IT WOULD BE DONE CORRECTLY:

interface IPrinter {

   void Print(Document d) throws Exception;
}

interface IScanner {

   void Scan(Document d) throws Exception;
}

class JustAPrinter implements IPrinter {

   public void Print(Document d) {

   }
}

class Photocopier implements IPrinter, IScanner {

   public void Print(Document d) throws Exception {
      throw new Exception();
   }

   public void Scan(Document d) throws Exception {
      throw new Exception();
   }

}

interface IMultiFunctionDevice extends IPrinter, IScanner {

}

class MultiFunctionMachine implements IMultiFunctionDevice {

   // compose this out of several modules (Decorator)
   private IPrinter printer;

   private IScanner scanner;

   public MultiFunctionMachine(IPrinter printer, IScanner scanner) {
      this.printer = printer;
      this.scanner = scanner;
   }

   public void Print(Document d) throws Exception {
      printer.Print(d);
   }

   public void Scan(Document d) throws Exception {
      scanner.Scan(d);
   }
}
