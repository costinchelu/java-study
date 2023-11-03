package problems.luxcity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * You should sort elements in an array by decreasing frequency of elements.
 * If two elements have the same frequency, sort them by increasing value.
 *
 * In the input:
 *
 *      arr - array of numbers
 *  At the output: sorted array of numbers
 *
 *  Example:
 *
 * [4, 10, 3, 6, 4, 4, 8, 8, 6] -->  [4, 4, 4, 6, 6, 8, 8, 3, 10]
 */
class IntFreqSort {

    public static List<Integer> frequencySort(List<Integer> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer no : arr) {
            if (!map.containsKey(no)) {
                map.put(no, 0);
                for (Integer search : arr) {
                    if (search.equals(no)) {
                        map.put(no, map.get(no) + 1);
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();

        Integer[] keys = new Integer[map.size()];
        Integer[] values = new Integer[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            keys[index] = mapEntry.getKey();
            values[index] = mapEntry.getValue();
            index++;
        }

        for(int i = 0; i < values.length; i++) {
            index = 0;
            int max = values[0];
            for (int j = 0; j < values.length; j++) {
                if(values[j] > max) {
                    max = values[j];
                    index = j;
                }
            }
            for(int k = 0; k < max; k++) {
                result.add(keys[index]);
            }
            values[index] = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] intArr = {4, 10, 3, 6, 4, 4, 8, 8, 6};
        List<Integer> list = Arrays.asList(intArr);
        System.out.println(frequencySort(list));
    }
}


//        List<Integer> keys = new ArrayList<>(map.keySet());
//        List<Integer> values = new ArrayList<>(map.values());
//        while (!values.isEmpty()) {
//            int max = values.get(0);
//            Iterator<Integer> it = values.listIterator();
//            while (it.hasNext()) {
//                if(it.)
//            }
//        }
//
//
//        for (Integer key : keys) {
//            int toMultiply = values.get(keys.indexOf(key));
//            for (int  i = 0; i < toMultiply; i++) {
//                result.add(key);
//            }
//        }