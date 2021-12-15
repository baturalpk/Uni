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
		
		Scanner in = new Scanner( System.in );
		String fileName = in.next().trim();
		String sourceCity = in.next().trim();
		String targetCity = in.next().trim();
		in.close();
		
		BufferedReader fileio = new BufferedReader(new FileReader(fileName));
		CountryMap countryMap = Utils.BuildCountryMap(fileio);
		fileio.close();
		
		DijkstraUndirectedSP dusp = Utils.FindShortestPathsOnMap(countryMap, sourceCity);
		String result = Utils.InfoAboutShortestPathTo(targetCity, dusp, countryMap);
		System.out.println(result);
	}

}
