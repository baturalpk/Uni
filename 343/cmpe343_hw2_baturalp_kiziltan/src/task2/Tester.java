package task2;

import java.io.*;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) throws Exception {
    	
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
