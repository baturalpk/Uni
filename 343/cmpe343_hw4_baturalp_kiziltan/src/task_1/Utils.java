//-----------------------------------------------------
// Title: Utilities class for Task 1
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class contains required utilities and functionalities
// for Task 1 application
//-----------------------------------------------------

package task_1;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import shared.Edge;

public class Utils {
	
	static Cities BuildCitiesFromFile(String fileName) throws Exception {
    	//--------------------------------------------------------
    	// Summary: Takes filename and opens the file. Then reads the input file line by
		// line to build a Cities object from it. First line contains city names. Then
		// sorts these inserted city names by lexicographical order. After that operation,
		// reads edges (roads) and their weights (time between) line by line to insert into
		// the city graph.
    	// Precondition: fileName --> String
    	// Postcondition: The cities are inserted into Cities' graph instance with their
		// names and edge connections.
    	//--------------------------------------------------------
    	
		Scanner fileio = new Scanner(new File(fileName));
		
		String firstLine = fileio.nextLine();
		String[] cityNames = firstLine.split(" ");
		Cities cities = new Cities(cityNames.length);
		Arrays.sort(cityNames);
		
		for (String city: cityNames) {
			cities.addCity(city.trim());
		}
		
		while (fileio.hasNextLine()) {
			String line = fileio.nextLine();
			String[] items = line.split(" ");
			
			if (items.length != 3) {
				throw new Exception("The line is not triple. Expected: \"city1 city2 weight\"");
			}
			
			double timeBetweenCities = Double.parseDouble(items[2].trim());
			cities.connectCities(items[0].trim(), items[1].trim(), timeBetweenCities);		
		}
		
		fileio.close();
		return cities;
	}
	
	static Iterable<String> MinimumSpanningRoadsOf(Cities cities) {
    	//--------------------------------------------------------
    	// Summary: Build a MST from cities' graph. Then, stores MST
		// properties (e.g., total edge weight, city names in order) using
		// a list of strings to be able to print on console later.
		// * Tricky part: To follow the natural traversal direction of MST,
		// in the loop part, the program compares current city edge by the previous one.
		// And switches (turns around) the edge if necessary.
		// (e.g.,
		// * LA --> SF
		// * Seattle --> SF
		// 		<becomes>
		// * LA --> SF
		// * SF --> Seattle
		// )
    	// Precondition: cities --> Cities
    	// Postcondition: results list, an arraylist of strings, is built
		// to print the minimum spanning tree properties of the cities.
    	//--------------------------------------------------------
    	
		LazyPrimMST mst = cities.FindMST();
		ArrayList<String> results = new ArrayList<>();
		DecimalFormat fmt = new DecimalFormat("0.#");

		results.add(fmt.format(mst.weight()));
		
		Edge prevRoad = null;
		for (Edge e: mst.edges()) {
			int v = e.either();
			int w = e.other(v);
			
			if (prevRoad == null) prevRoad = e;
			else {
				int prevW = prevRoad.other(prevRoad.either());
				if (prevW == w) {
					w = v;
					v = prevW;
					prevRoad = e.switched();
				}
				else {
					prevRoad = e;
				}
			}
			
			String c1 = cities.getCityNameAt(v);
			String c2 = cities.getCityNameAt(w);
			
			String road = String.format("%s %s %s", c1, c2, fmt.format(e.weight()));
			results.add(road);
		}
		
		return results;
	}

	public static boolean test() throws Exception {
		// Helper method for Tester class.
		// Compares expected and exact results each other.

		Cities cities = Utils.BuildCitiesFromFile("custom_test_input1.txt");

		StringBuilder expected = new StringBuilder();
		expected
				.append(12 + " ")
				.append("Ankara SF 2 ")
				.append("SF LA 3 ")
				.append("LA London 7 ");

		StringBuilder result = new StringBuilder();
		for (String line : Utils.MinimumSpanningRoadsOf(cities)) {
			result.append(line).append(" ");
		}

		System.out.println("=== Summary for TASK 1 Test:");
		System.out.println("Expected: " + expected);
		System.out.println("Result: " + result);

		return result.toString().equals(expected.toString());
	}

}
