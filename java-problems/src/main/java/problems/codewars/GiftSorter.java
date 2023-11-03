package problems.codewars;

/*
Happy Holidays fellow Code Warriors!
Santa's senior gift organizer Elf developed a way to represent up to 26 gifts by assigning a unique alphabetical character to each gift. After each gift was assigned a character, the gift organizer Elf then joined the characters to form the gift ordering code.

Santa asked his organizer to order the characters in alphabetical order, but the Elf fell asleep from consuming too much hot chocolate and candy canes! Can you help him out?

Sort the Gift Code
Write a function called sortGiftCode/sort_gift_code/SortGiftCode that accepts a string containing up to 26 unique alphabetical characters, and returns a string containing the same characters in alphabetical order.

Examples:
GiftSorter gs = new GiftSorter();
gs.sortGiftCode( 'abcdef' ); //=>  'abcdef'
gs.sortGiftCode( 'pqksuvy' ); //=>  'kpqsuvy'
gs.sortGiftCode( 'zyxwvutsrqponmlkjihgfedcba' ); //=>  'abcdefghijklmnopqrstuvwxyz'
*/

public class GiftSorter{

    public String sortGiftCode(String code){
        char[] chArr = code.toCharArray();
        boolean passed;
        do {
            passed = false;

            for (int i = 0; i < chArr.length - 1; i++) {
                if(chArr[i] > chArr[i + 1]) {
                    char aux = chArr[i];
                    chArr[i] = chArr[i + 1];
                    chArr[i + 1] = aux;
                    passed = true;
                }
            }

        } while (passed);

        return String.valueOf(chArr);
    }
}