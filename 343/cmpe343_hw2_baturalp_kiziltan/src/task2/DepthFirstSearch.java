//-----------------------------------------------------
// Title: Depth First Search
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: This class includes DFS algorithm to solve cycles on the maze.
//-----------------------------------------------------

package task2;

import java.util.*;

import task2.Maze.Cell;

public class DepthFirstSearch {

    private static int step = 0;
    
    public static boolean IsThereAnyCycle(Maze m, Maze.Cell src) {
        //--------------------------------------------------------
        // Summary: Public wrapper method for DFS algorithm. 
        // Precondition: m is a Maze, src is a Cell 
        // Postcondition: returns boolean
        //--------------------------------------------------------
    	
    	return DFS(m, src);
    }
    
	private static boolean DFS(Maze maze, Maze.Cell source) {
        //--------------------------------------------------------
        // Summary: Iterative version of standard Depth First Search algorithm.
		// In each step pops a cell from the stack and checks if the cell is visited,
		// valid or blocked for 4 different directions (up, right, left, down).
		// Then pushes valid cells into stack and does same processes for them.
		// If no cell left in the stack, then decides whether is the source cell
		// creates a cycle or not. It depends on some neighbors of the cells on the stack
		// intersected with border of the maze that breaks circularity.
        // Precondition: m is a Maze instance, source is a Cell 
        // Postcondition: returns boolean
        //--------------------------------------------------------
    	
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
					if (maze.checkForBorder(route)) 
						isCyclic = false;
					
					maze.setVisited(route);
					lifo.push(route);
				}
			}
		}
		
		return isCyclic;
	}
	
	// Getter for step number of current execution of DFS.
	public static int getStep() {
		return step;
	}

	// Resets step variable to default value of 0.
	public static void resetStep() {
		step = 0;
	}
	
}
