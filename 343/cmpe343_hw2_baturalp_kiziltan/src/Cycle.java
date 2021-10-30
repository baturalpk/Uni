import java.util.Stack;

public class Cycle {

	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	public Cycle(Graph G) {
		if (hasParallelEdges(G)) return;
	}

	private boolean hasParallelEdges(Graph G) {
		marked = new boolean[G.V()];

		for (int v = 0; v < G.V(); ++v) {
			for (int w: G.adj(v)) {
				if (marked[w]) {
					cycle = new Stack<>();
					cycle.push(v);
					cycle.push(w);
					cycle.push(v);
					return true;
				}

				marked[w] = true;
			}

			for (int w: G.adj(v)) {
				marked[w] = false;
			}
		}
		
		return false;
	}

}
