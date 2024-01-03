package problems.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MostFreqNumberInAList {

    public static int findMostFrequentNumber(List<Integer> numbers) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int number : numbers) {
            int frequency = frequencyMap.getOrDefault(number, 0);
            frequencyMap.put(number, frequency + 1);
        }

        int maxFrequency = 0;
        int mostFrequentNumber = 0;
        for (int key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) > maxFrequency) {
                maxFrequency = frequencyMap.get(key);
                mostFrequentNumber = key;
            }
        }

        return mostFrequentNumber;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 2, 1, 2);
        int mostFrequentNumber = findMostFrequentNumber(numbers);
        System.out.println("The most frequent number is: " + mostFrequentNumber);
    }
}

