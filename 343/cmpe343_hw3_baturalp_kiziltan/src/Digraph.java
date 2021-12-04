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
    	// Summary: Initialize digraph instance with the given size.
    	// Precondition: V is integer.
    	// Postcondition: member variables are initialized
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
    	// Getter for V
    	//--------------------------------------------------------
        return V;
    }

    public int E() {
    	//--------------------------------------------------------
    	// Getter for E 
    	//--------------------------------------------------------
        return E;
    }

    public void addEdge(int v, int w) {
    	//--------------------------------------------------------
    	// Summary: adds w into adjacency list of v
    	// Precondition: v, w are integers.
    	// Postcondition: w is added into adjacency list of v
    	//--------------------------------------------------------
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
    	//--------------------------------------------------------
    	// Summary: retrieves adjacents of the desired vertex.
    	// Precondition: v is integer.
    	// Postcondition: returns adjacency list of v
    	//--------------------------------------------------------
        return adj[v];
    }

    public int outdegree(int v) {
    	//--------------------------------------------------------
    	// Summary: brings number of adjacent vertices to v
    	// Precondition: v is integer
    	// Postcondition: returns size of adjaceny list 
    	//--------------------------------------------------------
        return adj[v].size();
    }
    
    @Override
    public String toString() {
    	//--------------------------------------------------------
    	// Summary: Builds a string that contains vertex ids and their adjacency list view
    	// Precondition: -
    	// Postcondition: Return string representation of the digraph
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
