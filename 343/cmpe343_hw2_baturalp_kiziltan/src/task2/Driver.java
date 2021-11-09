package task2;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename: ");
		String filename = sc.nextLine().trim();
		sc.close();

		Maze maze = Utils.ReadMazeFile(filename);
		Utils.SearchForCycles(maze);
		
	}

}
