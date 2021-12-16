//-----------------------------------------------------
// Title: City class
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class defines a city object with its coordinates and name.
//-----------------------------------------------------

package task_2;

public class City {
	private final double x;
	private final double y;
	private final String name;
	
	public City(double x, double y, String name) {
    	//--------------------------------------------------------
    	// Summary: Initializes the instance variables with the given values.
    	// Precondition: x and y --> double, name --> String
    	// Postcondition: the member variables are initialized.
    	//--------------------------------------------------------
    	
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	// GETTERS
	public double X() 	 { return x; 	}
	public double Y() 	 { return y; 	}
	public String Name() { return name; }
}
