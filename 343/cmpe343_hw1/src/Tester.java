//-----------------------------------------------------
// Title: Tester - test class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class contains 3 test functions, and a "main" method to execute all available tests. 
//				These tests are used to validate whether implementations of both linear probing & separate
//				chaining hash tables are correct or not on a most basic sense.
//-----------------------------------------------------
import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) throws InterruptedException {
		//--------------------------------------------------------
		// Summary: Entry point of Executes all tests respectively.
		// Precondition: args is string array.
		// Postcondition: Prints test results to console.
		//--------------------------------------------------------
		
		Test_1();
		System.out.println("--------\n");
		Test_2(HashTableLP.class, HashTableLP.INITIAL_CAP / 2);
		Test_3(HashTableLP.class, 64);
		System.out.println("---------");
		Test_2(HashTableSC.class, HashTableLP.INITIAL_CAP);
		Test_3(HashTableSC.class, 16);
		
		System.out.println("\n-- ALL TESTS SUCCESSFULLY DONE --");
		
	}
	
	static void Test_1() throws InterruptedException {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		IHashTable<String, Integer> dictLP = new HashTableLP<>();
		IHashTable<String, Integer> dictSC = new HashTableSC<>();
		
		System.out.println("Test-1 for both " + dictLP.getClass() + " & " + dictSC.getClass() + ":");
		
		// ---
		System.out.println("Insert inputs as a one line (each word must be separated with whitespace):");
		ArrayList<String> words = Utils.ReadFromScanner();
		
		Utils.FillDictionaryWithValues(words, dictLP);
		Utils.FillDictionaryWithValues(words, dictSC);
		System.out.printf("Table sizes for linear probing=%d & separate chaining=%d.\n", dictLP.getM(), dictSC.getM());
		System.out.printf("Total item count for linear probing=%d & separate chaining=%d.\n", dictLP.size(), dictSC.size());
		System.out.println("Most recurring 3 keys: (note> input must contain at least 3 distinct/unique words for better testability)");
		Utils.FindAndPrintMostRecurring3Keys((HashTableLP<String, Integer>) dictLP, (HashTableSC<String, Integer>) dictSC);
		
		// ---
		System.out.println("*** done, ! check results for your expected output values !");
		Thread.sleep(500);
	}
	
	static void Test_2(Class _class, int expectedM) throws InterruptedException {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		IHashTable<String, Integer> dict = null;
		
		if (_class.equals(HashTableLP.class)) 		dict = new HashTableLP<>();
		else if (_class.equals(HashTableSC.class)) 	dict = new HashTableSC<>();
		else										assert false : "Not able to instantiate Class: " + _class;
		
		System.out.println("Test-2 for " + dict.getClass() + ":");
		
		// ---
		String key1 = "ABC";
		String key2 = "DEF";
		String key3 = "GHI";
		
		Integer expectedValue = 15;
		int expectedSize = 2;
		int expectedTableSize = expectedM;
		
		for (int i = 1; i <= expectedValue; ++i) {
			if (i == 1) dict.put(key1, 1);
			else 		dict.put(key1, 1 + dict.get(key1));
		}
		
		dict.put(key2, 1);
		dict.put(key3, 1);
		
		dict.delete(key2);
		// ---
		
		System.out.printf("EXPECTED:\n*N=%d *M=%d *ValueFor key1=%d\n", expectedSize, expectedTableSize, expectedValue);
		System.out.printf("RESULT:\n*N=%d *M=%d *ValueFor key1=%d\n", dict.size(), dict.getM(), dict.get(key1));

		
		assert expectedSize == dict.size() 
				: dict.getClass() + ": Test-2 > Total key/value count is incorrect";
		
		assert expectedTableSize == dict.getM() 
				: dict.getClass() + ": Test-2 > Table size is incorrect";
		
		assert expectedValue.equals(dict.get(key1)) 
				: dict.getClass() + ": Test-2 > Key 'ABC' doesn't have expected value of " + expectedValue;
	
		System.out.println("success\n");
		Thread.sleep(500);
	}
	

	static void Test_3(Class _class, int expectedTableSize) throws InterruptedException {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		IHashTable<String, Integer> dict = null;
		
		if (_class.equals(HashTableLP.class)) 		dict = new HashTableLP<>();
		else if (_class.equals(HashTableSC.class)) 	dict = new HashTableSC<>();
		else										assert false : "Not able to instantiate Class: " + _class;
		
		System.out.println("Test-3 for " + dict.getClass() + ":");
		
		// ---
		int expectedSize = 26;
		
		for (int c = 65; c < 91; ++c) {
			dict.put(Character.toString(c), 1);
		}
		// ---
		System.out.printf("EXPECTED:\n*N=%d *M=%d\n", expectedSize, expectedTableSize);
		System.out.printf("RESULT:\n*N=%d *M=%d\n", dict.size(), dict.getM());
		
		assert expectedSize == dict.size() 
				: dict.getClass() + ": Test-3 > Total key/value count is incorrect";
		
		assert expectedTableSize == dict.getM() 
				: dict.getClass() + ": Test-3 > Table size is incorrect";
	
		System.out.println("success\n");
		Thread.sleep(500);
	}
	
}
