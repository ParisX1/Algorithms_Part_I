/*
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of the 
 * data structure. Create a generic data type Deque that implements the following API.
   https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */

// The other exceptions you should throw are:
// java.lang.IllegalArgumentException 
// java.lang.UnsupportedOperationException,

import java.util.Iterator;
import java.util.NoSuchElementException;

// public class Deque {
public class Deque<Item> implements Iterable<Item> {
    
    private Item[] deque;
    private int dequeCapacity;
    private int dequeObjectCount;
    private int dequeHead;
    private int dequeTail;

    // construct an empty deque
    public Deque(){
        dequeCapacity = 0;
        dequeObjectCount = 0;
        deque = (Item[]) new Object[dequeCapacity];

    }

    // is the deque empty?
    public boolean isEmpty() {
        return dequeObjectCount == 0;
    }

    // return the number of items on the deque
    public int size() {
        int i = 0;
        int count = 0;
        while (i < dequeCapacity){
            if (deque[i] != null)
                count++;
            i++;
        }
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (dequeHead != 0){
            deque[--dequeHead] = item;
            dequeObjectCount++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (dequeTail < dequeCapacity){ // WHAT IS THIS ACTUALLY DOING???
            deque[dequeTail] = item;
            dequeObjectCount++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        /*
        // if(!isEmpty()){
        // }
        Item tempItem = (Item) new Object();
        // tempItem = (Item) new Object();
        tempItem = deque[dequeHead];
        dequeHead++;
        dequeObjectCount--;
        return tempItem;
         */
        dequeObjectCount--;
        return deque[dequeHead++];
    }

    // remove and return the item from the back
    public Item removeLast() {
        dequeObjectCount--;
        return deque[dequeTail--];
        
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    // private class ListIterator implements Iterator<Item> {
    //     private Node current = first;
    //     public boolean hasNext() { return current != null; }
    //     public void remove() { /* not supported */ }
    //     public Item next() {
    //         Item item = current.item;
    //         current = current.next;
    //         return item;
    //     }
    // }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = dequeTail; 
    
        public boolean hasNext() { 
            return i > 0; 
        }
        
        public void remove() { 
            /* not supported */ 
            // Throw some error or message?
            throw new UnsupportedOperationException();
        }
        
        public Item next() { 
            if ( (dequeHead == 0) || (deque[dequeHead - 1] == null) )
                throw new NoSuchElementException();
            else 
                return deque[--i]; 
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}
