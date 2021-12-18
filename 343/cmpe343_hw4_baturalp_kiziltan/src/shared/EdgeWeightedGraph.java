//-----------------------------------------------------
// Title: EdgeWeightedGraph class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class defines an undirected edge weighted graph.
//-----------------------------------------------------

package shared;
import java.util.*;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private ArrayList<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
    	//--------------------------------------------------------
    	// Summary: Initializes member variables, constructs an empty graph.
    	// Precondition: V --> integer
    	// Postcondition: V is set to V, E is set to 0, adjacency list is initialized.
    	//--------------------------------------------------------
    	
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Edge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() {
    	//--------------------------------------------------------
    	// Summary: Getter for V
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return V;
    }

    public int E() {
        //--------------------------------------------------------
        // Summary: Getter for E
        // Precondition: -
        // Postcondition: -
        //--------------------------------------------------------

        return E;
    }

    private void validateVertex(int v) {
    	//--------------------------------------------------------
    	// Summary: Checks validity of vertex v
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(Edge e) {
    	//--------------------------------------------------------
    	// Summary: Checks validity of vertices. Then
        // connects specified vertices by adding an edge
    	// Precondition: e --> Edge
    	// Postcondition: the edge is inserted into adjacency
        // lists of both v and w, the number of edges is increased by 1
    	//--------------------------------------------------------
    	
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
    	//--------------------------------------------------------
    	// Summary: Checks validity of v and returns its adj list.
    	// Precondition: v --> integer
    	// Postcondition: returned adjacency list of v
    	//--------------------------------------------------------
    	
        validateVertex(v);
        return adj[v];
    }

    public Iterable<Edge> edges() {
    	//--------------------------------------------------------
    	// Summary: Brings all available edges that was inserted into the
        // graph by also checking for self loops.
    	// Precondition: -
    	// Postcondition: Returned all edges as an iterable
    	//--------------------------------------------------------
    	
        ArrayList<Edge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

}
