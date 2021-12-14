package task_2;

import shared.Edge;
import shared.EdgeWeightedGraph;

import java.util.*;
import java.util.Map.Entry;


public class CountryMap {
	private Map<Integer, City> cities;
	private EdgeWeightedGraph graph;
	
	public CountryMap(int cityCount) {
		cities = new HashMap<>(cityCount);
		graph = new EdgeWeightedGraph(cityCount);
	}
	
	private double distanceBetween(City c1, City c2) {
		double deltaXSquare = Math.abs((c2.X() - c1.X()) * (c2.X() - c1.X()));
		double deltaYSquare = Math.abs((c2.Y() - c1.Y()) * (c2.Y() - c1.Y()));
		return Math.sqrt(deltaXSquare + deltaYSquare);
	}
	
	public void addCity(int id, City city) {
		cities.put(id, city);
	}
	
	public void connectCities(int v, int w) {
		City c1 = getCityWithIdOf(v);
		City c2 = getCityWithIdOf(w);
		double distance = distanceBetween(c1, c2);
		graph.addEdge(new Edge(v, w, distance));
	}
	
	public int getIdOfCityWithName(String cityName) throws Exception {
		for (Entry<Integer, City> pair: cities.entrySet()) {
			if (pair.getValue().Name().equals(cityName)) {
				return pair.getKey();
			}
		}

		throw new Exception("There is no city with the name of " + cityName);
	}
	
	public City getCityWithIdOf(int id) {
		return cities.get(id);
	}
	
	public EdgeWeightedGraph getGraph() {
		return graph;
	}
}
