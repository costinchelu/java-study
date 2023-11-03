package problems;

/** Reverse the values in an array */
public class ReverseArray {

    public static<T> void reverseArray(T[] a) {
        int low = 0;
        int high = a.length - 1;

        while (low < high) {
            T temp = a[low];
            a[low++] = a[high];
            a[high--] = temp;
        }
    }

    public static void reverseArray2(double[] a) {
        for(int i = 0; i < a.length/2; i++) {
            double temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
    }
}
