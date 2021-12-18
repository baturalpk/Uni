//-----------------------------------------------------
// Title: Benchmark class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class measures and reports the Task 2 application in
// terms of performance (time complexity).
//-----------------------------------------------------

package task_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Benchmark {
	
	public static void main(String[] args) {
    	//--------------------------------------------------------
    	// Summary: The entry-point for benchmark class. It builds 3 CountryMap
		// object instances by reading dataset files. Then, prints total elapsed
		// time for each shortest path finding object (Each DijkstraSP constructor
		// includes a built-in time calculator that has executed on "new" call immediately).
    	// Precondition: args --> String array
    	// Postcondition: Performance results are printed on standard output.
    	//--------------------------------------------------------
    	
		CountryMap turkeyMap = null;
		CountryMap usaMap = null;
		
		System.out.println("* running ...\n");
		
		try {
			FileReader turkey = new FileReader("turkeymap.txt");
			FileReader usa = new FileReader("usa.txt");
			
			turkeyMap = Utils.BuildCountryMap(new BufferedReader(turkey));
			usaMap = Utils.BuildCountryMap(new BufferedReader(usa));
			
			turkey.close();
			usa.close();
		} 
		catch (FileNotFoundException e) {
			System.err.println("File NOT found.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("I/O error's thrown.");
			e.printStackTrace();
			System.exit(1);
		}
		
		DijkstraUndirectedSP turkeySP = new DijkstraUndirectedSP(turkeyMap.getGraph(), 0);
		DijkstraUndirectedSP usaSP = new DijkstraUndirectedSP(usaMap.getGraph(), 0);

		StringBuilder result = new StringBuilder();

		result
			.append("=== TIME PERFORMANCE RESULTS: ===\n")
			.append("* Total elapsed time to build Turkey dataset ")
				.append("(E= ").append(turkeyMap.getGraph().E())
				.append(" V= ").append(turkeyMap.getGraph().V()).append("): ")
				.append(turkeySP.ELAPSED_TIME_TO_BUILD).append(" ms.")
				.append("\n")
			.append("* Total elapsed time to build USA dataset ")
				.append("(E= ").append(usaMap.getGraph().E())
				.append(", V= ").append(usaMap.getGraph().V()).append("): ")
				.append(usaSP.ELAPSED_TIME_TO_BUILD).append(" ms.")
				.append("\n")
			.append("=== DONE ===");
		
		System.out.println(result);

	}
	
}
