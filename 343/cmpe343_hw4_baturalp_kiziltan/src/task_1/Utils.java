package task_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import shared.Edge;
import shared.EdgeWeightedGraph;
import shared.LazyPrimMST;
import shared.PrimMST;

public class Utils {
	
	static Cities BuildCityGraphFromFile(String fileName) throws Exception {
		Scanner fileio = new Scanner(new File(fileName));
		
		String firstLine = fileio.nextLine();
		String cityNames[] = firstLine.split(" ");
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
		LazyPrimMST mst = cities.FindMST();
		ArrayList<String> roads = new ArrayList<>();
		DecimalFormat fmt = new DecimalFormat("0.#");
		
		roads.add(fmt.format(mst.weight()));
		
		for (Edge e: mst.edges()) {
			int v = e.either();
			int w = e.other(v);
			String c1 = cities.getCityNameAt(v);
			String c2 = cities.getCityNameAt(w);
			
			String road = String.format("%s %s %s", c1, c2, fmt.format(e.weight()));
			roads.add(road);
		}
		
		return roads;
	}

}