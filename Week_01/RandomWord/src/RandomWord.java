import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int numWords = 0;
        double probability;
        boolean takingInput = true;
        String currentWord;
        String championString = "";
        while (!StdIn.isEmpty()) {
            currentWord = StdIn.readString();
            numWords++;
            probability = 1 / (double) numWords;
            if (StdRandom.bernoulli(probability)) {
                championString = currentWord;
            }
        }
        // StdOut.println("\nThe selected word is: " + championString);
        StdOut.println(championString);
    }
}
