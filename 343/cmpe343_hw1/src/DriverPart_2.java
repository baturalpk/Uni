//-----------------------------------------------------
// Title: Driver 2
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class is entry point for the Part 2 application.
//-----------------------------------------------------
import java.util.ArrayList;

public class DriverPart_2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		IHashTable<String, Integer> linearProbingHT = new HashTableLP<>();
		IHashTable<String, Integer> separateChainingHT = new HashTableSC<>();
		
		System.out.println("*** Starting...");
		
		// DATASETS
		ArrayList<String> smallDataset = Utils.ReadInputFileContent("small"); 	// 20 distinct words
		ArrayList<String> mediumDataset = Utils.ReadInputFileContent("medium");	// 1000 distinct words
		ArrayList<String> largeDataset = Utils.ReadInputFileContent("large");	// 5000 distinct words
		
		
		// SMALL
		System.out.println("\nSMALL WORDLIST:");
		System.out.println("Benchmark results for Linear Probing Hash Table:");
		Benchmark.Execute(linearProbingHT, smallDataset);
		
		System.out.println("\nBenchmark Results for Separate Chaining Hash Table:");
		Benchmark.Execute(separateChainingHT, smallDataset);
		System.out.println("-------------------------------------");
		
		// MEDIUM
		System.out.println("\nMEDIUM WORDLIST:");
		System.out.println("Benchmark results for Linear Probing Hash Table:");
		Benchmark.Execute(linearProbingHT, mediumDataset);
		
		System.out.println("\nBenchmark results for Separate Chaining Hash Table:");
		Benchmark.Execute(separateChainingHT, mediumDataset);
		System.out.println("-------------------------------------");
		
		// LARGE
		System.out.println("\nLARGE WORDLIST:");
		System.out.println("Benchmark results for Linear Probing Hash Table:");
		Benchmark.Execute(linearProbingHT, largeDataset);
		
		System.out.println("\nBenchmark results for Separate Chaining Hash Table:");
		Benchmark.Execute(separateChainingHT, largeDataset);
		System.out.println("-------------------------------------");

		
		System.out.println("\n-- ALL DONE --");
		
	}

}
