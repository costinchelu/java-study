package ocp;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ConcurrentCollections {

    public static void main(String[] args) {
//        var foodData = new HashMap<String, Integer>();  // java.util.ConcurrentModificationException
        // since the iterator on keySet() is not properly updated after the first element is removed.

        var foodData = new ConcurrentHashMap<String, Integer>();  // java.util.ConcurrentModificationException
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);

        for (String key : foodData.keySet()) {
            foodData.remove(key);
        }



        List<Integer> favNumbers = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
        for (var n : favNumbers) {
            System.out.print(n + " "); // 4 3 42
            favNumbers.add(n + 1);
        }
        System.out.println();
        System.out.println("Size: " + favNumbers.size()); // Size: 6
        System.out.println(favNumbers);  // [4, 3, 42, 5, 4, 43]


    }
}
