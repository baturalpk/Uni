//-----------------------------------------------------
// Title: Driver 1
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class is entry point for the Part 1 application.
//-----------------------------------------------------
import java.util.ArrayList;

public class DriverPart_1 {

	public static void main(String[] args) {
		//--------------------------------------------------------
		// Summary: Creates 2 different hash table implementations. Reads inputs from user as a one-liner.
		// Fills dictionaries with the given user input. Print table sizes and most recurring keys with their indices.
		// Precondition: args is string array
		// Postcondition: Final table sizes and most recurring 3 words are found and printed out to console.
		//--------------------------------------------------------
		
		IHashTable<String, Integer> linearProbingHT = new HashTableLP<>();
		IHashTable<String, Integer> separateChainingHT = new HashTableSC<>();
		
		System.out.println("Waiting for input:");
		ArrayList<String> content = Utils.ReadFromScanner();
		
		/*
		// OPTIONAL: Reads directly from command-line arguments
		
		if (args.length <= 0) {
			System.err.println("No argument provided!");
			System.exit(1);
		}
		
		ArrayList<String> content = Utils.ReadFromArguments(args);
		*/
		
		Utils.FillDictionaryWithValues(content, linearProbingHT);
		Utils.FillDictionaryWithValues(content, separateChainingHT);
		
		System.out.printf("Final table sizes for linear probing and separate chaining are %d and %d.\n\n",
				linearProbingHT.getM(), 
				separateChainingHT.getM());
		
		System.out.println("Top 3 most used words, their indexes for linear probing, their node indexes "
				+ "for separate chaining and their number of occurrences:");
		
		Utils.FindAndPrintMostRecurring3Keys(
				(HashTableLP<String, Integer>) linearProbingHT, 
				(HashTableSC<String, Integer>) separateChainingHT);
	
	}

}
