//-----------------------------------------------------
// Title: Edge class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class defines an undirected edge with weight.
//-----------------------------------------------------


package shared;

public class Edge implements Comparable<Edge> { 

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
    	//--------------------------------------------------------
    	// Summary: Check validity of vertices and create an edge with weight.
    	// Precondition: v and w --> integers, weight --> double  
    	// Postcondition: v, w and weight are set
    	//--------------------------------------------------------
    	
        if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
    	//--------------------------------------------------------
    	// Summary: Getter for weight
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return weight;
    }

    public int either() {
    	//--------------------------------------------------------
    	// Summary: Getter for v
    	// Precondition: -
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        return v;
    }

    public int other(int vertex) {
    	//--------------------------------------------------------
    	// Summary: Getter for other than the specified vertex
    	// Precondition: vertex --> integer
    	// Postcondition: -
    	//--------------------------------------------------------
    	
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }
    
    public Edge switched() {
    	//--------------------------------------------------------
    	// Summary: Change locations of vertices v and w each other
    	// Precondition: -
    	// Postcondition: Returned a new edge with switched locations of v and w
    	//--------------------------------------------------------
    	
    	return new Edge(w, v, weight);
    }

    @Override
    public int compareTo(Edge that) {
    	//--------------------------------------------------------
    	// Summary: Compares weight of this edge by other edge
    	// Precondition: that --> Edge
    	// Postcondition: Returned comparison result as integer
    	//--------------------------------------------------------
    	
        return Double.compare(this.weight, that.weight);
    }

}
