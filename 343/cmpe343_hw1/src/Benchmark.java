//-----------------------------------------------------
// Title: Benchmark - performance tester class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class includes required utility functions to test implemented hash tables
//				from performance perspective (time & space complexity).
//-----------------------------------------------------
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class Benchmark {
	
	public static void Execute(IHashTable<String, Integer> dictionary, ArrayList<String> content) throws InterruptedException {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		long timeElapsedAddition = TestInsertionOperation(dictionary, content);
		System.out.println("Put operation:");
		System.out.println("* Time taken: " + timeElapsedAddition + " ms.");
		System.out.println("* Table size: " + dictionary.getM());
		Thread.sleep(1000);
		
		long timeElapsedRetrieval = TestGetOperation(dictionary, content);
		System.out.println("Get operation:");
		System.out.println("* Time taken: " + timeElapsedRetrieval + " ms.");
		System.out.println("* Table size: " + dictionary.getM());
		Thread.sleep(1000);
		
		long timeElapsedDeletion = TestDeletionOperation(dictionary, content);
		System.out.println("Delete operation:");
		System.out.println("* Time taken: " + timeElapsedDeletion + " ms.");
		System.out.println("* Table size: " + dictionary.getM());
		Thread.sleep(1000);
		
	}
	
	private static long TestInsertionOperation(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		Instant __start__ = Instant.now();
		
		for (int i = 0; i < 100000; ++i) {
			Utils.FillDictionaryWithValues(words, dictionary);
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}

	private static long TestDeletionOperation(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		// Shuffles the words to simulate real life case.
		Collections.shuffle(words);
		
		Instant __start__ = Instant.now();
		
		// Removes all content from dictionary one by one
		for (String word: words) {
			dictionary.remove(word);
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}
	

	private static long TestGetOperation(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		// Shuffles the words to simulate real life case.
		Collections.shuffle(words);
		
		Instant __start__ = Instant.now();
		
		// Retrieve an element in each iteration
		for (String word: words) {
			Integer value = dictionary.get(word);
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}
	
}
