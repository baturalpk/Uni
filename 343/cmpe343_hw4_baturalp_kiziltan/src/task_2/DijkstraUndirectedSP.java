//-----------------------------------------------------
// Title: DijkstraUndirectedSP class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class finds the shortest paths on an undirected graph.
//-----------------------------------------------------

package task_2;

import shared.Edge;
import shared.EdgeWeightedGraph;

import java.time.Instant;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;


public class DijkstraUndirectedSP {
    private double[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Double> pq;
    public final int SOURCE;
    public final long ELAPSED_TIME_TO_BUILD;

    public DijkstraUndirectedSP(EdgeWeightedGraph G, int s) {
    	//--------------------------------------------------------
    	// Summary: Initializes distTo array by filling positive infinity.
        // Then sets dist. to source 0 and starts edge relaxation from s by
        // iterating and adding these edges into priority queue. Additionally,
        // starts a timer to calculate build time performance of the algorithm.
    	// Precondition: G --> EdgeWeightedGraph, s --> integer
    	// Postcondition: new Dijkstra undirected shortest path object is constructed
    	//--------------------------------------------------------
    	
    	//-------- TIMER - start -----------
    	Instant _start_ = Instant.now();
    	//----------------------------------
    	
    	SOURCE = s;
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.adj(v))
                relax(e, v);
        }

    	//-------- TIMER - end -----------
    	Instant _end_ = Instant.now();
    	//----------------------------------
    	ELAPSED_TIME_TO_BUILD = Duration.between(_start_, _end_).toMillis();

    }

    private void relax(Edge e, int v) {
    	//--------------------------------------------------------
    	// Summary: Special internal method for Dijkstra's shortest path
        // relaxation operation. If the distance to w is greater than dist to
        // v + e's weight then the program tries to add/update w into PQ.
    	// Precondition: e --> Edge, v and w --> integer
    	// Postcondition: no change, or w's inserted into PQ, or w's decreased in PQ
    	//--------------------------------------------------------
    	
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public boolean hasPathTo(int v) {
    	//--------------------------------------------------------
    	// Summary: Checks whether is there a path to v or not from the source
    	// Precondition: v --> integer
    	// Postcondition: returns boolean
    	//--------------------------------------------------------
    	
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
    	//--------------------------------------------------------
    	// Summary: Adds edges to the vertex v by starting from the source if there
        // is a path.
    	// Precondition: v --> integer
    	// Postcondition: returns an iterable stack that contains the path
    	//--------------------------------------------------------
    	
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Deque<Edge> path = new ArrayDeque<>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

    private void validateVertex(int v) {
    	//--------------------------------------------------------
    	// Summary: Checks validity of the vertex v.
    	// Precondition: 
    	// Postcondition: throw exception or not
    	//--------------------------------------------------------
    	
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
