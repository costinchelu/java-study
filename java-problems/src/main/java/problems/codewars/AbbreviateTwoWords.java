package problems.codewars;

/*
JAVA
Abbreviate a Two Word Name
8 kyu

Task
Write a function to convert a name into initials. This kata strictly takes two words with one space in between them.

The output should be two capital letters with a dot separating them.

It should look like this:

Sam Harris => S.H

Patrick Feeney => P.F

*/


public class AbbreviateTwoWords {

    public static String abbrevName(String name) {
        String[] splitter = name.split(" ");
        return (splitter[0].charAt(0) + "." + splitter[1].charAt(0)).toUpperCase();
    }
}
