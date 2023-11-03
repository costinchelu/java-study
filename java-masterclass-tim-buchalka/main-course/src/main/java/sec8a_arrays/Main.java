package sec8a_arrays;

// using arrays (also passing as input & output in methods

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //initializing the array with:
        //		type[] array = new type[5];
        // all numbers will be initialized with 0, booleans with false, strings and objects with null


		/*
		int[] myArray = getIntegers(5);
		System.out.println("Elements of the array are:");
		printArray(myArray);
		System.out.println("The average is " + getAverage(myArray));
		*/

        int[] myIntegers = {8, 3, 5, 4, 2};

        System.out.println("Sorted array looks like:");
        printArrayOneLine(sortIntegers(myIntegers));

        System.out.println("Really sorting array:");
        sortIntegers2(myIntegers);
        printArrayOneLine(myIntegers);
    }

    public static void printArray(int[] array) {
        for(int i = 0; i < array.length; i++){
            System.out.println("Element[" + i + "] = " + array[i]);
        }
    }

    public static void printArrayOneLine(int[] array) {
        System.out.println("Elements of the array are: " + Arrays.toString(array));
    }

    public static int[] getIntegers(int arrayLength) {
        System.out.println("Enter " + arrayLength + " integer values.\r");	// /r for getting the first input on next line
        int[] values = new int[arrayLength];

        for (int i = 0; i < values.length; i++) {
            values[i] = scanner.nextInt();
        }
        return values;
    }

    public static double getAverage(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return (double)sum / (double)array.length;
    }

    public static int[] sortIntegers(int[] array) {

        int[] sortedArray = Arrays.copyOf(array, array.length); //using internal methods
        // bubble sort
        boolean flag = true;
        int temp;
        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i] > sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    flag = true;
                }
            }
        }
        return sortedArray;
    }

    public static void sortIntegers2(int[] array){
        //selection sort
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
