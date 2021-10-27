//-----------------------------------------------------
// Title: Utilities - helper class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class contains utility functions to read input contents, to find most recurring 3 words, 
//				and to insert whole word list content into the given dictionary object.
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {
	
	private static class MostRecurringWords {
		
		private static class Word {
			String key = "";
			Integer value = -1;
			Integer LPIndex = -1;
			Integer SCNodeIndex = -1;
		}
		
		Word first;
		Word second;
		Word third;
		
		public MostRecurringWords() {
			first = new Word();
			second = new Word();
			third = new Word();
		}
	
	}
	
	public static ArrayList<String> ReadFromScanner() {
		//--------------------------------------------------------
		// Summary: Reads one line of user input. Then split them by whitespace and add into arraylist.
		// NOTE: This line must contain all words separated with whitespace.
		// Precondition: -
		// Postcondition: Returns a string arraylist of words which was parsed one by one.
		//--------------------------------------------------------
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> content = new ArrayList<>();
		
		for (String word: sc.nextLine().trim().split(" ")) {
			if (! word.isBlank())
				content.add(word);
		}
		
		sc.close();
		return content;
	}

	public static ArrayList<String> ReadFromArguments(String[] args) {
		//--------------------------------------------------------
		// Summary: Reads from command-line arguments. And convert it to string arraylist.
		// Precondition: args is array of strings.
		// Postcondition: args is converted to an arraylist, returned from function.
		//--------------------------------------------------------
		
		return new ArrayList<>(Arrays.asList(args));
	}
	
	public static ArrayList<String> ReadInputFileContent(String fileName) {
		//--------------------------------------------------------
		// Summary: Overloaded version of ReadInputFileContent(String[]).
		// Takes a filename, then passes it to other overloaded method using a
		// a one-liner array initialization method for returning an arraylist
		// which contains contents of desired text file.
		// Precondition: fileName is a string
		// Postcondition: filename is passed into ReadInputFileContent(String[]) method, and
		// returns a string arraylist.
		//--------------------------------------------------------
		
		return ReadInputFileContent(new String[]{ fileName });
	}
	
	public static ArrayList<String> ReadInputFileContent(String[] args) {
		//--------------------------------------------------------
		// Summary: Able to take name of text file from command-line arguments.
		// If no argument is provided, then uses "sampleinput1.txt" file as default.
		// If the desired file exists in the project's root folder, reads it using a
		// scanner and returns contents as a string arraylist.
		// Precondition: args is array of strings.
		// Postcondition: Returns a string arraylist.
		//--------------------------------------------------------
		
		String fileName = "sampleinput1.txt"; // Default
		
		if (args.length > 0) {
			String fn = args[0];
			if (fn.endsWith(".txt")) fileName = fn;
			else					 fileName = fn + ".txt";
		}
		
		File file = new File(fileName);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> content = new ArrayList<>();
		
		while (sc.hasNextLine()) {
			String[] wordsAtTheLine = sc.nextLine().trim().split(" ");
			
			for (String word: wordsAtTheLine) {
				if (! word.isBlank())
					content.add(word);
			}
		}
		
		sc.close();
		return content;
	}
	
	public static void FillDictionaryWithValues(ArrayList<String> words, IHashTable<String, Integer> dictionary) {
		//--------------------------------------------------------
		// Summary: Iterate over words, and tries to put each word into dictionary.
		// If any word's already exists in dictionary, then increments its occurrence by one.
		// Precondition: words is string arraylist, dictionary is a type of hash table
		// Postcondition: dictionary is filled with values.
		//--------------------------------------------------------
		
			for (String word: words) {
				Integer existingValue = dictionary.get(word);
				if (existingValue == null) {
					dictionary.put(word, 1);
				} else {
					dictionary.put(word, existingValue + 1);
				}
			}
		}
		
	public static void FindAndPrintMostRecurring3Keys(HashTableLP<String, Integer> lpht, HashTableSC<String, Integer> scht) {
		//--------------------------------------------------------
		// Summary: Uses an internal class which is called as MostRecurringWords. It stores 
		// top 3 keys with their number of occurrences, their separate chaining node indices, and 
		// their linear probing table indices. At first, the algorithm iterates over linear probing hash table
		// and decides 1st, 2nd, and 3rd biggest values. Then iterates over separate chaining hash table
		// to find corresponding node indices.
		// Precondition: lpht is Linear Probing Hash Table instance, scht is Separate Chaining Hash Table instance.
		// Postcondition: Prints out most recurring 3 keys' occurrences, their node indices, and their array indices.
		//--------------------------------------------------------
		
		MostRecurringWords mostRecurrings = new MostRecurringWords();
		
		// Iterate over Linear Probing Hash Table
		for (int index = 0; index < lpht.getM(); ++index) {
			String currentKey = lpht.getKeysAs(String.class)[index];
			Integer currentValue = lpht.getValuesAs(Integer.class)[index];
			
			if (currentValue == null) continue;
			
			
			if (currentValue >= mostRecurrings.first.value) {
				// Set old second as the third.
				mostRecurrings.third.key = mostRecurrings.second.key;
				mostRecurrings.third.value = mostRecurrings.second.value;
				mostRecurrings.third.LPIndex = mostRecurrings.second.LPIndex;
				
				// Set old first as the second.
				mostRecurrings.second.key = mostRecurrings.first.key;
				mostRecurrings.second.value = mostRecurrings.first.value;
				mostRecurrings.second.LPIndex = mostRecurrings.first.LPIndex;

				
				// Set current word as the first.
				mostRecurrings.first.key = currentKey;
				mostRecurrings.first.value = currentValue;
				mostRecurrings.first.LPIndex = index;
			}
			else if (currentValue <= mostRecurrings.first.value && 
					currentValue >= mostRecurrings.second.value) {
				
				// Set old second as the third.
				mostRecurrings.third.key = mostRecurrings.second.key;
				mostRecurrings.third.value = mostRecurrings.second.value;
				mostRecurrings.third.LPIndex = mostRecurrings.second.LPIndex;
				
				// Set current word as the second.
				mostRecurrings.second.key = currentKey;
				mostRecurrings.second.value = currentValue;
				mostRecurrings.second.LPIndex = index;
				

			}
			else if (currentValue <= mostRecurrings.second.value && 
					currentValue >= mostRecurrings.third.value) {
				
				// Set current word as the third.
				mostRecurrings.third.key = currentKey;
				mostRecurrings.third.value = currentValue;
				mostRecurrings.third.LPIndex = index;
			}
		}
		
		// Iterate over Separate Chaining Hash Table
		for (int i = 0; i < scht.getM(); ++i) {
			HashTableSC.Node currentChain = scht.getChain(i);
			int nodeIndex = 0;
			
			for (HashTableSC.Node x = currentChain; x != null; x = x.next) {
				if (x.key.equals(mostRecurrings.first.key)) {
					mostRecurrings.first.SCNodeIndex = nodeIndex;
				}

				if (x.key.equals(mostRecurrings.second.key)) {
					mostRecurrings.second.SCNodeIndex = nodeIndex;
				}

				if (x.key.equals(mostRecurrings.third.key)) {
					mostRecurrings.third.SCNodeIndex = nodeIndex;
				}
				
				++nodeIndex;
			}
		}
		
		System.out.printf("%s %d %d %d\n%s %d %d %d\n%s %d %d %d\n", 
			mostRecurrings.first.key, mostRecurrings.first.LPIndex, mostRecurrings.first.SCNodeIndex, mostRecurrings.first.value,
			mostRecurrings.second.key, mostRecurrings.second.LPIndex, mostRecurrings.second.SCNodeIndex, mostRecurrings.second.value,
			mostRecurrings.third.key, mostRecurrings.third.LPIndex, mostRecurrings.third.SCNodeIndex, mostRecurrings.third.value);
		
	}
		
}
