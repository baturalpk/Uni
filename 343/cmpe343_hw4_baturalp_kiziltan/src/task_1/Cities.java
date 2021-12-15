//-----------------------------------------------------
// Title: Cities class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class provides necessary abstractions
// to store multiple cities with their names in an edge-weighted graph.
//-----------------------------------------------------

package task_1;

import java.util.ArrayList;
import shared.Edge;
import shared.EdgeWeightedGraph;

public class Cities {
	private ArrayList<String> cityNames;
	private EdgeWeightedGraph cityGraph;
	
	public Cities(int cityCount) {
    	//--------------------------------------------------------
    	// Summary: Initializes member variables
    	// Precondition: cityCount --> integer
    	// Postcondition: cityNames and cityGraph are init. with specified
		// number of empty spaces
    	//--------------------------------------------------------
    	
		cityNames = new ArrayList<>(cityCount);
		cityGraph = new EdgeWeightedGraph(cityCount);
	}
	
	public LazyPrimMST FindMST() {
    	//--------------------------------------------------------
    	// Summary: Builds a minimum spanning tree from graph representation of
		// cities using Prim's lazy MST algorithm.
    	// Precondition: -
    	// Postcondition: Returned Lazy Prim MST of cities.
    	//--------------------------------------------------------
    	
		return new LazyPrimMST(cityGraph);
	}
	
	public void addCity(String city) {
    	//--------------------------------------------------------
    	// Summary: Adds name of city.
    	// Precondition: city --> String
    	// Postcondition: the city is added into name list
    	//--------------------------------------------------------
    	
		cityNames.add(city);
	}
	
	public void connectCities(String c1, String c2, double timeBetween) {
    	//--------------------------------------------------------
    	// Summary: Get vertex numbers of cities c1 and c2. Then
		// create an edge with given weight, and insert it into city graph.
    	// Precondition: c1 and c2 --> String, timeBetween --> double
    	// Postcondition: the cities are inserted into the graph with given weight
    	//--------------------------------------------------------
    	
		int v = getCityIndexOf(c1);
		int w = getCityIndexOf(c2);
		
		Edge road = new Edge(v, w, timeBetween);
		cityGraph.addEdge(road);
	}
	
	public int getCityIndexOf(String c) {
    	//--------------------------------------------------------
    	// Summary: get vertex id of city c
    	// Precondition: c --> String
    	// Postcondition: returned vertex id (index) of city c
    	//--------------------------------------------------------
    	
		return cityNames.indexOf(c);
	}

	public String getCityNameAt(int v) {
    	//--------------------------------------------------------
    	// Summary: Gets string representation of city with the vertex id
    	// Precondition: v --> integer
    	// Postcondition: returned name of city with the specified vertex id
    	//--------------------------------------------------------
    	
		return cityNames.get(v);
	}

}
