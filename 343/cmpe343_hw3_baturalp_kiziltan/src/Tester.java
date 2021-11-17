
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
		String expectedCalendar,
		String expectedScheduleList,
		String task1_1,
		String task1_2,
		int expectedOrderResult_1,
		String task2_1,
		String task2_2,
		int expectedOrderResult_2,
		String task3_1,
		String task3_2,
		int expectedOrderResult_3
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
		
		Run(checkTaskKeys			(scheduler.getTaskKeys().toString(), 		expectedKeys));
		Run(checkDependencyList 	(scheduler.getTasks().toString(), 			expectedDependencies));
		Run(checkNumberOfTasks 		(scheduler.getNumberOfTasks(), 				expectedTaskCount));
		Run(checkCalendar 			(scheduler.getCalendar().toString(), 		expectedCalendar));
		Run(checkListMethod 		(scheduler.List(), 							expectedScheduleList));
		Run(checkCheckOrderMethod 	(scheduler.CheckOrder(task1_1, task1_2), 	expectedOrderResult_1));
		Run(checkCheckOrderMethod 	(scheduler.CheckOrder(task2_1, task2_2), 	expectedOrderResult_2));
		Run(checkCheckOrderMethod 	(scheduler.CheckOrder(task3_1, task3_2), 	expectedOrderResult_3));
	}
	
	private static void Run(boolean result) {
		++testCaseCount;
		if (result == true) ++passed;
		System.out.println((result)
			? "\t--> successful ✔\n"
			: "\t--> failed ✘\n"
		);
	}
	
	private static boolean Check(String in, String expected) {
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in.equals(expected);
	}
	
	private static boolean Check(int in, int expected) {
		System.out.println("Result: \n" + in);
		System.out.println("Expected: \n" + expected);
		return in == expected;
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
	
	private static boolean checkCalendar(String in, String expected) {
		System.out.println("* Test 'Check Calendar': ");
		return Check(in, expected);
	}
	
	private static boolean checkListMethod(String in, String expected) {
		System.out.println("* Test 'Check List Method': ");
		return Check(in, expected);
	}
	
	private static boolean checkCheckOrderMethod(int in, int expected) {
		System.out.println("* Test 'Check Check Order Method': ");
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
					"[phase1, phase2, project1, presentation, phase3]",
					"Week 1: phase1 phase2 project1 \n" +
					"Week 2: presentation phase3 ",
					"phase1",
					"phase3",
					1,
					"presentation",
					"project1",
					-1,
					"phase3",
					"presentation",
					0
			);
		} catch (FileNotFoundException e) {
			System.err.println("Failed to open the file \"testinput1.txt\"");
		}
		System.out.println("=====================================");
		try {
			ExecuteNewTest(
					2, 
					new Scanner(new File("testinput2.txt")),
					"[project1, project2, project3, demo1, demo2, phase1, phase2, phase3, phase4, reporting, presentation, design]",
					"0: 7 \n1: 6 \n2: 9 \n3: 7 \n4: 8 \n5: 6 \n6: 7 \n7: 8 \n8: \n9: \n10: \n11: 5 \n",
					12,
					"[project1, project2, project3, demo1, demo2, design, phase1, phase2, phase3, phase4, reporting, presentation]",
					"Week 1: project1 project2 project3 \n" +
					"Week 2: demo1 demo2 design \n" +
					"Week 3: phase1 phase2 phase3 \n" +
					"Week 4: phase4 reporting presentation ",
					"phase4",
					"presentation",
					0,
					"phase2",
					"demo2",
					-1,
					"design",
					"reporting",
					1
			);
		} catch (FileNotFoundException e) {
			System.err.println("Failed to open the file \"testinput2.txt\"");
		}
		
		System.out.println("==============================");
		System.out.println("  ##### RESULTS #####");
		System.out.println("* Executed " + testGroupCount + " different tests.");
		System.out.println("* There were total number of " + testCaseCount + " test cases.");
		System.out.printf("✔ Passed %d of %d cases.\n", passed, testCaseCount);
	}
	
}
