//-----------------------------------------------------
// Title: Benchmark class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class measures and reports the Task 2 application in
// terms of performance (time complexity).
//-----------------------------------------------------

package shared;

public class Tester {
    public static void main(String[] args) throws Exception {
        //--------------------------------------------------------
        // Summary: The entry-point for Tester class. Executes all
        // individual unit-test methods of each significant data
        // and utility class.
        // (These classes are:
        //  - EdgeWeightedGraph
        //  - LazyPrimMST
        //  - MinPQ
        //  - DijkstraUndirectedSP
        //  - IndexMinPQ
        //  - Utility for Part 1
        //  - Utility for Part 2)
        // Precondition: args --> String array
        // Postcondition: Test results are printed on console.
        //--------------------------------------------------------

        Execute(task_1.Utils.class.getName(), task_1.Utils.test());
        Execute(task_2.Utils.class.getName(), task_2.Utils.test());
    }

    private static void Execute(String testName, boolean RESULT) {
        //--------------------------------------------------------
        // Summary: Wrapper method to execute and print result of a test.
        // Precondition: testName --> String, RESULT --> boolean(returned by test methods)
        // Postcondition: prints success or failed with test name
        //--------------------------------------------------------

        System.out.print("=== " + testName);
        System.out.println(RESULT
                ? ": success ✔\n"
                : ": failed ✘\n");
    }
}
