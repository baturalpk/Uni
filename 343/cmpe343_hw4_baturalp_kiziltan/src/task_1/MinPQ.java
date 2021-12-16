//-----------------------------------------------------
// Title: MinPQ class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class implements a minimum priority queue.
//-----------------------------------------------------

package task_1;

import java.util.*;

public class MinPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;

    public MinPQ(int initCapacity) {
    	//--------------------------------------------------------
    	// Summary: Initializes member variables of MinPQ
    	// Precondition: initCapacity --> integer
    	// Postcondition: n is set 0, pq is initialized with initCapacity + 1
    	//--------------------------------------------------------
    	
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ() {
    	//--------------------------------------------------------
    	// Summary: No-parametered constructor that initializes PQ with
        // default size of 1.
    	// Precondition: -
    	// Postcondition: Constructed a new MinPQ
    	//--------------------------------------------------------
    	
        this(1);
    }

    public boolean isEmpty() {
    	//--------------------------------------------------------
    	// Summary: Check emptiness of PQ
    	// Precondition: -
    	// Postcondition: returned whether it's empty or not as boolean
    	//--------------------------------------------------------
    	
        return n == 0;
    }

    public int size() {
    	//--------------------------------------------------------
    	// Summary: Getter for n
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return n;
    }

    private void resize(int capacity) {
    	//--------------------------------------------------------
    	// Summary: Resizes PQ array with the given capacity
    	// Precondition: capacity --> integer
    	// Postcondition: pq array is extended or shrunk
    	//--------------------------------------------------------

        Key[] temp = (Key[]) new Object[capacity];
        if (n >= 0) System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    public void insert(Key x) {
    	//--------------------------------------------------------
    	// Summary: if necessary resizes pq array then inserts a new elements and
        // runs swim operation.
    	// Precondition: x --> Key
    	// Postcondition: new element's inserted
    	//--------------------------------------------------------
    	
        if (n == pq.length - 1) resize(2 * pq.length);

        pq[++n] = x;
        swim(n);
    }

    public Key delMin() {
    	//--------------------------------------------------------
    	// Summary: deletes the minimum element then does sink & resize operations
        // if it's necessary
    	// Precondition: -
    	// Postcondition: minimum element is removed
    	//--------------------------------------------------------
    	
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return min;
    }

    private void swim(int k) {
    	//--------------------------------------------------------
    	// Summary: Special internal method to balance heap tree in upper direction.
    	// Precondition: k --> integer
    	// Postcondition: the heap  is balanced
    	//--------------------------------------------------------
    	
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
    	//--------------------------------------------------------
    	// Summary: Special internal method to balance heap tree in lower direction.
    	// Precondition: k --> integer
    	// Postcondition: the heap is balanced
    	//--------------------------------------------------------
    	
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
    	//--------------------------------------------------------
    	// Summary: compares two elements on the pq array to choose the greatest one
    	// Precondition: i and j --> integer
    	// Postcondition: returns a boolean as a result of the comparison
    	//--------------------------------------------------------
    	
    	return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
    	//--------------------------------------------------------
    	// Summary: Swaps elements located at i & j each other
    	// Precondition: i and j --> integer
    	// Postcondition: swapped two pq keys
    	//--------------------------------------------------------
    	
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public Iterator<Key> iterator() {
    	//--------------------------------------------------------
    	// Summary: Public method to build a new heap iterator
    	// Precondition: -
    	// Postcondition: returns the iterator
    	//--------------------------------------------------------
    	
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
    	//--------------------------------------------------------
    	// Summary: An internal class to build a heap iterator using keys in the priority queue
    	// Precondition: -
    	// Postcondition: helps for creating a generic key iterator
    	//--------------------------------------------------------
    	
        private MinPQ<Key> copy;

        public HeapIterator() {
        	copy = new MinPQ<>(size());
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
