//-----------------------------------------------------
// Title: Utilities
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: This class includes utility/helper methods for task 1.
//-----------------------------------------------------

package task1;

public class Utils {
	
	public static class Number {
		// This class describes a number with its integer value alongside the distance/step value of
		// the operation. The operation is a breadth search algorithm, and this class will be
		// used as a helper. Because tracing of the current step of operation (distance) value is
		// not likely to be possible, even it's stored on the global state or in the outer loop.
		
		int val;
		int dist;
		
		Number(int val, int dist) {
			this.val = val; 
			this.dist = dist;
		}
	}
	
	public static int Parse4DigitsToInt(int[] digits) {
        //--------------------------------------------------------
        // Summary: Converts 4 individual digits to one whole integer representation.
		// index 0 => *10^3 ... index 3 => *10^0
		// Example> in: int[1, 2, 3, 4] => out: 1234
        // Precondition: digits is an integer array (length of 4 expected)
        // Postcondition: returns integer
        //--------------------------------------------------------
		if (digits.length != 4) throw new IllegalArgumentException("Length of 4 is expected for int[] digits");
    	
		return ( digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3] );
	}

	public static int[] ParseIntTo4Digits(int num) {
        //--------------------------------------------------------
        // Summary: Converts an integer number to an integer array with length of 4. Each cell per digit.
		// Example> in: 1234 => out: int[1, 2, 3, 4]
        // Precondition: num is an integer
        // Postcondition: returns integer array
        //--------------------------------------------------------
    	
		int[] digits = new int[4];

		for (int i = 3; i >=0; --i) {
			digits[i] = num % 10;
			num = num / 10;
		}

		return digits;
	}
	
	public static int[] ParseStringTo4Digits(String str) {
        //--------------------------------------------------------
        // Summary: Splits a string by whitespace and tries to convert each
		// string representation of an integer digit to exact integer value
		// for putting inside a integer array. 
		// Example> in: "1 2 3 4" => out: int[1, 2, 3, 4]
        // Precondition: str is a string
        // Postcondition: returns integer array
        //--------------------------------------------------------
    	
		int[] result = new int[4];

		int i = 0;
		for (String rawInt: str.trim().split(" ")) {
			if (i >= 4) throw new IllegalArgumentException("Number of input numbers exceeds expected value");

			result[i] = Integer.parseInt(rawInt);
			++i;
		}

		return result;
	}
	
}
