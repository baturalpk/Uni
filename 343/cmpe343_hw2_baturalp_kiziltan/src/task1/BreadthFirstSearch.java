// 
package task1;

import java.util.*;

import task1.Utils.Number;

public class BreadthFirstSearch {
	
	// highest natural number that is not in the interval [0, 1, 2..., 9], 10
	private static final int UPPER_BOUNDARY = 10; // essential for wrap-around cases to evaluate modulus operations
									
	// maximum number of digits (wheel count)
	private static final int DIGIT_COUNT = 4;	
	
	public static int DecryptTheSafe(int source, int target, ArrayList<Integer> marked) {
		return BFS(source, target, marked);
	}

	private static int BFS(int src, int tar, ArrayList<Integer> marked) {
		
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
}
