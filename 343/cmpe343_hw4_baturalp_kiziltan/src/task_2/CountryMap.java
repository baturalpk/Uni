//-----------------------------------------------------
// Title: 
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class 
//-----------------------------------------------------

package task_2;

import shared.Edge;
import shared.EdgeWeightedGraph;

import java.util.*;
import java.util.Map.Entry;


public class CountryMap {
	private Map<Integer, City> cities;
	private EdgeWeightedGraph graph;
	
	public CountryMap(int cityCount) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		cities = new HashMap<>(cityCount);
		graph = new EdgeWeightedGraph(cityCount);
	}
	
	private double distanceBetween(City c1, City c2) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		double deltaXSquare = Math.abs((c2.X() - c1.X()) * (c2.X() - c1.X()));
		double deltaYSquare = Math.abs((c2.Y() - c1.Y()) * (c2.Y() - c1.Y()));
		return Math.sqrt(deltaXSquare + deltaYSquare);
	}
	
	public void addCity(int id, City city) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		cities.put(id, city);
	}
	
	public void connectCities(int v, int w) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		City c1 = getCityWithIdOf(v);
		City c2 = getCityWithIdOf(w);
		double distance = distanceBetween(c1, c2);
		graph.addEdge(new Edge(v, w, distance));
	}
	
	public int getIdOfCityWithName(String cityName) throws Exception {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		for (Entry<Integer, City> pair: cities.entrySet()) {
			if (pair.getValue().Name().equals(cityName)) {
				return pair.getKey();
			}
		}

		throw new Exception("There is no city with the name of " + cityName);
	}
	
	public City getCityWithIdOf(int id) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return cities.get(id);
	}
	
	public EdgeWeightedGraph getGraph() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return graph;
	}
}
