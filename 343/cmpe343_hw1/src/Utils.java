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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

	public static ArrayList<String> ReadFromArguments(String[] args) {
		return new ArrayList<>(Arrays.asList(args));
	}
	
	public static ArrayList<String> ReadInputFileContent(String fileName) {
		return ReadInputFileContent(new String[]{ fileName });
	}
	
	public static ArrayList<String> ReadInputFileContent(String[] args) {
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
		
		MostRecurringWords mostRecurrings = new MostRecurringWords();
		
		// Iterate over Linear Probing Hash Table
		for (int index = 0; index < lpht.getM(); ++index) {
			String currentKey = lpht.getKeysAs(String.class)[index];
			Integer currentValue = lpht.getValuesAs(Integer.class)[index];
			
			if (currentValue == null) continue;
			
			if (currentValue > mostRecurrings.first.value) {
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
			else if (currentValue < mostRecurrings.first.value && 
					currentValue > mostRecurrings.second.value) {
				//
				mostRecurrings.third.key = mostRecurrings.second.key;
				mostRecurrings.third.value = mostRecurrings.second.value;
				mostRecurrings.third.LPIndex = mostRecurrings.second.LPIndex;
				
				mostRecurrings.second.key = currentKey;
				mostRecurrings.second.value = currentValue;
				mostRecurrings.second.LPIndex = index;
				

			}
			else if (currentValue < mostRecurrings.second.value && 
					currentValue > mostRecurrings.third.value) {
				//
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
