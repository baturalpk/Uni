package task1;

public class Utils {
	
	public static class Number {
		// This class describes a number with its integer value alongside the distance/step value of
		// the operation. The operation defines a breadth search algorithm, and this class will be
		// used as a helper. Because tracing of the current step of operation (distance) value is
		// not likely to be possible, while it's stored in the global state or the outer loop.
		
		int val;
		int dist;
		
		Number(int val, int dist) {
			this.val = val; 
			this.dist = dist;
		}
	}
	
	public static int Parse4DigitsToInt(int[] digits) {
		return ( digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3] );
	}

	public static int[] ParseIntTo4Digits(int num) {
		int[] digits = new int[4];

		for (int i = 3; i >=0; --i) {
			digits[i] = num % 10;
			num = num / 10;
		}

		return digits;
	}
	
	public static int[] ParseStringTo4Digits(String str) throws Exception {
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
