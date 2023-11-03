package sec9c_abstractclass.challenge;

public class Node extends ListItem{

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return this.rightLink;
    }

    @Override
    ListItem setNext(ListItem item) {
        this.rightLink = item;
        return this.rightLink;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem item) {
        this.leftLink = item;
        return this.leftLink;
    }

    @Override
    int compareTo(ListItem item) {
        if(item != null) {
            /*
            public int compareTo​(String anotherString)

Compares two strings lexicographically. The comparison is based on the Unicode value of each character in the strings.
The character sequence represented by this String object is compared lexicographically to the character sequence
represented by the argument string.
The result is a negative integer if this String object lexicographically precedes the argument string.
The result is a positive integer if this String object lexicographically follows the argument string.
The result is zero if the strings are equal; compareTo returns 0 exactly when the equals(Object) method would
return true.
This is the definition of lexicographic ordering.
If two strings are different, then either they have different characters at some index that is a valid index
for both strings, or their lengths are different, or both.

Returns:
the value 0 if the argument string is equal to this string;
a value less than 0 if this string is lexicographically less than the string argument;
and a value greater than 0 if this string is lexicographically greater than the string argument
             */
            return ((String) super.getValue()).compareTo( (String) item.getValue() );
        }
        else {
            return -1;
        }
    }
}
