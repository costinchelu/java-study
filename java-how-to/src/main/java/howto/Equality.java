package howto;

/**
 * use == to compare references
 * <br>
 * use equals() to compare values
 */
class Equality {

    public static void main(String[] args) {
        String s1 = "hello"; // String literal
        String s2 = "hello"; // Another string literal
        String s3 = new String("hello"); // New string object

        System.out.println(s1 == s2); // Output: true
        System.out.println(s1.equals(s2)); // Output: true

        System.out.println(s1 == s3); // Output: false
        System.out.println(s1.equals(s3)); // Output: true
    }
}
