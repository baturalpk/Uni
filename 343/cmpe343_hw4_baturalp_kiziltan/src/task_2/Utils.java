//-----------------------------------------------------
// Title: 
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class 
//-----------------------------------------------------

package task_2;

import shared.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Utils {
	
	enum TraverseDirection {
		DEFAULT,
		REVERSE
	}
	
	private final static String REGEX = "[ \t]"; // Delimiter for both empty space and tab characters
	
	static CountryMap BuildCountryMap(BufferedReader in) throws IOException {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		String[] firstLine = in.readLine().trim().split(REGEX);
		int vertexCount = Integer.parseInt(firstLine[0]);
		int edgeCount = Integer.parseInt(firstLine[1]);
		
		CountryMap map = new CountryMap(vertexCount);
		
		for (int i = 0; i < vertexCount; ++i) {
			String[] line = in.readLine().trim().split(REGEX);
			
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
			String[] line = in.readLine().trim().split(REGEX);
			
			int v = Integer.parseInt(line[0]);
			int w = Integer.parseInt(line[1]);
			map.connectCities(v, w);
		}
		
		return map;
	}
	
	static DijkstraUndirectedSP FindShortestPathsOnMap(CountryMap map, String sourceCity) throws Exception {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return new DijkstraUndirectedSP(map.getGraph(), map.getIdOfCityWithName(sourceCity));
	}
	
	static String InfoAboutShortestPathTo(String targetCity, DijkstraUndirectedSP dusp, CountryMap map) throws Exception {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
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
		
		info.append(visitedCities.size()).append(" cities to be visited: \n");

		Iterator<String> visited = visitedCities.iterator();
		while (visited.hasNext()) {
			info.append(visited.next()).append("\n");
		}
		
		info.append("Distance: ").append((int) pathLength).append(" km");
		return info.toString();
	}
	
}
