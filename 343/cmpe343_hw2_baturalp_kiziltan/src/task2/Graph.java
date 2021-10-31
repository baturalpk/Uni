package task2;
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

	public void removeEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		
		if (adj[w].contains(v)) adj[w].remove(adj[w].indexOf(v));
		if (adj[v].contains(w)) adj[v].remove(adj[v].indexOf(w));
		--E;
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

	private void validateVertex(int v) {
		if (v < 0 || v >= this.V) 
			throw new IllegalArgumentException("vertex is out of constraints: " + v);
	}

	@Override
	public String toString() {
		String prettyPrint = "";

		for (int v = 0; v < this.V; ++v) {
			prettyPrint += (v + "=> ");
			for (Integer w: this.adj(v)) {
				prettyPrint += (w + ", ");
			}
			prettyPrint += "\n";
		}

		return prettyPrint;
	}

}
