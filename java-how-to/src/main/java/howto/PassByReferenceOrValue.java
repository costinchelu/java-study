package howto;


public class PassByReferenceOrValue {

    public static void main(String[] args) {
        var roar1 = "roar";
        var roar2 = new StringBuilder("roar");
        int e = 1;
        Integer ee = 1;
        new Lion().roar(roar1, roar2, e, ee);
        System.out.println(roar1 + " " + roar2 + " " + e + " " + ee);
        /*
        we are getting:

                    roar roar!!! 1 1

         > Passing a StringBuilder means we are passing a reference. Any operation inside the roar method will be applied
         on the actual Object we passed.

         > On the other hand, any operation on the int primitive will not be applied because we are passing just a
         value.

         > In the case of Integer - we know that this is an object (a wrapper over int) - it behaves as if it were a value type.
          Integer objects are immutable, meaning their values cannot be changed after creation.
          So, when you perform operations on an Integer within a method, you're actually creating a new Integer object
          with the result of the operation. This does not change the original Integer object that was passed in.

          > Same as with String. It is immutable, so inside roar() we create another string.
         */
    }
}

class Lion {

    public void roar(String roar1, StringBuilder roar2, int primitive, Integer wrappper) {
        roar1.concat("!!!");
        roar2.append("!!!");
        primitive++;
        wrappper++;
    }
}

