package dp_structural.composite.mapexample;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, String> personAttributes = new HashMap<>();

        personAttributes.put("site_role", "person");
        personAttributes.put("access_role", "limited");



        Map<String, String> groupAttributes = new HashMap<>();

        groupAttributes.put("group_role", "claims");



        Map<String, String> secAttributes = new HashMap<>();

        secAttributes.putAll(personAttributes);
        secAttributes.putAll(groupAttributes);


        System.out.println(secAttributes);
        System.out.println(personAttributes);
    }
}
/*
Create hierarchical recursive tree structures of related objects
 - any element of the structure may be accessed and utilised in a standard manner


 */