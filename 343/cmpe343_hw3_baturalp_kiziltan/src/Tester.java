//-----------------------------------------------------
// Title: Tester
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class contains testing utilities.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tester {
	
	private static int testGroupCount = 0;
	private static int testCaseCount = 0;
	private static int passed = 0;
	
	public static void main(String[] args) {
		//--------------------------------------------------------
		// Summary: Entry point of Tester class. Creates a new test, executes it, and prints results
		// Precondition: args is string array
		// Postcondition: test report is printed with details
		//--------------------------------------------------------
		try {
			ExecuteNewTest(
					1, 
					new Scanner(new File("testinput1.txt")),
					"[phase1, phase2, phase3, project1, presentation]",
					"0: 1 \n1: 2 \n2: \n3: 2 \n4: 2 \n",
					5,
					new Calendar4(
						new int[] { 4 },
						new int[] { 3, 0 },
						new int[] { 1 },
						new int[] { 2 }
					)
			);
		} catch (FileNotFoundException e) {
			System.err.println("Failed to open the file \"testinput1.txt\"");
		}
		
		System.out.println("==============================");
		System.out.println("  ##### RESULTS #####");
		System.out.println("* Executed " + testGroupCount + " different test(s).");
		System.out.println("* There were total number of " + testCaseCount + " test cases.");
		System.out.printf("✔ Passed \"%d\" of %d cases.\n", passed, testCaseCount);
	}
	
	private static void ExecuteNewTest(
		int ID,
		Scanner fileIO,
		String expectedKeys,
		String expectedDependencies,
		int expectedTaskCount,
		Calendar4 expectedCalendar
	) {
		//--------------------------------------------------------
		// Summary: creates a new test and runs comparison methods
		// Precondition: ID, expectedTaskCount are integers and 
		// fileIO, expectedKeys, expectedDependencies are strings and
		// expectedCalendar is Calendar4 instance
		// Postcondition: prints comparison results with some details
		//--------------------------------------------------------
		
		++testGroupCount;
		System.out.println("### TEST " + ID + " ###\n");
		ProjectScheduler scheduler = null;
		try {
			scheduler = new ProjectScheduler(fileIO);
		} catch (Exception e) {
			System.out.println("Failed to initialize Project Scheduler. Check correctness of input file.");
			System.out.println("REASON: " + e.toString());
		}
		
		Run(checkTaskKeys		(scheduler.getTaskKeys().toString(), expectedKeys));
		Run(checkDependencyList (scheduler.getTasks().toString(), 	 expectedDependencies));
		Run(checkNumberOfTasks 	(scheduler.getNumberOfTasks(), 		 expectedTaskCount));
		Run(checkCalendar 		(scheduler.getCalendar(), 			 expectedCalendar));
	}
	
	private static void Run(boolean result) {
		//--------------------------------------------------------
		// Summary: wrapper method for the checker functions
		// Precondition: result is boolean
		// Postcondition: prints success or failure
		//--------------------------------------------------------
		++testCaseCount;
		if (result == true) ++passed;
		System.out.println((result)
			? "\t--> successful ✔\n"
			: "\t--> failed ✘\n"
		);
	}
	
	private static boolean Check(int in, int expected) {
		//--------------------------------------------------------
		// Summary: compares two integers.
		// Precondition: in, expected are integers
		// Postcondition: returns comparison of two integers.
		//--------------------------------------------------------
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in == expected;
	}
	
	private static boolean Check(Object in, Object expected) {
		//--------------------------------------------------------
		// Summary: compares two objects.
		// Precondition: in, expected are objects.
		// Postcondition:  returns comparison of two objects.
		//--------------------------------------------------------
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in.equals(expected);
	}
	
	private static boolean checkTaskKeys(String in, String expected) {
		//--------------------------------------------------------
		// Summary: compares two variables for "TaskKeys"
		// Precondition: in, expected are strings
		// Postcondition: returns comparison result as boolean
		//--------------------------------------------------------
		System.out.println("* Test 'Check Task Keys': ");
		return Check(in, expected);
	}
	
	private static boolean checkDependencyList(String in, String expected) {
		//--------------------------------------------------------
		// Summary: compares two variables for "DependencyList"
		// Precondition: in, expected are strings
		// Postcondition: returns comparison result as boolean
		//--------------------------------------------------------
		System.out.println("* Test 'Check Dependency List': ");
		return Check(in, expected);
	}
	
	private static boolean checkNumberOfTasks(int in, int expected) {
		//--------------------------------------------------------
		// Summary: compares two variables for "NumberOfTasks"
		// Precondition: in, expected are integers
		// Postcondition: returns comparison result as boolean
		//--------------------------------------------------------
		System.out.println("* Test 'Check Number of Tasks': ");
		return Check(in, expected);
	}
	
	private static boolean checkCalendar(Calendar4 in, Calendar4 expected) {
		//--------------------------------------------------------
		// Summary: compares two variables for "Calendar"
		// Precondition: in, expected are Calendar4 instances
		// Postcondition: returns comparison result as boolean
		//--------------------------------------------------------
		System.out.println("* Test 'Check Calendar': ");
		return Check(in, expected);
	}
	
}
