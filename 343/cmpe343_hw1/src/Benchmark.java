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
		// Summary: Runs time test functions for operations put, get, and delete respectively.
		// Then prints elapsed times for each operation with table sizes and item counts.
		// Precondition: "dictionary" is a type of hash table, "content" is a string arraylist.
		// Postcondition: Put, Get, Delete operations are tested and results are printed out to console.
		//--------------------------------------------------------

		long timeElapsedInsertion = TestTimeForInsertion(dictionary, content);
		System.out.println("Put operation:");
		System.out.println("* Time taken: " + timeElapsedInsertion + " ms.");
		System.out.printf("* M=%d, N=%d, ", dictionary.getM(), dictionary.size());

		if (dictionary.getClass().equals(HashTableLP.class)) {
			System.out.printf("LoadFactor(N/M)=%.2f\n", (float)dictionary.size()/(float)dictionary.getM());
		}
		else {
			System.out.printf("AverageItemCountPerList(N/M)=%.2f\n", (float)dictionary.size()/(float)dictionary.getM());
		}

		Thread.sleep(500);
		
		long timeElapsedRetrieval = TestTimeForRetrieval(dictionary, content);
		System.out.println("Get operation:");
		System.out.println("* Time taken: " + timeElapsedRetrieval + " ms.");
		System.out.println("* Table size and total item count did not change.");
		Thread.sleep(500);
		
		long timeElapsedDeletion = TestTimeForDeletion(dictionary, content);
		System.out.println("Delete operation:");
		System.out.println("* Time taken: " + timeElapsedDeletion + " ms.");
		System.out.printf("* M=%d, N=%d, ", dictionary.getM(), dictionary.size());
		
		if (dictionary.getClass().equals(HashTableLP.class))
			System.out.printf("LoadFactor(N/M)=%.2f\n", (float)dictionary.size()/(float)dictionary.getM());
		else
			System.out.printf("AverageItemCountPerList(N/M)=%.2f\n", (float)dictionary.size()/(float)dictionary.getM());
		
		Thread.sleep(500);
	}
	
	public static long TestTimeForInsertion(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		//--------------------------------------------------------
		// Summary: Calculates duration between before the put operation and after the put operation.
		// Precondition: "dictionary" is a type of hash table, "words" is a string arraylist
		// Postcondition: Returns elapsed time as type of long
		//--------------------------------------------------------
		
		Instant __start__ = Instant.now();
		
		for (int i = 0; i < 100000; ++i) {
			Utils.FillDictionaryWithValues(words, dictionary);
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}
	
	public static long TestTimeForDeletion(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		//--------------------------------------------------------
		// Summary: Shuffles wordlist. Calculates duration between before the get operation and after the get operation.
		// Precondition: "dictionary" is a type of hash table, "words" is a string arraylist
		// Postcondition: Returns elapsed time as type of long
		//--------------------------------------------------------
		
		// Shuffles the words to simulate real life case.
		Collections.shuffle(words);
		
		Instant __start__ = Instant.now();
		
		// Removes all content from dictionary one by one
		for (String word: words) {
			dictionary.delete(word);
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}

	public static long TestTimeForRetrieval(IHashTable<String, Integer> dictionary, ArrayList<String> words) {
		//--------------------------------------------------------
		// Summary: Shuffles wordlist. Calculates duration between before the delete operation and after the delete operation.
		// Precondition: "dictionary" is a type of hash table, "words" is a string arraylist
		// Postcondition: Returns elapsed time as type of long
		//--------------------------------------------------------
		
		// Shuffles the words to simulate real life case.
		Collections.shuffle(words);
		
		Instant __start__ = Instant.now();
		
		// Retrieve an element in each iteration
		for (int i = 0; i < 50000; ++i) {
			for (String word: words) {
				Integer value = dictionary.get(word);
			}
		}
		
		Instant __finish__ = Instant.now();
		return Duration.between(__start__, __finish__).toMillis();
	}
		
}
