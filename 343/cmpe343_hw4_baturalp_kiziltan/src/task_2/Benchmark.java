//-----------------------------------------------------
// Title: 
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class 
//-----------------------------------------------------

package task_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Benchmark {
	
	public static void main(String[] args) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		CountryMap sampleMap = null;
		CountryMap turkeyMap = null;
		CountryMap usaMap = null;
		
		System.out.println("* running ...\n");
		
		try {
			FileReader sample = new FileReader("sample_input2.txt");
			FileReader turkey = new FileReader("turkeymap.txt");
			FileReader usa = new FileReader("usa.txt");
			
			sampleMap = Utils.BuildCountryMap(new BufferedReader(sample));
			turkeyMap = Utils.BuildCountryMap(new BufferedReader(turkey));
			usaMap = Utils.BuildCountryMap(new BufferedReader(usa));
			
			sample.close();
			turkey.close();
			usa.close();
		} 
		catch (FileNotFoundException e) {
			// TODO: handle exception
			System.exit(1);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(1);
		}
		finally {
			
			
		}
		
		DijkstraUndirectedSP sampleSP = new DijkstraUndirectedSP(sampleMap.getGraph(), 0);
		DijkstraUndirectedSP turkeySP = new DijkstraUndirectedSP(turkeyMap.getGraph(), 0);
		DijkstraUndirectedSP usaSP = new DijkstraUndirectedSP(usaMap.getGraph(), 0);
		
		StringBuilder result = new StringBuilder();
		
		result
			.append("=== TIME PERFORMANCE RESULTS: ===\n")
			.append("* Total elapsed time to build small dataset: ").append(sampleSP.ELAPSED_TIME_TO_BUILD).append(" ms.")
			.append("\n")
			.append("* Total elapsed time to build medium dataset: ").append(turkeySP.ELAPSED_TIME_TO_BUILD).append(" ms.")
			.append("\n")
			.append("* Total elapsed time to build large dataset: ").append(usaSP.ELAPSED_TIME_TO_BUILD).append(" ms.")
			.append("\n")
			.append("=== DONE ===");
		
		System.out.println(result.toString());
	}
	
}
