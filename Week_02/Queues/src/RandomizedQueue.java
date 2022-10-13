/*
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the 
 * item removed is chosen uniformly at random among items in the data structure. Create 
 * a generic data type RandomizedQueue that implements the following API.
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

// public class RandomizedQueue {
 public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQueue;
    private final int INIT_CAPACITY = 8;
    private int queueCapacity;
    private int queueObjectCount;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queueCapacity = INIT_CAPACITY;
        queueObjectCount = 0;
        randomQueue = (Item[]) new Object[queueCapacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return queueObjectCount == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        /*
        int i = 0;
        int count = 0;
        while (i < queueCapacity){
            if (randomQueue[i] != null)
                count++;
            i++;
        }
        */
        return queueObjectCount;
    }

    private int capacity(){
        return queueCapacity;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)   throw new IllegalArgumentException();
        randomQueue[queueObjectCount] = item;
        queueObjectCount++;
        checkForResize();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())  throw new NoSuchElementException();

        int indexToReturn = getRandomIndex(queueObjectCount);
        Item returnItem = (Item) new Object(); // Temp item to return

        // Get return item & swap with last element
        returnItem = randomQueue[indexToReturn];
        randomQueue[indexToReturn] = randomQueue[queueObjectCount-1];
        randomQueue[queueObjectCount-1] = null;
        
        queueObjectCount--;
        checkForResize();
        return returnItem;
    }

    private int getRandomIndex(int rangeMax){
        return StdRandom.uniformInt(rangeMax);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())  throw new NoSuchElementException();
        
        int indexToReturn = getRandomIndex(queueObjectCount);
        Item returnItem = (Item) new Object(); // Temp item to return
        returnItem = randomQueue[indexToReturn];
        return returnItem;
    }

    private void checkForResize(){
        if (queueObjectCount == (queueCapacity / 4))    resizeDeque(queueCapacity / 2);
        else if (queueObjectCount == queueCapacity)     resizeDeque(queueCapacity * 2);
    }

    private void resizeDeque(int newCapacity){
        Item[] dequeCopy = (Item[]) new Object[newCapacity];
        int j = 0;
        for (int i = 0; i < queueCapacity; i++){
            if (randomQueue[i] != null){
                dequeCopy[j] = randomQueue[i];
                j++;
            }
        }
        randomQueue = dequeCopy;
        queueCapacity = newCapacity;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();   
    }

    private class RandomArrayIterator implements Iterator<Item> {
        
        private int randomIndicies[];   // Hold unique random index positions
        private int indexPosition;      // Track position in randomIndicies
        private int indexValue;         // Value at randomIndicies[i]

        RandomArrayIterator(){
            indexPosition = 0;
            indexValue = 0;
            randomIndicies = new int[queueObjectCount];
            for (int i = 0; i < queueObjectCount; i++)
                randomIndicies[i] = i;
            StdRandom.shuffle(randomIndicies);
        }
    
        public boolean hasNext() { 
            return indexPosition < queueObjectCount; 
        }
        
        public void remove() { 
            throw new UnsupportedOperationException();
        }
        
        public Item next() { 
            if (!hasNext()) throw new NoSuchElementException();
            indexValue = randomIndicies[indexPosition];
            indexPosition++;
            return randomQueue[indexValue];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> myRandomQueue1 = new RandomizedQueue<Integer>();
        myRandomQueue1.enqueue(1);
        myRandomQueue1.enqueue(2);
        myRandomQueue1.enqueue(3);
        myRandomQueue1.enqueue(4);

        // Testing Iterator
        System.out.println("Testing Iterator:");
        Iterator<Integer> myIterator = myRandomQueue1.iterator();
        System.out.println(myIterator.next());
        System.out.println(myIterator.next());
        System.out.println(myIterator.next());
        System.out.println(myIterator.next());

        // Testing Random Sampling
        System.out.println("\nTesting Random Sample:");
        System.out.println(myRandomQueue1.sample());
        System.out.println(myRandomQueue1.sample());
        System.out.println(myRandomQueue1.sample());
        System.out.println(myRandomQueue1.sample());


        // Testing Random Dequeue
        System.out.println("\nTesting Random Dequeue:");
        System.out.println("~Dequeue 1~");
        System.out.println("Item Value:\t" + myRandomQueue1.dequeue());
        System.out.println("Queue Size:\t" + myRandomQueue1.size());
        System.out.println("Queue Capacity:\t" + myRandomQueue1.capacity());

        System.out.println("\n~Dequeue 2~");
        System.out.println("Item Value:\t" + myRandomQueue1.dequeue());
        System.out.println("Queue Size:\t" + myRandomQueue1.size());
        System.out.println("Queue Capacity:\t" + myRandomQueue1.capacity());

        System.out.println("\n~Dequeue 3~");
        System.out.println("Item Value:\t" + myRandomQueue1.dequeue());
        System.out.println("Queue Size:\t" + myRandomQueue1.size());
        System.out.println("Queue Capacity:\t" + myRandomQueue1.capacity());

        System.out.println("\n~Dequeue 4~");
        System.out.println("Item Value:\t" + myRandomQueue1.dequeue());
        System.out.println("Queue Size:\t" + myRandomQueue1.size());
        System.out.println("Queue Capacity:\t" + myRandomQueue1.capacity());
    }
}

/* TO DO
 * DONE dequeue and sample at the moment are not random
 * Check queue can be resized - smaller and larger
 * New queue.  Add items.  Remove to zero and then re-add
 * When resizing, does the iterator still work
 * Test deque vs sample
 * Maybe test with strings - this is what the tester does (Test premutation with this..)
 */