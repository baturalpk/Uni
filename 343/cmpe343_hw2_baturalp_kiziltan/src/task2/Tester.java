//-----------------------------------------------------
// Title: Tester
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: This class includes test cases for task 2.
//-----------------------------------------------------

package task2;

import java.io.*;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) throws Exception {
        //--------------------------------------------------------
        // Summary: Reads pre-built maze file. Removes contents of
    	// test output file that is left from previous runs.
    	// Pipes standard output to the test output file.
    	// Searches for cycles using DFS algorithm.
    	// Resets standard output and runs internal "checkTestResult" method
    	// to verift correctness of test results.
        // Precondition: args is String array
        // Postcondition: prints test results.
        //--------------------------------------------------------
    	
		Maze maze = Utils.ReadMazeFile("task2_test_input.txt");
		
		PrintStream defaultOut = System.out;
		FileOutputStream outputFile = new FileOutputStream("task2_test_output.txt");
		outputFile.write("".getBytes());
		outputFile.flush();
		
		System.setOut(new PrintStream(outputFile));
		
		Utils.SearchForCycles(maze);
		
		outputFile.close();
		System.setOut(defaultOut);
		
		checkTestResult();
		
    }
    
    private static void checkTestResult() throws Exception {
        //--------------------------------------------------------
        // Summary: Defines expected output string.
    	// Reads content of the test output file after execution of the program.
    	// Compares both expected and exact results to verify whether they are correct or not.
        // Precondition: -
        // Postcondition: prints test results.
        //--------------------------------------------------------
    	
    	String expected = "7 Cycles; the longest has length 20.";
    	Scanner out = new Scanner(new File("task2_test_output.txt"));
    	String result = out.nextLine();
    	
    	System.out.println(
    			"Result: \t" + result + "\n" 
    					+
    			"Expected: \t" + expected + "\n" 
    					+
    			(result.equals(expected) ? "- success -" : "- failed -")
    		);
    	
    	out.close();
    	
    }

}
