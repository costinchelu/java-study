import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions (abstract class or interface).

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

enum Relationship {
   PARENT,
   CHILD,
   SIBLING
}

class Person {

   public String name;
   // other details

   public Person(String name) {
      this.name = name;
   }
}

interface RelationshipBrowser {

   List<Person> findAllChildrenOf(String name);
}

// low level implementation (data storage)
class Relationships implements RelationshipBrowser {

   // Triplet class requires javatuples
   private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

   public List<Triplet<Person, Relationship, Person>> getRelations() {
      return relations;
   }

   public void addParentAndChild(Person parent, Person child) {
      relations.add(new Triplet<>(parent, Relationship.PARENT, child));
      relations.add(new Triplet<>(child, Relationship.CHILD, parent));
   }

   public void addSibling(Person sibling1, Person sibling2) {
      relations.add(new Triplet<>(sibling1, Relationship.SIBLING, sibling2));
      relations.add(new Triplet<>(sibling2, Relationship.SIBLING, sibling1));
   }

   public List<Person> findAllChildrenOf(String name) {
      return relations.stream()
              .filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT)
              .map(Triplet::getValue2).collect(Collectors.toList());
   }
}

// high level implementation
class Research {

   public Research(Relationships relationships) {
      // high-level: find all of john's children
      List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
      relations.stream().filter(x ->
                      x.getValue0().name.equals("John") &&
                      x.getValue1() == Relationship.PARENT)
            .forEach(child -> System.out.println("John has a child called " + child.getValue2().name));
   }

   public Research(RelationshipBrowser browser, Person person) {
      List<Person> children = browser.findAllChildrenOf(person.name);
      for (Person child : children)
         System.out.println(person.name + " has a child called " + child.name);
   }
}

class DependencyInversionPrinciple {

   public static void main(String[] args) {
      Person parent = new Person("John");
      Person child1 = new Person("Chris");
      Person child2 = new Person("Matt");

      // low-level module
      Relationships relationships = new Relationships();
      relationships.addParentAndChild(parent, child1);
      relationships.addParentAndChild(parent, child2);
      relationships.addSibling(child1, child2);
      new Research(relationships);

      // it's using relationshipBrowser (for dependency inversion)
      new Research(relationships, parent);
   }
}
