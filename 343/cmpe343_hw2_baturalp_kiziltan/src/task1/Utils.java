package task1;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Utils {
	

	private static class Number {
		// This class describes a number with its integer value alongside the distance/step value of
		// the operation. The operation defines a breadth search algorithm, and this class will be
		// used as a helper. Because tracing of the current step of operation (distance) value is
		// not likely to be possible, while it's stored in the global state or the outer loop.
		
		int val;
		int dist;
		
		Number(int val, int dist) {
			this.val = val; 
			this.dist = dist;
		}
	}
	
	public static int BreadthFirstSearch(int src, int tar, ArrayList<Integer> marked) {
		// highest natural number that is not in the interval [0, 1, 2..., 9], 10
		final int UPPER_BOUNDARY = 10; // essential for wrap-around cases to evaluate modulus operations
										
		// maximum number of digits (wheel count)
		final int DIGIT_COUNT = 4;			
		
		Queue<Number> fifo = new LinkedList<>(); // FIFO (First In First Out) queue for breadth first search implementation 
		int distance = 0; // describes current step of operation (minimum required trials to open the safe in our case)

		fifo.add(new Number(src, distance)); // add source number to 

		while (fifo.size() > 0) {
			Number num = fifo.remove();
			distance = num.dist;

			if (num.val == tar) return distance; 	// target found

			if (marked.contains(num.val)) continue;	// already visited

			marked.add(num.val); // set visited
			++distance; // increase the current number of steps
			
			int[] digits = Utils.ParseIntTo4Digits(num.val);
			ArrayList<Number> adjacentClockwise = new ArrayList<>();
			ArrayList<Number> adjacentNegative = new ArrayList<>();
					
			for (int i = 0; i < DIGIT_COUNT; ++i) {
				int[] temp_0 = new int[DIGIT_COUNT];
				int[] temp_1 = new int[DIGIT_COUNT];
				System.arraycopy(digits, 0, temp_0, 0, DIGIT_COUNT);
				System.arraycopy(digits, 0, temp_1, 0, DIGIT_COUNT);

				temp_0[i] = (temp_0[i] - 1) % UPPER_BOUNDARY;
				if (temp_0[i] < 0) temp_0[i] += UPPER_BOUNDARY;
				
				temp_1[i] = (temp_1[i] + 1) % UPPER_BOUNDARY;
				if (temp_1[i] < 0) temp_1[i] += UPPER_BOUNDARY;
				
				adjacentNegative.add(new Number(Utils.Parse4DigitsToInt(temp_0), distance));
				adjacentClockwise.add(new Number(Utils.Parse4DigitsToInt(temp_1), distance));
			}
			
			fifo.addAll(adjacentClockwise);
			fifo.addAll(adjacentNegative);
		}

		return -1;
	}

	public static int Parse4DigitsToInt(int[] digits) {
		return ( digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3] );
	}

	public static int[] ParseIntTo4Digits(int num) {
		int[] digits = new int[4];

		for (int i = 3; i >=0; --i) {
			digits[i] = num % 10;
			num = num / 10;
		}

		return digits;
	}
	
	public static int[] ParseStringTo4Digits(String str) throws Exception {
		int[] result = new int[4];

		int i = 0;
		for (String rawInt: str.trim().split(" ")) {
			if (i >= 4) throw new IllegalArgumentException("Number of input numbers exceeds expected value");

			result[i] = Integer.parseInt(rawInt);
			++i;
		}

		return result;
	}
	
//
//	public static Graph[] initializeTheSafe() {
//		Graph wheel1 = createWheelWithInitialState(10);
//		Graph wheel2 = createWheelWithInitialState(10);
//		Graph wheel3 = createWheelWithInitialState(10);
//		Graph wheel4 = createWheelWithInitialState(10);
//
//		return new Graph[]{
//				wheel1,
//				wheel2,
//				wheel3,
//				wheel4
//		};
//	}
//
//	private static Graph createWheelWithInitialState(int size) {
//		Graph wheel = new Graph(size);
//
//		for (int j = 0; j < 10; ++j) {
//			int v = j;
//			int w = (j+1) % 10;
//			wheel.addEdge(v, w);
//		}
//
//		return wheel;
//	}
//
//	public static boolean isItPossibleToOpen(Graph[] safe, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
//		for (int i = 0; i < 4; ++i) {
//			removeForbiddenNumbers(safe, i, sources, targets, forbiddens, forbiddenCount);
//			BreadthFirstPaths path = new BreadthFirstPaths(safe[i], sources[i]);
//			safe[i] = createWheelWithInitialState(10);
//
//			if (! path.checkAnyPathTo(targets[i])) continue;
//			else return true;
//		}
//
//		return false;
//	}
//
//	public static BreadthFirstPaths[] findShortestWaysToUnlock(Graph[] safe, int[] sources) {
//		return new BreadthFirstPaths[] {
//				new BreadthFirstPaths(safe[0], sources[0]),
//				new BreadthFirstPaths(safe[1], sources[1]),
//				new BreadthFirstPaths(safe[2], sources[2]),
//				new BreadthFirstPaths(safe[3], sources[3])
//		};
//	}
//
//	public static int calcMinNumberOfButtonPresses(BreadthFirstPaths[] paths, int[] targets) {
//		int buttonPresses = 0;
//
//		for (int i = 0; i < 4; ++i) {
//			if (paths[i].checkAnyPathTo(targets[i])) 
//				buttonPresses += paths[i].distTo(targets[i]);
//			else return -1;
//		}
//
//		return buttonPresses;
//	}
//
//	private static void removeForbiddenNumbers(Graph[] safe, int wheelNumber, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
//		for (int i = 0; i < forbiddenCount; ++i) {
//			int[] number = forbiddens[i];
//
//			if ( (number[wheelNumber] != sources[wheelNumber]) /*&& 
//					(number[wheelNumber] != targets[wheelNumber]) */) {
//
//				cutConnectionBetweenForbiddenEdges(safe[wheelNumber], number[wheelNumber]);
//			}
//		}
//	}
//
//		public static void removeAllForbiddenNumbers(Graph[] safe, int[] sources, int[] targets, int[][] forbiddens, int forbiddenCount) {
//			for (int i = 0; i < forbiddenCount; ++i) {
//				int[] number = forbiddens[i];
//	
//				for (int j = 0; j < 4; ++j) {
//					if ((number[j] != sources[j]) || (number[j] != targets[j])) {
//						cutConnectionBetweenForbiddenEdges(safe[j], number[j]);
//					}
//				}
//			}
//		}
//
//	private static void cutConnectionBetweenForbiddenEdges(Graph G, int v) {
//		int w0 = (v-1) % 10;
//		int w1 = (v+1) % 10;
//
//		if (w0 < 0) w0 += 10;
//		if (w1 < 0) w1 += 10;
//
//		G.removeEdge(v, w0);
//		G.removeEdge(v, w1);
//	}

}
