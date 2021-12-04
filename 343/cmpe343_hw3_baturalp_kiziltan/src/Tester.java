
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tester {
	
	private static int testGroupCount = 0;
	private static int testCaseCount = 0;
	private static int passed = 0;
	
	private static void ExecuteNewTest(
		int ID,
		Scanner fileIO,
		String expectedKeys,
		String expectedDependencies,
		int expectedTaskCount,
		Calendar4 expectedCalendar
	) {
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
		++testCaseCount;
		if (result == true) ++passed;
		System.out.println((result)
			? "\t--> successful ✔\n"
			: "\t--> failed ✘\n"
		);
	}
	
	private static boolean Check(int in, int expected) {
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in == expected;
	}
	
	private static boolean Check(Object in, Object expected) {
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in.equals(expected);
	}
	
	private static boolean checkTaskKeys(String in, String expected) {
		System.out.println("* Test 'Check Task Keys': ");
		return Check(in, expected);
	}
	
	private static boolean checkDependencyList(String in, String expected) {
		System.out.println("* Test 'Check Dependency List': ");
		return Check(in, expected);
	}
	
	private static boolean checkNumberOfTasks(int in, int expected) {
		System.out.println("* Test 'Check Number of Tasks': ");
		return Check(in, expected);
	}
	
	private static boolean checkCalendar(Calendar4 in, Calendar4 expected) {
		System.out.println("* Test 'Check Calendar': ");
		return Check(in, expected);
	}
	
	public static void main(String[] args) {
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
	
}
