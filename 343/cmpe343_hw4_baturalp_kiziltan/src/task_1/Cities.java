//-----------------------------------------------------
// Title: 
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class 
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
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		cityNames = new ArrayList<>(cityCount);
		cityGraph = new EdgeWeightedGraph(cityCount);
	}
	
	public LazyPrimMST FindMST() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return new LazyPrimMST(cityGraph);
	}
	
	public void addCity(String city) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		cityNames.add(city);
	}
	
	public void connectCities(String c1, String c2, double timeBetween) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		int v = getCityIndexOf(c1);
		int w = getCityIndexOf(c2);
		
		Edge road = new Edge(v, w, timeBetween);
		cityGraph.addEdge(road);
	}
	
	public int getCityIndexOf(String c) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return cityNames.indexOf(c);
	}

	public String getCityNameAt(int v) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return cityNames.get(v);
	}

	public EdgeWeightedGraph getCityGraph() {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
    	//--------------------------------------------------------
    	
		return cityGraph;
	}
	
}
