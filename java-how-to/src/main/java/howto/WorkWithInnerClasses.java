package howto;

public class WorkWithInnerClasses {

    public static void main(String[] args) {
        OuterClass.StaticInnerClass.staticInnerMethod();

        OuterClass.StaticInnerClass sic = new OuterClass.StaticInnerClass("instance field");
        sic.instanceInnerMethod();

        OuterClass oc = new OuterClass();
        OuterClass.InstanceInnerClass iic = oc.new InstanceInnerClass();
        iic.instanceInnerMethod();
    }
}


class OuterClass {

    private static String staticOuterField = "Static Outer Field";

    private String instanceOuterField = "Instance Outer Field";

    public void outerClassMethod() {
        String s1 = StaticInnerClass.staticInnerField;

        String s2 = InstanceInnerClass.staticInnerField;

        InstanceInnerClass iic = new InstanceInnerClass();
        String s3 = iic.instanceInnerField;

        OuterClass.StaticInnerClass sic = new StaticInnerClass("instance field");
        String s4 = sic.instanceInnerFieldInStaticInnerClass;
    }

    public class InstanceInnerClass {

        private String instanceInnerField = "Instance Inner Field";

        // Static declarations in inner classes are not supported in JDK 11
        private static String staticInnerField = "Instance static inner field";

        public void instanceInnerMethod() {
            System.out.println(staticOuterField);           // Can access static outer field
            System.out.println(staticInnerField);   // Can access static inner field
            System.out.println(instanceOuterField);         // Can access instance outer field
            System.out.println(instanceInnerField);         // Can access instance inner field
        }
    }

    public static class StaticInnerClass {

        private static String staticInnerField = "Static Inner Field";

        // a non-static inner field is not accessible
        private String instanceInnerFieldInStaticInnerClass;

        // we need a constructor in order to access an instance inner field
        public StaticInnerClass(String i) {
            this.instanceInnerFieldInStaticInnerClass = i;
        }

        public static void staticInnerMethod() {
            System.out.println(staticOuterField);       // Can access static outer field
            // System.out.println(instanceOuterField);  // Cannot access instance outer field directly
            System.out.println(staticInnerField);       // Can access static inner field
        }

        public void instanceInnerMethod() {
            System.out.println(instanceInnerFieldInStaticInnerClass);
        }
    }
}