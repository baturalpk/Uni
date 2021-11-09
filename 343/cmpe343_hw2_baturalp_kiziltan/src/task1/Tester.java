package task1;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

	public static void main(String[] args) throws Exception {
		System.out.println("Task 1 - Tests (4) => \n");
		Execute(Test_Parse4DigitsToInt());
		Execute(Test_ParseIntTo4Digits());
		Execute(Test_ParseStringTo4Digits());
		Execute(Test_BFS());
	}
	
	public static void Execute(boolean RESULT) {
		System.out.println(RESULT 
				? ": success\n" 
				: ": failed\n");
	}
	 
	public static boolean Test_Parse4DigitsToInt() {
		int[] in = new int[] { 1, 9, 9, 1 };
		int expected = 1991;
		
		int out = Utils.Parse4DigitsToInt(in);
		
		System.out.printf("expected: %d, out: %d\n", expected, out);
		System.out.print("Parse4DigitsToInt");
		return expected == out;
	}
	
	public static boolean Test_ParseIntTo4Digits() {
		int in = 1947;
		int[] expected = new int[] { 1, 9, 4, 7 };
		
		int[] out = Utils.ParseIntTo4Digits(in);
		
		System.out.printf("expected: %s, out: %s\n", 
				Arrays.toString(expected),
				Arrays.toString(out));
		System.out.print("ParseIntTo4Digits");
		return Arrays.compare(expected, out) == 0;
	}
	
	public static boolean Test_ParseStringTo4Digits() throws Exception {
		String in = "4 4 4 4";
		int[] expected = new int[] { 4, 4, 4, 4 };
		
		int[] out = Utils.ParseStringTo4Digits(in);
		
		System.out.printf("expected: %s, out: %s\n", 
				Arrays.toString(expected),
				Arrays.toString(out));
		System.out.print("ParseStringTo4Digits");
		return Arrays.compare(expected, out) == 0;
	}
	
	public static boolean Test_BFS() {
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
