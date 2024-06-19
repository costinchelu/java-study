package howto;


public class PassByReferenceOrValue {

    public static void main(String[] args) {
        var roar1 = "roar";                     // IMMUTABLE
        var roar2 = new StringBuilder("roar");  // MUTABLE
        int e = 1;                              // VALUE
        Integer ee = 1;                         // IMMUTABLE
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

class DragonWarriorReferenceChallenger {

    public static void main(String... doYourBest) {
        StringBuilder warriorProfession = new StringBuilder("Dragon ");     // mutable
        String warriorWeapon = "Sword ";                                    // immutable

        changeWarriorClass(warriorProfession, warriorWeapon);
        /*
        warriorProfession is indeed a mutable object. When it is passed to changeWarriorClass() a copy of the reference
        to the actual object in the heap is created (specifically for that method call). But a copy to that reference means
        that through that copy, we can still access the original object in the heap, as the original reference. So we can
        indeed modify it. But when we assign null to that reference, we are only assigning null to the copy and with
        that we are cutting the link of the copied reference to the actual object in the heap. Still, the original reference
        from the main method is not affected by this assignment, because the assignment is done only for the local copy of
        the reference.
        * */
        System.out.println("Warrior=" + warriorProfession + " &&  Weapon=" + warriorWeapon);
    }

    static void changeWarriorClass(StringBuilder warriorProfession, String weapon) {
        warriorProfession.append("Knight");
        weapon = "Dragon " + weapon;

        weapon = null;
        warriorProfession = null;
        // assigning null to a reference variable only removes the reference, not the object itself
    }
}

