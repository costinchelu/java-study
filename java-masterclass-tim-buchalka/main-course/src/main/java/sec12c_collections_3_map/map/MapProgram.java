package sec12c_collections_3_map.map;

import java.util.HashMap;

public class MapProgram {
    public static void main(String[] args) {

        java.util.Map<String, String> languages = new HashMap<>();
        languages.put("Java", "a compiled high level object-oriented, platform independent language");
        languages.put("Python", "an interpreted, object oriented, high-level programming language with dynamic semantics");
        languages.put("Algol", "an algorithmic language");
        languages.put("BASIC", "Beginners All Purposes Symbolic Instruction Code");
        languages.put("Lisp", "Therein lies madness");

        System.out.println(languages.get("Java"));

        if(languages.containsKey("Java")){
            languages.put("Java", "this course is about Java");
        }
        else {
            languages.putIfAbsent("Java", "object-oriented language");
        }
        System.out.printf(languages.get("Java"));

        languages.remove("Lisp");
        languages.replace("Algol", "  an algorithmic language");
        languages.replace("Algol", "  an algorithmic language", "an algorithmic language");

        System.out.println("\n___Looping through the map:");

        for(String key: languages.keySet()) {
            System.out.println(key + " : " + languages.get(key));
        }
    }
}
