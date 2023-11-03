package howto;

import java.util.*;

/**
 * Remove duplicate elements from a list using a set (Collections).
 * The list is made by using the elements of an array
 */
public class RemoveDuplicateElementsFromList {

    private static final String[] arr = {"String1", "String2", "String2", "String3", "String4", "String4", "String5"};


    public static void main(String[] args) {

        List<String> theList = new ArrayList<>(Arrays.asList(arr));

        System.out.println("Initial list:");
        printList(theList);

        System.out.println("Convert list to hashSet (linkedHashSet if order is important)");
        Set<String> theSet = new LinkedHashSet<>(theList);

        theList.clear();

        System.out.println("Copy from set to list");
        theList.addAll(theSet);

        System.out.println("No duplicate list:");
        printList(theList);
    }

    public static <E> void printList(List<E> aList) {
        for (E e : aList) {
            System.out.println(e);
        }
    }
}
