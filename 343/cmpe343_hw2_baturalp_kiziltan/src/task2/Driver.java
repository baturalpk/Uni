//-----------------------------------------------------
// Title: Driver
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: Entry point of task 2 app.
//-----------------------------------------------------

package task2;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws Exception {
        //--------------------------------------------------------
        // Summary: Get the filename. Read the maze file to build the maze programmatically.
		// Then run DFS algorithm on the maze to find cycles.
        // Precondition: args is string array
        // Postcondition: Cycle count and the longest one are printed out.
        //--------------------------------------------------------
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename: ");
		String filename = sc.nextLine().trim();
		sc.close();

		Maze maze = Utils.ReadMazeFile(filename);
		Utils.SearchForCycles(maze);
		
	}

}
