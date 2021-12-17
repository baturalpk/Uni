//-----------------------------------------------------
// Title: Utilities class for Task 2
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class contains required utilities and functionalities
// for solving Task 2.
//-----------------------------------------------------

package task_2;

import shared.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Utils {

	// Describes two possible traversal direction for
	// the shortest path algorithm to be able to identify
	// natural lying down position of roads/edges.
	enum TraverseDirection {
		DEFAULT,
		REVERSE
	}
	
	private final static String DELIMITER = "[ \t]"; // Delimiter for both empty space and tab characters
	
	static CountryMap BuildCountryMap(BufferedReader in) throws IOException {
    	//--------------------------------------------------------
    	// Summary: Reads edge and vertex count from the first line of the input. After that,
		// iterates over the file up to vertex count. In each iteration over a line, creates a
		// City object and inserts it into Country Map instance. Does similar operation for creating
		// edge connections between cities.
    	// Precondition: in --> BufferedReader (input file)
    	// Postcondition: throws exception or creates a new CountryMap instance and returns
    	//--------------------------------------------------------
    	
		String[] firstLine = in.readLine().trim().split(DELIMITER);
		int vertexCount = Integer.parseInt(firstLine[0]);
		int edgeCount = Integer.parseInt(firstLine[1]);
		
		CountryMap map = new CountryMap(vertexCount);
		
		for (int i = 0; i < vertexCount; ++i) {
			String[] line = in.readLine().trim().split(DELIMITER);
			
			String cityName;
			if (line.length == 4) {
				cityName = line[3];
			}else {
				cityName = line[0];
			}
			
			int id = Integer.parseInt(line[0]);			
			City city = new City(
				Integer.parseInt(line[1]),
				Integer.parseInt(line[2]),
				cityName
			);
			
			map.addCity(id, city);
		}
		
		for (int i = 0; i < edgeCount; ++i) {
			String[] line = in.readLine().trim().split(DELIMITER);
			
			int v = Integer.parseInt(line[0]);
			int w = Integer.parseInt(line[1]);
			map.connectCities(v, w);
		}
		
		return map;
	}
	
	static DijkstraUndirectedSP FindShortestPathsOnMap(CountryMap map, String sourceCity) throws Exception {
    	//--------------------------------------------------------
    	// Summary: Takes a pre-built CountryMap instance and a source city name to
		// find the shortest path on it.
    	// Precondition: map --> CountryMap, sourceCity --> String
    	// Postcondition: throws exception or returns a DijkstraUndirectedSP.
    	//--------------------------------------------------------
    	
		return new DijkstraUndirectedSP(map.getGraph(), map.getIdOfCityWithName(sourceCity));
	}
	
	static String InfoAboutShortestPathTo(String targetCity, DijkstraUndirectedSP dusp, CountryMap map) throws Exception {
    	//--------------------------------------------------------
    	// Summary: Uses a StringBuilder to return the result. Then does the following steps:
		// 1) Declares and/or init. total path length, visitedCities set, and traversal direction.
		// 2) Iterates over the pre-calculated shortest paths by giving target city.
		// 		* At first iteration, it determines the traversal direction depending on natural
		// 		position of roads (edges).
		// 3) In each iteration extracts city names by looking up to both vertices on the edge.
		// 4) Adds these city names to visitedCities set (since it's a linked set, further re-insertions
		//    are idempotent, and preserves the visit order). Additionally, increases total pathLength by
		//	  adding weight of current edge.
		// 5) Finally, appends some kind of information such as how many cities to be visited, or
		// 	  what is the names of cities which was previously visited in order.
    	// Precondition: targetCity --> String, dusp --> DijkstraUndirectedSP, map --> CountryMap
    	// Postcondition: throws exception or returns printable information about the shortest map to target city.
    	//--------------------------------------------------------
    	
		StringBuilder info = new StringBuilder();
		int targetID = map.getIdOfCityWithName(targetCity);
		
		if (! dusp.hasPathTo(targetID)) {
			info
				.append("! There is no path to ").append(targetCity)
				.append(" from ").append(map.getCityWithIdOf(dusp.SOURCE));
			
			return info.toString();
		}
		
		double pathLength = .00;
		Set<String> visitedCities = new LinkedHashSet<>();
		TraverseDirection travelDir = null;
		
		for (Edge road: dusp.pathTo(targetID)) {
			int v = road.either();
			int w = road.other(v);

			if (travelDir == null) {
				if (v == dusp.SOURCE)
					travelDir = TraverseDirection.DEFAULT;
				else
					travelDir = TraverseDirection.REVERSE;
			}
			
			if (travelDir == TraverseDirection.REVERSE) {
				road = road.switched();
				v = road.either();
				w = road.other(v);
			}
			
			pathLength += road.weight();
			visitedCities.add(map.getCityWithIdOf(v).Name());
			visitedCities.add(map.getCityWithIdOf(w).Name());
		}
		
		info.append(visitedCities.size()).append(" cities to be visited:\n");

		Iterator<String> visited = visitedCities.iterator();
		while (visited.hasNext()) {
			info.append(visited.next()).append("\n");
		}
		
		info.append("Distance: ").append((int) pathLength).append(" km");
		return info.toString();
	}
	
}
