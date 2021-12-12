package task_1;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import shared.EdgeWeightedGraph;
import shared.PrimMST;

public class Driver {

	public static void main(String[] args) {
		String fileName = "sample_input1.txt";
		
		if (args.length > 0) {
			if (args[0].endsWith(".txt")) {
				fileName = args[0];
			}
		}
		
		Cities cities = null;
		try {
			cities = Utils.BuildCityGraphFromFile(fileName);
		} 
		catch (FileNotFoundException e) {
			// TODO: handle exception
			System.exit(1);
		} 
		catch (NoSuchElementException e) {
			// TODO: handle exception
			System.exit(1);
		} 
		catch (NumberFormatException e) {
			// TODO: handle exception
			System.exit(1);
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.exit(1);
		}
		
		for (String edge: Utils.MinimumSpanningRoadsOf(cities)) {
			System.out.println(edge);
		}
		
	}

}
