/*
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of the 
 * data structure. Create a generic data type Deque that implements the following API.
   https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first; // First node in the linked-list
    private Node last;  // Last node in the linked-list
    private int dequeSize;
    

    // construct an empty deque
    public Deque() {
        dequeSize = 0;
        first = null;
        last = null;
    }

    private class Node {
        Item item; 
        Node next;      // The next node in the linked-list (that this node links to)
        Node previous;  // The node before in the linked-list (that is node links from)
        
        // construct a Node
        public Node() {
            item = null;
            next = null;
            previous = null;
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
            oldFirst.previous = first;
        }
        dequeSize++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty())  
            first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }            
        dequeSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = first.item; // Item to return
        first = first.next;
        first.previous = null;
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

    private Item getFirstItem(){
        return first.item;
    }

    private Item getLastItem(){
        return last.item;
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
        
        Deque<Integer> myDeque0 = new Deque<Integer>();
        myDeque0.addFirst(12);
        myDeque0.removeLast();
        System.out.println(myDeque0.getFirstItem());

        /*
        // First Test
        Deque<Integer> myDeque1 = new Deque<Integer>();
        myDeque1.addFirst(12);
        myDeque1.addFirst(9);
        myDeque1.addLast(11);
        assert myDeque1.getFirstItem() == 9;
        assert myDeque1.getLastItem() == 11;

        // Second Test
        Deque<Integer> myDeque2 = new Deque<Integer>();
        myDeque2.addFirst(12);
        myDeque2.removeLast();
        assert myDeque2.getFirstItem() == null;
        assert myDeque2 == null;
        assert myDeque2.getLastItem() == null;

        // Test: Printing with Iterator
        //Deque<Integer> myDeque = new Deque<Integer>();
        Iterator<Integer> myIterator = myDeque1.iterator();
        while (myIterator.hasNext())
            System.out.println(myIterator.next());
         */
    }
}
