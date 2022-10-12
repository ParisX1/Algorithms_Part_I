/*
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of the 
 * data structure. Create a generic data type Deque that implements the following API.
   https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque {

    private Node first = null;
    private Node last = null;
    private int dequeSize;
    

    // construct an empty deque
    public Deque() {

    }

    private class Node {
        Object item;
        Node next;
        
        // construct a Node
        public Node() {

        }

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return dequeSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        dequeSize++;
    }

    // add the item to the back
    public void addLast(Item item) {
        
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Object item = first.item;
        first = first.next;
        return item;
        dequeSize--;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Object item = last.item;
        last = last.next;
        return item;
        dequeSize--;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        
    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}
