package howto;

import lombok.Getter;

/*
MEMBERS OF OTHER CLASSES
ROLE: ORGANIZE THE CODE

- static member classes
- inner classes (3 types: non-static, local and anonymous)
 */
public class WorkWithInnerClasses {

    public static void main(String[] args) {
        // access to the static inner class
        EnclosingClass.StaticMemberClass.staticInnerMethod();

        EnclosingClass.StaticMemberClass sc = new EnclosingClass.StaticMemberClass("instance field");
        sc.instanceInnerMethod();

        // access to the inner class
        EnclosingClass oc = new EnclosingClass();
        EnclosingClass.InstanceInnerClass ic = oc.new InstanceInnerClass();
        ic.instanceInnerMethod();

        // local class example
        class LocalClass {

            int m = 5;
        }

        LocalClass lc = new LocalClass();
        lc.m += 1;
        System.out.println(lc.m);

        // Anonymous class example
        SomeInterface si = new SomeInterface() {

            @Override
            public void someMethod() {
                System.out.println("Anonymous");
            }
        };
        //        SomeInterface si = () -> System.out.println("Anonymous");

        si.someMethod();
    }
}

@Getter
class EnclosingClass {

    // need to be initialized
    private static String staticOuterField;

    private String instanceOuterField = "Instance Outer Field";

    // static initialization block
    static {
        staticOuterField = "Static Outer Field";
    }

    public void enclosingClassMethod() {
        // test accessing the static and inner classes:

        String s1 = StaticMemberClass.staticInnerField;

        String s2 = InstanceInnerClass.staticInnerField;

        InstanceInnerClass iic = new InstanceInnerClass();
        String s3 = iic.instanceInnerField;

        StaticMemberClass sic = new StaticMemberClass("instance field");
        String s4 = sic.instanceInnerFieldInStaticInnerClass;
    }

    class InstanceInnerClass {

        private String instanceInnerField = "Instance Inner Field";

        // Static declarations in inner classes are not supported in JDK 11
        private static String staticInnerField = "Instance static inner field";

        public void instanceInnerMethod() {
            System.out.println(staticInnerField);           // Can access static inner field
            System.out.println(instanceInnerField);         // Can access instance inner field

            System.out.println(staticOuterField);           // Can access static outer field DIRECTLY
            System.out.println(instanceOuterField);         // Can access instance outer field DIRECTLY
        }
    }

    static class StaticMemberClass {

        private static String staticInnerField = "Static Inner Field";

        // a non-static inner field is not accessible
        private String instanceInnerFieldInStaticInnerClass;

        // we need a constructor in order to access an instance inner field
        public StaticMemberClass(String i) {
            this.instanceInnerFieldInStaticInnerClass = i;
        }

        // Although it is enclosed, a static member class cannot access the enclosing class’s instance fields and invoke its instance methods.
        public static void staticInnerMethod() {
            System.out.println(staticInnerField);       // Can access its own static inner field (as expected for a static method)
            System.out.println(staticOuterField);       // Can access static outer field DIRECTLY

            // System.out.println(instanceOuterField);  // Cannot access instance outer field (like the inner class instance method)
        }

        public void instanceInnerMethod() {
            System.out.println(staticInnerField);                       // can access its own static inner field as expected
            System.out.println(instanceInnerFieldInStaticInnerClass);   // can access its own instance inner field as expected

            System.out.println(EnclosingClass.staticOuterField);        // can access static outer field
            EnclosingClass ec = new EnclosingClass();
            ec.enclosingClassMethod();
            System.out.println(ec.getInstanceOuterField());             // can access instance methods and getters of outer class
        }
    }
}

interface SomeInterface {
    void someMethod();
}
