//-----------------------------------------------------
// Title: CountryMap class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class defines required level of abstractions to store
// City objects in an edge weighted graph with their IDs.
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
    	// Summary: Creates 1 HashMap to hold cities by their IDs, and 1
		// EdgeWeightedGraph to represent their relations between each other.
    	// Precondition: cityCount --> integer
    	// Postcondition: the instance variables are initialized.
    	//--------------------------------------------------------
    	
		cities = new HashMap<>(cityCount);
		graph = new EdgeWeightedGraph(cityCount);
	}
	
	private double distanceBetween(City c1, City c2) {
    	//--------------------------------------------------------
    	// Summary: calculates euclidean distance between cities
    	// Precondition: c1 and c2 --> City
    	// Postcondition: returned total distane between 2 cities as double
    	//--------------------------------------------------------
    	
		double deltaXSquare = Math.abs((c2.X() - c1.X()) * (c2.X() - c1.X()));
		double deltaYSquare = Math.abs((c2.Y() - c1.Y()) * (c2.Y() - c1.Y()));
		return Math.sqrt(deltaXSquare + deltaYSquare);
	}
	
	public void addCity(int id, City city) {
    	//--------------------------------------------------------
    	// Summary: Stores a City object with its id.
    	// Precondition: id --> integer, city --> City
    	// Postcondition: the city is inserted into cities hashmap with its
		// unique id.
    	//--------------------------------------------------------
    	
		cities.put(id, city);
	}
	
	public void connectCities(int v, int w) {
    	//--------------------------------------------------------
    	// Summary: Retrieves city ids as parameters, then connects
		// them by adding an edge with the calculated distance between them.
    	// Precondition: v and w --> integer
    	// Postcondition: city v & city w are connected with an edge in the graph
    	//--------------------------------------------------------
    	
		City c1 = getCityWithIdOf(v);
		City c2 = getCityWithIdOf(w);
		double distance = distanceBetween(c1, c2);
		graph.addEdge(new Edge(v, w, distance));
	}
	
	public int getIdOfCityWithName(String cityName) throws Exception {
    	//--------------------------------------------------------
    	// Summary: Searches for specified city by its name inside the
		// hash map key-value pairs. Throws an exception if it does not
		// contain.
    	// Precondition: cityName --> String
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
    	// Summary: Returns city that has specified id
    	// Precondition: id --> integer
    	// Postcondition: returned an City object
    	//--------------------------------------------------------
    	
		return cities.get(id);
	}
	
	public EdgeWeightedGraph getGraph() {
    	//--------------------------------------------------------
    	// Summary: Getter for the graph
    	// Precondition: -
    	// Postcondition: returned the internal graph instance itself
    	//--------------------------------------------------------
    	
		return graph;
	}
}
