package task2;

import java.io.File;
import java.util.Scanner;

import task2.Maze.Cell;

public class Utils {
	
	// Minimum MAGnification(Zoom) level required to generate a proper Maze from the given file.
	private static final int MAG = 3;
	
	public static Maze ReadMazeFile(String filename) throws Exception {
		Scanner fileio = new Scanner(new File(filename));
		
		int w = fileio.nextInt();
		int h = fileio.nextInt();
		
		Maze maze = new Maze(MAG*h, MAG*w);

		for (int i = 0; i < h; ++i) {
			String line = fileio.next().trim();
			for (int j = 0; j < w; ++j) {
				String symbol = String.valueOf(line.charAt(j));

				switch(symbol.trim()) {
					case "\\": {
						for (int k = 0; k < MAG; ++k)
							maze.setWall(new Cell(MAG*i+k, MAG*j+k));
					} break;
					case "/": {
						for (int ki = 0, kj = MAG-1; ki < MAG; ++ki, --kj)
							maze.setWall(new Cell(MAG*i+ki, MAG*j+kj));
					} break;
				}
			}
		}

		fileio.close();
		return maze;
	}
	
	public static void SearchForCycles(Maze maze) {		
		int cycleCount = 0, maxLength = 0;
		
		for (int i = 0; i < maze.H(); ++i) {
			for (int j = 0; j < maze.W(); ++j) {
				Cell currentCell = new Cell(i, j);
				
				if (! maze.isVisited(currentCell) && ! maze.isBlocked(currentCell)) {
					maze.setVisited(currentCell);
					
					if (DepthFirstSearch.IsThereAnyCycle(maze, currentCell)) {
						++cycleCount;
						maxLength = Math.max(DepthFirstSearch.getStep(), maxLength);
					}
					
					DepthFirstSearch.resetStep();
				}
			}
		}
		
		if (cycleCount == 0) {
			System.out.println("There are no cycles.");
			return;
		}

		System.out.printf("%d Cycles; the longest has length %d.\n", cycleCount, maxLength/MAG);
	}
	
}
