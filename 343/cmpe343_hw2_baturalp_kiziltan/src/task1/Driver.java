package task1;
import java.io.File;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		int[] source = null;
		int[] target = null;
		int numberOfForbiddens = 0;
		ArrayList<Integer> marked = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter filename: ");
		String fileName = sc.nextLine().trim();
		sc.close();
		try {
			Scanner fileio = new Scanner(new File(fileName));
			source = Utils.ParseStringTo4Digits(fileio.nextLine().trim());

			target = Utils.ParseStringTo4Digits(fileio.nextLine().trim());
			numberOfForbiddens = Integer.parseInt(fileio.nextLine().trim());

			for (int i = 0; i < numberOfForbiddens; ++i) {
				marked.add(Utils.Parse4DigitsToInt(Utils.ParseStringTo4Digits(fileio.nextLine().trim())));
			}

			fileio.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		int result = BreadthFirstSearch.DecryptTheSafe(
				Utils.Parse4DigitsToInt(source), 
				Utils.Parse4DigitsToInt(target), 
				marked);
		System.out.println(result);
		
	}
}
