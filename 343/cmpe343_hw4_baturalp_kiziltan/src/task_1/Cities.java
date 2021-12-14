package task_1;

import java.util.ArrayList;
import shared.Edge;
import shared.EdgeWeightedGraph;

public class Cities {
	private ArrayList<String> cityNames;
	private EdgeWeightedGraph cityGraph;
	
	public Cities(int cityCount) {
		cityNames = new ArrayList<>(cityCount);
		cityGraph = new EdgeWeightedGraph(cityCount);
	}
	
	public LazyPrimMST FindMST() {
		return new LazyPrimMST(cityGraph);
	}
	
	public void addCity(String city) {
		cityNames.add(city);
	}
	
	public void connectCities(String c1, String c2, double timeBetween) {
		int v = getCityIndexOf(c1);
		int w = getCityIndexOf(c2);
		
		Edge road = new Edge(v, w, timeBetween);
		cityGraph.addEdge(road);
	}
	
	public int getCityIndexOf(String c) {
		return cityNames.indexOf(c);
	}

	public String getCityNameAt(int v) {
		return cityNames.get(v);
	}

	public EdgeWeightedGraph getCityGraph() {
		return cityGraph;
	}
	
}
