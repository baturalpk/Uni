package task_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		String fileName = "usa.txt"; // "sample_input2.txt";
		String sourceCity = null;
		String targetCity = null;
		
		if (args.length > 0) {
			if (args[0].endsWith(".txt")) {
				fileName = args[0];
			}
			
			if (args.length == 3) {
				sourceCity = args[1];
				targetCity = args[2];
			}
		}
		
		BufferedReader fileio = new BufferedReader(new FileReader(fileName, Charset.forName("UTF-8")));
		CountryMap countryMap = Utils.BuildCountryMap(fileio);
		fileio.close();

		System.out.println("inputs: ");
		Scanner in = new Scanner(System.in);
		String line = in.nextLine().trim();
		String city[] = line.split(" ");
		if (sourceCity == null || targetCity == null) {
			sourceCity = city[0];
			targetCity = city[1];
		}
		in.close();
		
		DijkstraUndirectedSP dusp = Utils.FindShortestPathsOnMap(countryMap, sourceCity);
		String result = Utils.InfoAboutShortestPathTo(targetCity, dusp, countryMap);
		System.out.println(result);
	}

}
