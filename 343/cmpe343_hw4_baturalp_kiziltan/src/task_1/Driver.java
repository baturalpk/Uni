//-----------------------------------------------------
// Title: Driver class for Task 1
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class is the entry point of Task 1 application
//-----------------------------------------------------

package task_1;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
    	//--------------------------------------------------------
    	// Summary: -- MAIN method --
		// Reads filename from scanner or defaults to "sample_input1.txt" if
		// user's input does not end with .txt suffix. Then builds a Cities instance from
		// this input file. Finally, calculates and prints minimum spanning tree of the city graph.
    	// Precondition: args --> String array
    	// Postcondition: file is read and MST of cities are printed
    	//--------------------------------------------------------
    	
		Scanner in = new Scanner( System.in );
		String line = in.nextLine().trim();
		
		String fileName = (line.endsWith(".txt"))
				? line
				: "sample_input1.txt";
		
		in.close();
		
		Cities cities = null;
		try {
			cities = Utils.BuildCitiesFromFile(fileName);
		} 
		catch (FileNotFoundException e) {
			System.err.println("The specified file NOT found.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e) {
			System.err.println("Check validity of the file.");
			e.printStackTrace();
			System.exit(1);
		}

		for (String result: Utils.MinimumSpanningRoadsOf(cities)) {
			System.out.println(result);
		}
		
	}

}
