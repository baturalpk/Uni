//-----------------------------------------------------
// Title: Digraph
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class defines a directed graph.
//-----------------------------------------------------

import java.util.*;

public class Digraph {
	
    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;
    
    public Digraph(int V) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
    }
        
    public int V() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        return V;
    }

    public int E() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        return E;
    }

    private void validateVertex(int v) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        validateVertex(v);
        return adj[v].size();
    }
    
    @Override
    public String toString() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        StringBuilder s = new StringBuilder();
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append("\n");
        }
        return s.toString();
    }

}
