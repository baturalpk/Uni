//-----------------------------------------------------
// Title: Driver
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: Entry point of task 1 app.
//-----------------------------------------------------

package task1;
import java.io.File;
import java.util.*;

public class Driver {
	
	public static void main(String[] args) {
        //--------------------------------------------------------
        // Summary: Creates 2 int array that will contain source and target numbers by digits.
		// Use an arraylist for marked numbers. Read forbidden numbers from the file and mark as marked.
		// Send source, target, marked variables into BFS algorithm to decrypt the safe. Print the result.
        // Precondition: args is string array
        // Postcondition: Result is found and printed.
        //--------------------------------------------------------
		
		int[] source = null;
		int[] target = null;
		int numberOfForbiddens = 0;
		ArrayList<Integer> marked = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename: ");
		String fileName = sc.nextLine().trim();
		sc.close();
		try {
			Scanner fileio = new Scanner(new File(fileName));
			source = Utils.ParseStringTo4Digits(fileio.nextLine().trim());

			target = Utils.ParseStringTo4Digits(fileio.nextLine().trim());
			numberOfForbiddens = Integer.parseInt(fileio.nextLine().trim());

			for (int i = 0; i < numberOfForbiddens; ++i) {
				marked.add(Utils.Parse4DigitsToInt(Utils.ParseStringTo4Digits(fileio.nextLine().trim())));
			}

			fileio.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		int result = BreadthFirstSearch.DecryptTheSafe(
				Utils.Parse4DigitsToInt(source), 
				Utils.Parse4DigitsToInt(target), 
				marked);
		System.out.println(result);
		
	}
}
