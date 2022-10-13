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

        Item item = first.item; // Temp Item to return
        first = first.next;
        first.previous = null;
        dequeSize--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.item; // Temp Item to return

        if (last.previous == null){ // If only one item in list, reset to empty list
            last = null;
            first = null;
        }
        else {
            last = last.previous;
            last.next = null;
        }

        //if (isEmpty()) last = null;
        
        dequeSize--;
        return item;
    }

    private Item getFirstItem(){
        return first.item;
    }

    private Item getLastItem(){
        return last.item;
    }

    private Node getFirst(){
        return first;
    }

    private Node getLast(){
        return last;
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
        
        String testPassMessage = new String();

        // Test 1
        // Test for adding and removing to create empty list
        System.out.print("Test 1: ");
        Deque<Integer> myDeque1 = new Deque<Integer>();
        myDeque1.addFirst(12);
        myDeque1.removeLast();
        if (myDeque1.getFirst() == null)
            testPassMessage = "Pass";
        else testPassMessage = "Fail";
        System.out.println(testPassMessage);

        
        // Test 2
        // Add to front and back, check order is correct and size is correct
        System.out.print("Test 2: ");
        Deque<Integer> myDeque2 = new Deque<Integer>();
        myDeque2.addFirst(12);
        myDeque2.addFirst(9);
        myDeque2.addLast(11);
        testPassMessage = "Pass";
        if( myDeque2.getFirstItem() != 9) testPassMessage = "Fail";
        if( myDeque2.getLastItem() != 11) testPassMessage = "Fail";
        if( myDeque2.size() != 3) testPassMessage = "Fail";
        System.out.println(testPassMessage);

        
        // Test 3
        System.out.print("Test 3: ");
        Deque<Integer> myDeque3 = new Deque<Integer>();
        myDeque3.addFirst(1);
        myDeque3.addFirst(2);
        myDeque3.addLast(3);
        myDeque3.addFirst(4);
        myDeque3.removeLast();
        myDeque3.removeFirst();
        myDeque3.addFirst(5);
        myDeque3.addLast(6);
        myDeque3.addFirst(7);
        myDeque3.removeLast();
        testPassMessage = "Pass";
        if( myDeque3.getFirstItem() != 7) testPassMessage = "Fail";
        if( myDeque3.getLastItem() != 1) testPassMessage = "Fail";
        if( myDeque3.size() != 4) testPassMessage = "Fail";
        System.out.println(testPassMessage);
        
        // Test 4: Printing with Iterator
        System.out.println("Test 4: ");
        System.out.print("Actual: \t");
        Iterator<Integer> myIterator = myDeque3.iterator();
        while (myIterator.hasNext())
            System.out.print(myIterator.next() + " ");
            System.out.println("\nExpected: \t7 5 2 1");
    }
}
