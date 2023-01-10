/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    // public static void main(String[] args) {
    //     String word = "";
    //
    //     while (!StdIn.isEmpty()) {
    //         String input = StdIn.readString();
    //         if (StdRandom.bernoulli()) {
    //             word = input;
    //         }
    //     }
    //
    //     StdOut.println(word);
    // }

    public static void main(String[] args) {
        int index = 0;
        String champion = "";

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            boolean accept = StdRandom.bernoulli(1 / (index + 1.0));
            if (accept) {
                champion = word;
            }
            index++;
        }
        StdOut.println(champion);
    }
}
