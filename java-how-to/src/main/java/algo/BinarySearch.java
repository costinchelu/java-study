package algo;

/**
uses at most 1 + log2 of arr.size compares
 */
public class BinarySearch {
    /**
     * Returns index of {@code key} if it is present in the sorted array else return -1
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (key == arr[mid]) return mid;
            else if (key > arr[mid]) low = mid + 1;
            else high = mid - 1;
        }
        // element is not present
        return -1;
    }

    public static int binarySearchRecursive(int key, int[] arr, int low, int high) {
        if (low > high) {
            // (base case) element is not present
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (key > arr[mid]) return binarySearchRecursive(key, arr, mid + 1, high);
        else if (key < arr[mid]) return binarySearchRecursive(key, arr, low, mid - 1);
        else return mid;
    }

    // Driver method to test above
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int x = 6;
        int result = binarySearch(arr, x);
        if (result == -1) {
            System.out.println("Element not present");
        } else {
            System.out.println("Element found at index " + result);
        }
    }
}
