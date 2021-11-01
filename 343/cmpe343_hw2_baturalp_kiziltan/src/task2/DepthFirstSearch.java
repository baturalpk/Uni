package task2;

import java.util.*;

import task2.Maze.Cell;

public class DepthFirstSearch {

    private static int step = 0;
    
    public static boolean IsThereAnyCycle(Maze m, Maze.Cell src) {
    	return DFS(m, src);
    }
    
	private static boolean DFS(Maze maze, Maze.Cell source) {
		boolean isCyclic = true;
		Stack<Maze.Cell> lifo = new Stack<>();
		
		lifo.push(source);
		
		while (! lifo.empty()) {
			Cell cell = lifo.pop();
			++step;
			
			Cell[] routes = new Cell[] {
					new Cell(cell.y, cell.x+1),
					new Cell(cell.y+1, cell.x),
					new Cell(cell.y, cell.x-1),
					new Cell(cell.y-1, cell.x)
			};
			
			for (Cell route: routes) {
				if (maze.isCellValid(route) && ! maze.isVisited(route) && ! maze.isBlocked(route)) {
					if (maze.checkForBorder(route)) isCyclic = false;
					
					maze.setVisited(route);
					lifo.push(route);
				}
			}
		}
		
		return isCyclic;
	}
	
	public static int getStep() {
		return step;
	}

	public static void resetStep() {
		step = 0;
	}
	
}
