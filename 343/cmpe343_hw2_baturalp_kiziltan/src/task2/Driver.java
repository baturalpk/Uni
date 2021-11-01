package task2;

import java.time.*;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		String filename = sc.nextLine().trim();
		sc.close();
		
//		Instant __start__ = Instant.now();
		Maze maze = Utils.ReadMazeFile(filename);
		Utils.SearchForCycles(maze);
//		Instant __end__ = Instant.now();
		
//		System.out.println("Time elapsed: " + Duration.between(__start__, __end__).toMillis());
	}

}
