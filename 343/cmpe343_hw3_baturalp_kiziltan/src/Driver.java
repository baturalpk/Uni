
import java.io.File;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		ProjectScheduler scheduler = null;
		String filename = null;
		
		if (args.length <= 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the input filename: ");
			filename = sc.nextLine().trim();
			sc.close();
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
			e.printStackTrace();
		}
		
		Scanner stdIn = new Scanner(System.in);
		while (true) {
			System.out.printf("Enter choice (0: Exit, 1: List schedule, 2: Check order): ");
			String line = stdIn.nextLine().trim();
			
			int command = -1;
			try {
				command = Integer.parseInt(line);
			} 
			catch (NumberFormatException e) {
				System.out.println("Invalid command. Type only numbers [0-2]");
			}
			
			switch (command) {
				case 0:
					System.exit(0);;
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
