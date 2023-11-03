package problems.luxcity;

class NoOfWords {
    /**
     * Write a function that takes a string and returns the number of four letters names.
     * The input argument names is a string with names separated by spaces without punctuation.
     *
     * Example:
     *
     * names = "Tror Gvigris Deriana Nori"
     * fourletters(names) = 2
     */
    public static int fourLetters(String names) {
        String[] splitter = names.split(" ");
        int response = 0;
        for (String s : splitter) {
            if (s.length() == 4) {
                response++;
            }
        }
        return response;
    }

    public static void main(String[] args) {
        String names = "Tor Gis Deriana Nri";
        System.out.println(fourLetters(names));
    }
}
