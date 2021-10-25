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
public class Tester {

	public static void main(String[] args) throws InterruptedException {
		
		Test_1(HashTableLP.class);
		Test_2(HashTableLP.class);
		Test_3(HashTableLP.class, 64);
		System.out.println("---------");
		Test_1(HashTableSC.class);
		Test_2(HashTableSC.class);
		Test_3(HashTableSC.class, 16);
		
		System.out.println("\n-- ALL TESTS SUCCESSFULLY DONE --");
		
	}
	
	static void Test_1(Class _class) throws InterruptedException {
		IHashTable<String, Integer> dict = null;
		
		if (_class.equals(HashTableLP.class)) 		dict = new HashTableLP<>();
		else if (_class.equals(HashTableSC.class)) 	dict = new HashTableSC<>();
		else										assert false : "Not able to instantite the Class: " + _class;
		
		int expectedSize = 3;
		int expectedTableSize = HashTableLP.INITIAL_CAP;
		
		dict.put("A", 1);
		dict.put("B", 1);
		dict.put("C", 1);
		dict.put("D", 1);
		dict.remove("A");
		
		assert expectedSize == dict.size() 
				: dict.getClass() + ": Test-1 > Total key/value count is incorrect";
		
		assert expectedTableSize == dict.getM() 
				: dict.getClass() + ": Test-1 > Table size is incorrect";
		
		System.out.println(dict.getClass() + " : Test-1 : " + "success");
		Thread.sleep(500);
	}
	
	static void Test_2(Class _class) throws InterruptedException {
		IHashTable<String, Integer> dict = null;
		
		if (_class.equals(HashTableLP.class)) 		dict = new HashTableLP<>();
		else if (_class.equals(HashTableSC.class)) 	dict = new HashTableSC<>();
		else										assert false : "Not able to instantite Class: " + _class;
		
		String key = "ABC";
		
		Integer expectedValue = 15;
		int expectedSize = 1;
		int expectedTableSize = HashTableLP.INITIAL_CAP;
		
		for (int i = 1; i <= expectedValue; ++i) {
			if (i == 1) dict.put(key, 1);
			else 		dict.put(key, 1 + dict.get(key));
		}
		
		assert expectedSize == dict.size() 
				: dict.getClass() + ": Test-2 > Total key/value count is incorrect";
		
		assert expectedTableSize == dict.getM() 
				: dict.getClass() + ": Test-2 > Table size is incorrect";
		
		assert expectedValue.equals(dict.get(key)) 
				: dict.getClass() + ": Test-2 > Key 'ABC' doesn't have expected value of " + expectedValue;
	
		System.out.println(dict.getClass() + " : Test-2 : " + "success");
		Thread.sleep(500);
	}
	

	static void Test_3(Class _class, int expectedTableSize) throws InterruptedException {
		IHashTable<String, Integer> dict = null;
		
		if (_class.equals(HashTableLP.class)) 		dict = new HashTableLP<>();
		else if (_class.equals(HashTableSC.class)) 	dict = new HashTableSC<>();
		else										assert false : "Not able to instantite Class: " + _class;
		
		int expectedSize = 26;
		
		for (int c = 65; c < 91; ++c) {
			dict.put(Character.toString(c), 1);
		}
		
		assert expectedSize == dict.size() 
				: dict.getClass() + ": Test-3 > Total key/value count is incorrect";
		
		assert expectedTableSize == dict.getM() 
				: dict.getClass() + ": Test-3 > Table size is incorrect";
	
		System.out.println(dict.getClass() + " : Test-3 : " + "success");
		Thread.sleep(500);
	}
}
