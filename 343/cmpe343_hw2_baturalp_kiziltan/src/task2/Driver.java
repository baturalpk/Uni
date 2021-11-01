package task2;

import java.io.File;
import java.util.*;

public class Driver {
	
	public static void main(String[] args) {
		
		ArrayList<Stack<Integer>> cycles;

		Scanner sc = new Scanner(System.in);
		String filename = sc.nextLine().trim();
		sc.close();

		int w = 0;
		int h = 0;
		// Graph maze = null;
		Maze maze = null;

		try {
			Scanner fileio = new Scanner(new File(filename));

			w = fileio.nextInt();
			h = fileio.nextInt();
			// maze = new Graph(2*x*2*y);
			maze = new Maze(3*h, 3*w);

			for (int i = 0; i < h; ++i) {
				String line = fileio.next().trim();
				for (int j = 0; j < w; ++j) {
					String symbol = String.valueOf(line.charAt(j));

					switch(symbol.trim()) {
						case "/": {
							maze.setWall(3*i, 3*j+2);
							maze.setWall(3*i+1, 3*j+1);
							maze.setWall(3*i+2, 3*j);
							
//							int v0 = (2*x*(i+3)) + (2*j);
//							int v1 = (2*x*(i+2)) + (2*j+1);
//							int v2 = (2*x*(i+1)) + (2*j+2);
//							maze.addEdge(v0, v1);
//							maze.addEdge(v1, v2);
//							System.out.printf("/ => v0:%d v1:%d v2:%d\n", v0, v1, v2);
						} break;
						case "\\": {
							maze.setWall(3*i, 3*j);
							maze.setWall(3*i+1, 3*j+1);
							maze.setWall(3*i+2, 3*j+2);
							
//							int v0 = (2*x*(i+1)) + (2*j);
//							int v1 = (2*x*(i+2)) + (2*j+1);
//							int v2 = (2*x*(i+3)) + (2*j+2);
//							maze.addEdge(v0, v1);
//							maze.addEdge(v1, v2);
//							System.out.printf("\\ => v0:%d v1:%d v2:%d\n", v0, v1, v2);
						} break;
					}
				}
			}

			fileio.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		System.out.println(maze.toString());
		System.out.println(maze.H());
		System.out.println(maze.W());

		int cycCount = 0;
		int maxL = 0;

		for (int i = 0; i < maze.H(); ++i) {
			for (int j = 0; j < maze.W(); ++j) {
				if (! maze.isVisited(i, j) && ! maze.isBlocked(i, j)) {
					++cycCount;
					DepthFirstSearch.exe(maze, i, j);
					maxL = Math.max(DepthFirstSearch.count, maxL);
					DepthFirstSearch.count = 0;
				}
			}
		}

		System.out.printf("cyc count: %d - maxL: %d\n", cycCount, maxL);

		System.out.println(maze.toString());
//		System.out.println(maze.toString());
//		Cycle c = new Cycle(maze);
//		cycles = c.allCycles;
//		
//		System.out.println("Found " + cycles.size());
//		
//		int maxL = -1;
//		for (Stack<Integer> cycle: cycles) {
//			if (cycle.size() > maxL) maxL = cycle.size();
//		}
//		
//		System.out.println("Max Length " + maxL);
	}

}
