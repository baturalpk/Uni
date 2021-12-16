//-----------------------------------------------------
// Title: IndexMinPQ class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class implements an index minimum priority queue.
//-----------------------------------------------------

package task_2;
import java.util.*;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
    	//--------------------------------------------------------
    	// Summary: Initializes the member variables with maximum size of maxN
    	// Precondition: maxN --> integer
    	// Postcondition: the instance variables are initialized
    	//--------------------------------------------------------
    	
        if (maxN < 0) throw new IllegalArgumentException();
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        //--------------------------------------------------------
        // Summary: Check emptiness of PQ
        // Precondition: -
        // Postcondition: returned whether it's empty or not as boolean
        //--------------------------------------------------------
    	
        return n == 0;
    }

    public boolean contains(int i) {
    	//--------------------------------------------------------
    	// Summary: check whether the PQ contains i or not
    	// Precondition: i --> integer
    	// Postcondition: returns a boolean for checking op.
    	//--------------------------------------------------------

        return qp[i] != -1;
    }

    public void insert(int i, Key key) {
        //--------------------------------------------------------
        // Summary: if the index's not in the pq, inserts a new elements and
        // runs swim operation.
        // Precondition: i --> integer, key --> Key
        // Postcondition: the new element's inserted
        //--------------------------------------------------------

        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int delMin() {
        //--------------------------------------------------------
        // Summary: deletes the minimum element then does sink & resize operations
        // if it's necessary
        // Precondition: -
        // Postcondition: minimum element is removed
        //--------------------------------------------------------
    	
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[n+1] = -1;
        return min;
    }

    public void decreaseKey(int i, Key key) {
    	//--------------------------------------------------------
    	// Summary: if the specified index is in the PQ, then tries to decrease
        // its key and runs swim operation
    	// Precondition: i --> integer, key --> Key
    	// Postcondition: the key at index i is decreased
    	//--------------------------------------------------------

        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) < 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
        keys[i] = key;
        swim(qp[i]);
    }

    private boolean greater(int i, int j) {
        //--------------------------------------------------------
        // Summary: compares two elements on the pq array to choose the greatest one
        // Precondition: i and j --> integer
        // Postcondition: returns a boolean as a result of the comparison
        //--------------------------------------------------------
    	
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        //--------------------------------------------------------
        // Summary: Swaps elements located at i & j each other
        // Precondition: i and j --> integer
        // Postcondition: swapped two pq keys
        //--------------------------------------------------------
    	
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
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

    public Iterator<Integer> iterator() {
        //--------------------------------------------------------
        // Summary: Public method to build a new heap iterator
        // Precondition: -
        // Postcondition: returns the iterator
        //--------------------------------------------------------
    	
    	return new HeapIterator(); 
    }

    private class HeapIterator implements Iterator<Integer> {
        //--------------------------------------------------------
        // Summary: An internal class to build a heap iterator using keys in the priority queue
        // Precondition: -
        // Postcondition: helps for creating a generic key iterator
        //--------------------------------------------------------
    	
        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
