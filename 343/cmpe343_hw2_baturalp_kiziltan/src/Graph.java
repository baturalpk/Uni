import java.util.ArrayList;

public class Graph {
	
	 private final int V;
	    private int E;
	    private final ArrayList<Integer>[] adj;

	    public Graph(int V) {
	        if (V < 0) throw new IllegalArgumentException();
	        this.V = V;
	        this.E = 0;
	        adj = (ArrayList<Integer>[]) new ArrayList[V];
	        for (int v = 0; v < V; ++v) {
	            adj[v] = new ArrayList<>();
	        }
	    }

	    public void addEdge(int v, int w) {
	        validateVertex(v);
	        validateVertex(w);
	        adj[v].add(w);
	        adj[w].add(v);
	        ++E;
	    }

	    public int degree(int v) {
	        validateVertex(v);
	        return adj[v].size();
	    }

	    public Iterable<Integer> adj(int v) {
	        validateVertex(v);
	        return adj[v];
	    }

	    public int V() {
	        return this.V;
	    }

	    public int E() {
	        return this.E;
	    }

	    public void validateVertex(int v) {
	        if (v < 0 || v >= this.V) 
	        	throw new IllegalArgumentException();
	    }
	    
}
