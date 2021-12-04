//-----------------------------------------------------
// Title: Driver
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class is the entry-point of application.
//-----------------------------------------------------

import java.io.File;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		//--------------------------------------------------------
		// Summary: 
		// 	1- Read filename
		// 	2- Open file and create a Project Scheduler instance with file contents
		// 	3- Start event loop and wait user to make a choice (Exit, List, CheckOrder)
		// Precondition: args is string array
		// Postcondition: schedule is created and user actions are processed
		//--------------------------------------------------------
		
		ProjectScheduler scheduler = null;
		String filename = null;
		Scanner stdIn = new Scanner(System.in);
		
		if (args.length <= 0) {
			System.out.println("Enter the input filename: ");
			filename = stdIn.nextLine().trim();
		}
		else {
			filename = args[0];
		}
		
		try {
			Scanner fileIO = new Scanner(new File(filename));
			scheduler = new ProjectScheduler(fileIO);
			fileIO.close();
		} catch (Exception e) {
			System.out.println("Failed to initialize Project Scheduler:");
			System.out.println("\t- Check input source");
			stdIn.close();
			e.printStackTrace();
		}
		
		while (true) {
			System.out.printf("Enter choice (0: Exit, 1: List schedule, 2: Check order): ");
			String line = stdIn.nextLine().trim();
			
			int command = -1;
			try {
				command = Integer.parseInt(line);
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid command. Type only numbers [0-2].");
				continue;
			}
			
			switch (command) {
				case 0:
					stdIn.close();
					System.exit(0);
					break;
				case 1:
					System.out.println(scheduler.List());
					break;
				case 2:
					System.out.print("Enter first task: ");
					String task1 = stdIn.nextLine().trim();
					System.out.print("Enter second task: ");
					String task2 = stdIn.nextLine().trim();
					
					if (task2.equals(task1)) 
						System.out.println("Enter a task different than the first one.");
					
					int result = scheduler.CheckOrder(task1, task2);
					
					if (result == 0) {
						System.out.printf("You should do %s and %s on the same day.\n", task1, task2);
					}
					else if (result == 1) {
						System.out.printf("You should do %s before %s.\n", task1, task2);
					}
					else if (result == -1) {
						System.out.printf("You should do %s after %s.\n", task1, task2);
					}
					break;
				default: System.out.println("Unknown command. Try something else.");
			}
		}
	}

}
