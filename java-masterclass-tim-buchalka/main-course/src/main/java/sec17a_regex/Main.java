package sec17a_regex;

public class Main {

    public static void main(String[] args) {
        // simplest form
        String string = "I am a string. Yes I am.";
        System.out.println(string);

        String yourString = string.replaceAll("I am", "You're");
        System.out.println(yourString);     // prints YYYYYYYYYYYYYYYYYYYYYYY

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";


        // ALL .
        System.out.println(alphanumeric.replaceAll(".", "Y"));


        // BEGINNING WITH ^
        System.out.println(alphanumeric.replaceAll("^abcDeee", " THE BEGINNING "));
        String secondString = "abcDeeeF12GhhabcDeeiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDee", " THE BEGINNING "));


        // FINISHING WITH $
        System.out.println(alphanumeric.replaceAll("l99z$", " THE END "));


        // AND operator (implicit) = "abc" means "a" and "b" and "c"


        // specific characters selection = []
        System.out.println(alphanumeric.replaceAll("[aei]", " REPLACE "));
        // this replaces F || j only if they are preceded by a || e || i
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", " REPLACE "));
        // this replaces Harry || harry  with Harry
        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));


        // OR operator = |
        System.out.println("harry".replaceAll("[H|h]arry", "Harry"));


        // NOT operator = [^] or !
        String tvTest = "tstvtkt";  // find all t's not followed by v's
        System.out.println(tvTest.replaceAll("t[^v]", " REPLACE "));    // REPLACE tv REPLACE t
        System.out.println(tvTest.replaceAll("t(?!v)", " REPLACE "));   // REPLACE stv REPLACE k REPLACE
        // POSITIVE LOOK AHEAD ?=
        System.out.println(tvTest.replaceAll("t(?=v)", " REPLACE "));   // ts REPLACE vtkt


        // RANGE -
        System.out.println(alphanumeric.replaceAll("[a-gA-D]", " REPLACE "));


        // IGNORING CASE SENSITIVITY
        System.out.println(alphanumeric.replaceAll("(?i)[a-e]", " REPLACE "));
        // FOR unicode: System.out.println(alphanumeric.replaceAll("(?iu)[a-e]", " REPLACE "));


        // EXCEPT [^...] is character class not boundary matcher =
        System.out.println(alphanumeric.replaceAll("[^ej]", " REPLACE "));


        // replace all numbers
        System.out.println(alphanumeric.replaceAll("\\d", " NO NUMBERS "));
        // replace all letters
        System.out.println(alphanumeric.replaceAll("\\D", " NO LETTERS "));


        // replace all whitespaces
        String hasWhiteSpace = "I have blanks and\ta tab. And also a newline\n";
        System.out.println(hasWhiteSpace);
        System.out.println(hasWhiteSpace.replaceAll("\\s", ""));


        // replace all characters a-Z, 0-9 and _
        System.out.println(alphanumeric.replaceAll("\\w", "X"));
        // replace anything BUT characters a-Z, 0-9, _
        System.out.println(hasWhiteSpace.replaceAll("\\W", "X"));


        // surrounds words (detected with white-spaces)
        System.out.println(hasWhiteSpace.replaceAll("\\b", "X"));


        /*
        Validating a US phone number like:

                        (800) 123-4567

        ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$


        ^([\(]{1}[0-9]{3}[\)]{1} = ^ no chars before start of the expression followed by {1} char of (
        (which will be escaped with \), after that will have {3} chars in range 0-9 followed by {1} )

        [ ]{1} then we will have one space

        {3} chars in range 0-9
        [\-]{1} a single - (dash) (also escaped)
        [0-9]{4})$ {4} chars in range 0-9 and nothing after the expression $ (means it will be at the end)



        Validating a Romanian mobile phone number with or without whole country prefix:

                +40712345678 or 0040712345678 or 0712345678

        ^((004)*|(\+4)*)07[0-9]{8}$



        Validating a VISA card:

        ^4[0-9]{12}([0-9]{3})?$

        (starts with a 4. New accounts have 16 numbers but old accounts have 13)
        ( in this case ? will match or not the previous round brackets content)



        Another hypothetical example:

              ^[A-Za-z]\d[A-Za-z][ -]?\d[A-Za-z]\d$

         we have a letter followed by digit followed by letter then they will optionally be a blank or
         a dash and then a number followed by letter followed by a number
        */
    }
}
