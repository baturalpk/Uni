import java.io.File;
import java.util.*;

public class Driver_Task1 {

	public static void main(String[] args) {
		//		Graph[] theSafe = Utils.initializeTheSafe();
		//
		//		int[] sources = null;
		//		int[] targets = null;
		//		int[][] forbiddens = null;
		//		int numberOfForbiddens = 0;

		Graph[] theSafe = Utils.initializeTheSafe();

		int[] source = null;
		int[] target = null;
		int numberOfForbiddens = 0;
		ArrayList<Integer> marked = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		sc.close();
		try {
			Scanner fileio = new Scanner(new File(fileName.trim()));
			source = Utils.parseLineToInt4Array(fileio.nextLine());
			// marked.add(Utils.ParseDigitsToInt(source));

			target = Utils.parseLineToInt4Array(fileio.nextLine());
			numberOfForbiddens = Integer.parseInt(fileio.nextLine().trim());

			for (int i = 0; i < numberOfForbiddens; ++i) {
				marked.add(Utils.ParseDigitsToInt(Utils.parseLineToInt4Array(fileio.nextLine())));
			}

			fileio.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		int result = Utils.BreadthFirstSearch(Utils.ParseDigitsToInt(source), Utils.ParseDigitsToInt(target), marked);
		System.out.println(result);

		//		Utils.removeAllForbiddenNumbers(theSafe, sources, forbiddens, numberOfForbiddens);
		//
		//		for (Graph wheel: theSafe) {
		//			System.out.println(wheel.toString());
		//		}

		//		if (! Utils.isItPossibleToOpen(theSafe, sources, targets, forbiddens, numberOfForbiddens)) {
		//			System.out.println(-1);
		//			return;
		//		}
		//
		//		BreadthFirstPaths[] shortestWays = Utils.findShortestWaysToUnlock(theSafe, sources);
		//		int totalButtonPressesRequired = Utils.calcMinNumberOfButtonPresses(shortestWays, targets);
		//
		//		System.out.println(totalButtonPressesRequired);
	}
}
