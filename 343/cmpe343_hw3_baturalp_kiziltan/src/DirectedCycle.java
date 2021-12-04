//-----------------------------------------------------
// Title: Directed Cycle
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class defines directed cycle detection utilities.
//-----------------------------------------------------

import java.util.*;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            if (cycle != null) return;

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
        return cycle != null;
    }

}
