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
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        if (n >= 0) System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    public void insert(Key x) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        if (n == pq.length - 1) resize(2 * pq.length);

        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    public Key delMin() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void swim(int k) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
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
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
    	return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
    	//--------------------------------------------------------
    	// Summary: Swaps i and j each other
    	// Precondition: i and j --> integer
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    public Iterator<Key> iterator() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
        private MinPQ<Key> copy;

        public HeapIterator() {
        	copy = new MinPQ<Key>(size());
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
