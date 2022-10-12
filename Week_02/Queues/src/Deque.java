/*
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of the 
 * data structure. Create a generic data type Deque that implements the following API.
   https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    //private Item item;
    private int dequeSize;
    

    // construct an empty deque
    public Deque() {
        //item = (Item) new Object();
        dequeSize = 0;
        first = null;
        last = null;
        // first.item = null;
        // first.next = null;
        // last.item = null;
        // last.next = null;

    }

    private class Node {
        Item item;
        Node next;
        
        // construct a Node
        public Node() {
            // item = (Item) new Object();
            // item = null;
            // next = null;
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

        if (item == null)
            throw new IllegalArgumentException();

        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.next = null;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        /*
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            
        }
         */
        dequeSize++;
        //displayItem(first);
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty())  first = last;
        else            oldLast.next = last;
        
        dequeSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        dequeSize--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.item;
        last = last.next;
        if (isEmpty()) last = null;
        dequeSize--;
        return item;
    }

    private void displayItem(Node node){
        System.out.println(first.item);
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        
        private Node currentIteratedNode = first; 

        public boolean hasNext() { 
            return currentIteratedNode != null; 
        }
        
        public void remove() { 
            throw new UnsupportedOperationException();
        }
        
        public Item next() { 
            if ( !hasNext() )
                throw new NoSuchElementException();
            else {
                Item returnItem = currentIteratedNode.item;
                currentIteratedNode = currentIteratedNode.next;
                return returnItem; 
            }
    }
}

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> myDeque = new Deque<Integer>();
        myDeque.addFirst(12);
        myDeque.addFirst(9);
        myDeque.addLast(11);
        // System.out.println(myDeque);
        // System.out.println(myDeque.first);

        Iterator<Integer> myIterator = myDeque.iterator();
        while (myIterator.hasNext())
            System.out.println(myIterator.next());
    }

}
