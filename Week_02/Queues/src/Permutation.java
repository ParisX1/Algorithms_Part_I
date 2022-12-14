/*
 * Client. Write a client program Permutation.java that takes an integer k as 
 * a command-line argument; reads a sequence of strings from standard input 
 * using StdIn.readString(); and prints exactly k of them, uniformly at random. 
 * Print each item from the sequence at most once.
 */

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> myRandomQueue = new RandomizedQueue<String>();

        // Read input and add to queue
        while (!StdIn.isEmpty()) {
            myRandomQueue.enqueue(StdIn.readString());
        }

        // Create iterator and print k objects
        Iterator<String> myIterator = myRandomQueue.iterator();
        for (int i = 0; i < k; i++)
            System.out.println(myIterator.next());
    }
}
