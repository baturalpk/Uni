import java.io.File;
import java.util.Scanner;

public class Driver_Task1 {

	public static void main(String[] args) {
		Graph[] theSafe = Utils.initializeTheSafe();

		int[] sources = null;
		int[] targets = null;
		int[][] forbiddens = null;
		int numberOfForbiddens = 0;


		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		sc.close();
		try {
			Scanner fileio = new Scanner(new File(fileName.trim()));
			sources = Utils.parseLineToInt4Array(fileio.nextLine());
			targets = Utils.parseLineToInt4Array(fileio.nextLine());

			numberOfForbiddens = Integer.parseInt(fileio.nextLine().trim());
			forbiddens = new int[numberOfForbiddens][];

			for (int i = 0; i < numberOfForbiddens; ++i) {
				forbiddens[i] = Utils.parseLineToInt4Array(fileio.nextLine());
			}

			fileio.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

//		Utils.removeAllForbiddenNumbers(theSafe, sources, forbiddens, numberOfForbiddens);
//
//		for (Graph wheel: theSafe) {
//			System.out.println(wheel.toString());
//		}
		
		if (! Utils.isItPossibleToOpen(theSafe, sources, targets, forbiddens, numberOfForbiddens)) {
			System.out.println(-1);
			return;
		}

		BreadthFirstPaths[] shortestWays = Utils.findShortestWaysToUnlock(theSafe, sources);
		int totalButtonPressesRequired = Utils.calcMinNumberOfButtonPresses(shortestWays, targets);

		System.out.println(totalButtonPressesRequired);
	}
}
