//-----------------------------------------------------
// Title: 
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 4
// Description: This class 
//-----------------------------------------------------

package task_2;

public class City {
	private final double x;
	private final double y;
	private final String name;
	
	public City(double x, double y, String name) {
    	//--------------------------------------------------------
    	// Summary: 
    	// Precondition: 
    	// Postcondition: 
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
