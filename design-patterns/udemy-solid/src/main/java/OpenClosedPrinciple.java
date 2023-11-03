import java.util.List;
import java.util.stream.Stream;

enum Color {
   RED,
   GREEN,
   BLUE
}

enum Size {
   SMALL,
   MEDIUM,
   LARGE,
   HUGE
}

class Product {

   public String name;

   public Color color;

   public Size size;

   public Product(String name, Color color, Size size) {
      this.name = name;
      this.color = color;
      this.size = size;
   }
}

class ProductFilter {

   public Stream<Product> filterByColor(List<Product> products, Color color) {
      return products.stream().filter(p -> p.color == color);
   }

   public Stream<Product> filterBySize(List<Product> products, Size size) {
      return products.stream().filter(p -> p.size == size);
   }

   public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
      return products.stream().filter(p -> p.size == size && p.color == color);
   }
   // state space explosion
   // that is for 2 criteria
   // for 3 criteria we may need 7 methods
   // at no point in time we would want to jump in this method and add or modify stuff...
   // that would mean it is open for modification.
   // Better to construct those classes and let them open for extension
}

// SPECIFICATION PATTERN
// we introduce two new interfaces that are open for extension
// by using generics we could filter anything
interface Specification<T> {

   boolean isSatisfied(T item);
}

/**
 * we can now construct concrete specifications for every attribute we would
 * like to filter <br>
 * isSatisfied method will return true if the Product has the attribute that we
 * are looking for
 */
class ColorSpecification implements Specification<Product> {

   private Color color;

   public ColorSpecification(Color color) {
      this.color = color;
   }

   @Override
   public boolean isSatisfied(Product p) {
      return p.color == color;
   }
}

// also size specification
class SizeSpecification implements Specification<Product> {

   private Size size;

   public SizeSpecification(Size size) {
      this.size = size;
   }

   @Override
   public boolean isSatisfied(Product p) {
      return p.size == size;
   }
}

// we can also construct a specification that filters more than one attribute
class AndSpecification<T> implements Specification<T> {

   private Specification<T> first, second;

   public AndSpecification(Specification<T> first, Specification<T> second) {
      this.first = first;
      this.second = second;
   }

   @Override
   public boolean isSatisfied(T item) {
      return first.isSatisfied(item) && second.isSatisfied(item);
   }

}

// abstract filter that will return a stream of objects that have the stated
// specification
interface Filter<T> {

   Stream<T> filter(List<T> items, Specification<T> spec);
}

// concrete filter
class BetterFilter implements Filter<Product> {

   @Override
   public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
      return items.stream().filter(spec::isSatisfied);
   }
}

public class OpenClosedPrinciple {

   public static void main(String[] args) {
      Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
      Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
      Product house = new Product("House", Color.BLUE, Size.LARGE);

      List<Product> products = List.of(apple, tree, house);

      ProductFilter pf = new ProductFilter();
      System.out.println("Green products (old):");
      // filter and print
      pf.filterByColor(products, Color.GREEN).forEach(p -> System.out.println(" - " + p.name + " is green"));

      // ^^ BEFORE

      // vv AFTER
      BetterFilter bf = new BetterFilter();
      System.out.println("Green products (new and improved filtering):");
      bf.filter(products, new ColorSpecification(Color.GREEN))
            .forEach(p -> System.out.println(" - " + p.name + " is green"));

      System.out.println("Large products:");
      bf.filter(products, new SizeSpecification(Size.LARGE))
            .forEach(p -> System.out.println(" - " + p.name + " is large"));

      System.out.println("Large blue items:");
      bf.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
            .forEach(p -> System.out.println(" - " + p.name + " is large and blue"));
   }
}