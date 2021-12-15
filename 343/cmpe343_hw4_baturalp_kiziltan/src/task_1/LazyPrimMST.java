//-----------------------------------------------------
// Title: Lazy Prim MST class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class implements lazy version of Prim's
// minimum spanning tree finding algorithm.
//-----------------------------------------------------

package task_1;

import shared.Edge;
import shared.EdgeWeightedGraph;

import java.util.*;

public class LazyPrimMST {
    private double weight;
    private Queue<Edge> mst;
    private boolean[] marked;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
    	//--------------------------------------------------------
    	// Summary: Initializes member variables, then searches for MST.
    	// Precondition: G --> EdgeWeightedGraph
    	// Postcondition: MST is found.
    	//--------------------------------------------------------
    	
        mst = new LinkedList<>();
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) prim(G, v);

    }

    private void prim(EdgeWeightedGraph G, int s) {
    	//--------------------------------------------------------
    	// Summary: Prim's main algorithm for finding MST. Starts to scan
        // from the source vertex s until the priority queue is got empty.
    	// Precondition: G --> EdgeWeightedGraph, s --> integer
    	// Postcondition: some edges are marked as visited and/or inserted into mst.
    	//--------------------------------------------------------
    	
        scan(G, s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);

            if (marked[v] && marked[w]) continue;
            mst.offer(e);
            weight += e.weight();
            if (!marked[v]) scan(G, v);
            if (!marked[w]) scan(G, w);
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
    	//--------------------------------------------------------
    	// Summary: Prim's scanning algorithm. Inserts edges that
        // ensures specified conditions into minimum priority queue.
    	// Precondition: G --> EdgeWeightedGraph, v --> integer
    	// Postcondition: adjacent edges of v are inserted into PQ if not marked
    	//--------------------------------------------------------

        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
    	//--------------------------------------------------------
    	// Summary: Getter for mst
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return mst;
    }

    public double weight() {
    	//--------------------------------------------------------
    	// Summary: Getter for weight
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return weight;
    }

}
