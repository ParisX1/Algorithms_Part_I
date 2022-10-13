/*
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of the 
 * data structure. Create a generic data type Deque that implements the following API.
   https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */

// The other exceptions you should throw are:
// java.lang.IllegalArgumentException 
// java.lang.UnsupportedOperationException,

/*

import java.util.Iterator;
import java.util.NoSuchElementException;



// public class Deque {
public class Deque<Item> implements Iterable<Item> {
    
    private Item[] deque;
    private final int INIT_CAPACITY = 8;
    private int dequeCapacity;
    private int dequeObjectCount;
    private int dequeHead;
    private int dequeTail;

    // construct an empty deque
    public Deque(){
        dequeCapacity = INIT_CAPACITY;
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
        
        if (item == null)
            throw new IllegalArgumentException();
        
        if (dequeHead != 0){
            deque[--dequeHead] = item;
            dequeObjectCount++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null)
            throw new IllegalArgumentException();

        if (dequeTail < dequeCapacity){ // WHAT IS THIS ACTUALLY DOING???
            deque[dequeTail] = item;
            dequeObjectCount++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        
        if (isEmpty())
            throw new NoSuchElementException();

        Item returnItem = (Item) new Object();
        returnItem = deque[dequeHead];
        deque[dequeHead] = null;
        dequeObjectCount--;
        dequeHead++;

        checkForResize();

        return returnItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        
        if (isEmpty())
            throw new NoSuchElementException();

        Item returnItem = (Item) new Object();
        returnItem = deque[dequeTail];
        deque[dequeTail] = null;
        dequeObjectCount--;
        dequeTail--;
        return returnItem;
    }

    private void checkForResize(){
        
        // Resize if array too large
        if (dequeObjectCount == (dequeCapacity / 4)){
            resizeDeque(dequeCapacity / 2);
        }
    }

    private void resizeDeque(int newCapacity){
        Item[] dequeCopy = (Item[]) new Object[newCapacity];
        int j = 0;
        for (int i = 0; i < dequeCapacity; i++){
            if (deque[i] != null){
                dequeCopy[j] = deque[i];
                j++;
            }
        }
        deque = dequeCopy;
        dequeCapacity = newCapacity;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = dequeTail; 
    
        public boolean hasNext() { 
            return i > 0; 
        }
        
        public void remove() { 
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
        System.out.println("Hello world");
        
    }

}
*/