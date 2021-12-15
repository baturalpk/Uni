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
import java.io.FileReader;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		String fileName = "sample_input2.txt";
		String sourceCity = null;
		String targetCity = null;
		
		if (args.length > 0) {
			if (args[0].endsWith(".txt")) {
				fileName = args[0];
			}
			
			if (args.length == 3) {
				sourceCity = args[1];
				targetCity = args[2];
			}
		}
		
		BufferedReader fileio = new BufferedReader(new FileReader(fileName));
		CountryMap countryMap = Utils.BuildCountryMap(fileio);
		fileio.close();

		System.out.println("inputs: ");
		Scanner in = new Scanner(System.in);
		String line = in.nextLine().trim();
		String city[] = line.split(" ");
		if (sourceCity == null || targetCity == null) {
			sourceCity = city[0].trim();
			targetCity = city[1].trim();
		}
		in.close();
		
		DijkstraUndirectedSP dusp = Utils.FindShortestPathsOnMap(countryMap, sourceCity);
		String result = Utils.InfoAboutShortestPathTo(targetCity, dusp, countryMap);
		System.out.println(result);
	}

}
