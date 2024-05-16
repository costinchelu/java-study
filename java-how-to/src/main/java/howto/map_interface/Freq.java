package howto.map_interface;

import java.util.*;

public class Freq {

    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<>();

        // Initialize frequency table from command line
        for (String a : args) {
            m.compute(a, (k, freq) -> (freq == null) ? 1 : freq + 1);
        }

        System.out.println(m.size() + " distinct words:");
        System.out.println(m);
    }

    public static <K, V> boolean validate(Map<K, V> attrMap, Set<K> requiredAttrs, Set<K>permittedAttrs) {
        boolean valid = true;
        Set<K> attrs = attrMap.keySet();

        if (! attrs.containsAll(requiredAttrs)) {
            Set<K> missing = new HashSet<K>(requiredAttrs);
            missing.removeAll(attrs);
            System.out.println("Missing attributes: " + missing);
            valid = false;
        }
        if (! permittedAttrs.containsAll(attrs)) {
            Set<K> illegal = new HashSet<K>(attrs);
            illegal.removeAll(permittedAttrs);
            System.out.println("Illegal attributes: " + illegal);
            valid = false;
        }
        return valid;
    }
}
