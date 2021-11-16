//-----------------------------------------------------
// Title: Tester
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: This class includes test cases for task 1.
//-----------------------------------------------------

package task1;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

	public static void main(String[] args) throws Exception {
        //--------------------------------------------------------
        // Summary: Executes all tests.
        // Precondition: args is string array
        // Postcondition: test results are printed.
        //--------------------------------------------------------
		
		System.out.println("Task 1 - Tests (4) => \n");
		Execute(Test_Parse4DigitsToInt());
		Execute(Test_ParseIntTo4Digits());
		Execute(Test_ParseStringTo4Digits());
		Execute(Test_BFS());
	}
	
	private static void Execute(boolean RESULT) {
        //--------------------------------------------------------
        // Summary: Wrapper method to execute and interpret results of
		// all other test methods.
        // Precondition: RESULT is a boolean (returned by test methods)
        // Postcondition: prints success or failed
        //--------------------------------------------------------
		
		System.out.println(RESULT 
				? ": success\n" 
				: ": failed\n");
	}
	 
	private static boolean Test_Parse4DigitsToInt() {
        //--------------------------------------------------------
        // Summary: Tests Parse4DigitsToInt() method under Utils class
		// by giving example input and comparing by expected result.
        // Precondition: 
        // Postcondition: 
        //--------------------------------------------------------
		
		int[] in = new int[] { 1, 9, 9, 1 };
		int expected = 1991;
		
		int out = Utils.Parse4DigitsToInt(in);
		
		System.out.printf("expected: %d, out: %d\n", expected, out);
		System.out.print("Parse4DigitsToInt");
		return expected == out;
	}
	
	private static boolean Test_ParseIntTo4Digits() {
        //--------------------------------------------------------
        // Summary: Tests ParseIntTo4Digits() method under Utils class
		// by giving example input and comparing by expected result.
        // Precondition: 
        // Postcondition: 
        //--------------------------------------------------------
		
		int in = 1947;
		int[] expected = new int[] { 1, 9, 4, 7 };
		
		int[] out = Utils.ParseIntTo4Digits(in);
		
		System.out.printf("expected: %s, out: %s\n", 
				Arrays.toString(expected),
				Arrays.toString(out));
		System.out.print("ParseIntTo4Digits");
		return Arrays.compare(expected, out) == 0;
	}
	
	private static boolean Test_ParseStringTo4Digits() throws Exception {
        //--------------------------------------------------------
        // Summary: Tests ParseStringTo4Digits() method under Utils class
		// by giving example input and comparing by expected result.
        // Precondition: 
        // Postcondition: 
        //--------------------------------------------------------
		
		String in = "4 4 4 4";
		int[] expected = new int[] { 4, 4, 4, 4 };
		
		int[] out = Utils.ParseStringTo4Digits(in);
		
		System.out.printf("expected: %s, out: %s\n", 
				Arrays.toString(expected),
				Arrays.toString(out));
		System.out.print("ParseStringTo4Digits");
		return Arrays.compare(expected, out) == 0;
	}
	
	private static boolean Test_BFS() {
        //--------------------------------------------------------
        // Summary: Mimics the driver class. Gives an example source,
		// target and forbidden numbers, then executes BFS algorithm.
		// Lastly compares for expected result by exact test output.
        // Precondition: - 
        // Postcondition: test result is printed
        //--------------------------------------------------------
		
		int[] source = new int[] { 1, 0, 0, 0 };
		int[] target = new int[] { 4, 0, 0, 1 };
		ArrayList<Integer> marked = new ArrayList<>();
		marked.add(2000);
		marked.add(2001);
		marked.add(3000);
		marked.add(3001);
		
		int expected = 6;
		int out = BreadthFirstSearch.DecryptTheSafe(
				Utils.Parse4DigitsToInt(source), 
				Utils.Parse4DigitsToInt(target), 
				marked);
		
		System.out.printf("expected: %d, out: %d\n", expected, out);
		System.out.print("BFS");
		return expected == out;
	}
	
}
