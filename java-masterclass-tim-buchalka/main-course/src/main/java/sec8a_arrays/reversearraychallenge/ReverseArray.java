package sec8a_arrays.reversearraychallenge;

/*
-Write a method called reverse() with an int array as a parameter.
-The method should not return any value. In other words, the method is allowed to modify the array parameter.
-In main() test the reverse() method and print the array both reversed and non-reversed.
-To reverse the array, you have to swap the elements, so that the first element
is swapped with the last element and so on.
-For example, if the array is {1, 2, 3, 4, 5}, then the reversed array is {5, 4, 3, 2, 1}.
Tip:
-Create a new console project with the name ReverseArrayChallenge
*/


import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) {
        int[] intArray = new int[10];
        for (int i = 0; i < intArray.length; i++){
            intArray[i] = (int)(Math.random() * 40);
        }
        System.out.println("Our array is " + Arrays.toString(intArray));
        reverse(intArray);
        System.out.println("Reversed array is " + Arrays.toString(intArray));
    }

    private static void reverse(int[] array) {
        int[] copyArray = Arrays.copyOf(array, array.length);
        for(int i = 0, j = array.length - 1; i < array.length; i++, j--) {
            array[i] = copyArray[j];
        }

        /*  ALTERNATIV
        int maxIndex = array.length - 1;
        int halfLength = array.length / 2;
        for(int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
         */
    }
}
