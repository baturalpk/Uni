
public class Utils {

	public static Graph[] initializeTheSafe() {
		Graph wheel1 = createWheelWithInitialState(10);
		Graph wheel2 = createWheelWithInitialState(10);
		Graph wheel3 = createWheelWithInitialState(10);
		Graph wheel4 = createWheelWithInitialState(10);
		
		return new Graph[]{
				wheel1,
				wheel2,
				wheel3,
				wheel4
		};
	}

	private static Graph createWheelWithInitialState(int size) {
		Graph wheel = new Graph(size);

		for (int j = 0; j < 10; ++j) {
			int v = j;
			int w = (j+1) % 10;
			wheel.addEdge(v, w);
		}

		return wheel;
	}

	public static boolean isItPossibleToOpen(Graph[] safe, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
		for (int i = 0; i < 4; ++i) {
			removeForbiddenNumbers(safe, i, sources, targets, forbiddens, forbiddenCount);
			BreadthFirstPaths path = new BreadthFirstPaths(safe[i], sources[i]);
			safe[i] = createWheelWithInitialState(10);
			
			if (! path.checkAnyPathTo(targets[i])) continue;
			else return true;
		}

		return false;
	}

	public static BreadthFirstPaths[] findShortestWaysToUnlock(Graph[] safe, int[] sources) {
		return new BreadthFirstPaths[] {
				new BreadthFirstPaths(safe[0], sources[0]),
				new BreadthFirstPaths(safe[1], sources[1]),
				new BreadthFirstPaths(safe[2], sources[2]),
				new BreadthFirstPaths(safe[3], sources[3])
		};
	}

	public static int calcMinNumberOfButtonPresses(BreadthFirstPaths[] paths, int[] targets) {
		int buttonPresses = 0;

		for (int i = 0; i < 4; ++i) {
			if (paths[i].checkAnyPathTo(targets[i])) 
				buttonPresses += paths[i].distTo(targets[i]);
			else return -1;
		}

		return buttonPresses;
	}

	private static void removeForbiddenNumbers(Graph[] safe, int wheelNumber, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
		for (int i = 0; i < forbiddenCount; ++i) {
			int[] number = forbiddens[i];

			if ( (number[wheelNumber] != sources[wheelNumber]) /*&& 
					(number[wheelNumber] != targets[wheelNumber]) */) {
				
				cutConnectionBetweenForbiddenEdges(safe[wheelNumber], number[wheelNumber]);
			}
		}
	}

//	public static void removeAllForbiddenNumbers(Graph[] safe, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
//		for (int i = 0; i < forbiddenCount; ++i) {
//			int[] number = forbiddens[i];
//
//			for (int j = 0; j < 4; ++j) {
//				if ((number[j] != sources[j]) || (number[j] != targets[j])) {
//					cutConnectionBetweenForbiddenEdges(safe[j], number[j]);
//				}
//			}
//		}
//	}

	private static void cutConnectionBetweenForbiddenEdges(Graph G, int v) {
		int w0 = (v-1) % 10;
		int w1 = (v+1) % 10;

		if (w0 < 0) w0 += 10;
		if (w1 < 0) w1 += 10;

		G.removeEdge(v, w0);
		G.removeEdge(v, w1);
	}

	public static int[] parseLineToInt4Array(String line) throws Exception {
		int[] result = new int[4];

		int i = 0;
		for (String rawInt: line.trim().split(" ")) {
			if (i >= 4) throw new IllegalArgumentException("Number of input numbers exceeds expected value");

			result[i] = Integer.parseInt(rawInt);
			++i;
		}

		return result;
	}

}
