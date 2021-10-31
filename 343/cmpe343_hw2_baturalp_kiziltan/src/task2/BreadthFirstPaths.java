package task2;
import java.util.*;

public class BreadthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		validateVertex(s);
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> fifo = new LinkedList<>();

		for (int v = 0; v < G.V(); ++v) {
			distTo[v] = Integer.MAX_VALUE; // the case if there is no such a path for.
		}

		distTo[s] = 0;
		marked[s] = true;
		fifo.add(s);

		while (!fifo.isEmpty()) {
			int v = fifo.remove();
			for (int w: G.adj(v)) {				
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					fifo.add(w);
				}
			}
		}
	}

	public int distTo(int v) {
		validateVertex(v);
		return this.distTo[v];
	}

	public boolean checkAnyPathTo(int v) {
		return this.distTo[v] != Integer.MAX_VALUE;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= this.marked.length)
			throw new IllegalArgumentException("vertex is out of constraints");
	}

}
