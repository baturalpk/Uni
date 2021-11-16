
import java.util.*;

public class DirectedCycle {
	
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;

	public DirectedCycle(Digraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v] && cycle == null)
				dfs(G, v);
	}

	// run DFS and find a directed cycle (if one exists)
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.adj(v)) {

			// short circuit if directed cycle found
			if (cycle != null)
				return;

			// found new vertex, so recur
			else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}

			// trace back directed cycle
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
		return cycle != null;
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

}
