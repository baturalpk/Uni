//-----------------------------------------------------
// Title: Depth First Order
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class contains postorder using DFS definition.
//-----------------------------------------------------

import java.util.*;

public class DepthFirstOrder {
    private boolean[] marked;
    private int[] post;
    private Queue<Integer> postorder;
    private int postCounter;

    public DepthFirstOrder(Digraph G) {
    	//--------------------------------------------------------
    	// Summary: Initialize member variables and run DFS algorithm to find
    	// postorder form.
    	// Precondition: G is directed graph
    	// Postcondition: postorder found
    	//--------------------------------------------------------
        post   = new int[G.V()];
        postorder = new LinkedList<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
    	//--------------------------------------------------------
    	// Summary: DFS algorithm implementation to mark postorder form.
    	// Precondition: G is directed graph, v is integer
    	// Postcondition: some vertices marked and added into postorder
    	//--------------------------------------------------------
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.offer(v);
        post[v] = postCounter++;
    }

    public Iterable<Integer> post() {
    	//--------------------------------------------------------
    	// Getter for postorder.
    	//--------------------------------------------------------
        return postorder;
    }
}
