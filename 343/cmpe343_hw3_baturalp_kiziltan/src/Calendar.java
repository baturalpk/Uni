//-----------------------------------------------------
// Title: Calendar
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class describes a calendar.
//-----------------------------------------------------

import java.util.*;

public class Calendar {
	
	private ArrayList<ArrayList<Integer>> weeks;
	private int size;
	
	public Calendar(int weekCount) {
		//--------------------------------------------------------
		// Summary: Default constructor that initializes member variables
		// Precondition: -
		// Postcondition: weeks are initialized.
		//--------------------------------------------------------
		size = 0;
		weeks = new ArrayList<>();
		
		for (int i = 0; i < weekCount; ++i) {
			weeks.add(new ArrayList<Integer>());
		}
	}
	
	public Calendar(int w[][]) {
		//--------------------------------------------------------
		// Summary: Extended constructor that initializes member variables
		// with given values.
		// Precondition: w[][] is 2d integer array
		// Postcondition: weeks are initialized with values.
		//--------------------------------------------------------
		this(w.length);
		
		for (int i = 0; i < w.length; ++i) {
			for (int value: w[i]) {
				weeks.get(i).add(value);
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		//--------------------------------------------------------
		// Overridden version of "equals" method. Compares contents of all weeks.
		//--------------------------------------------------------
		if (o == this) return true;
		else {
			Calendar other = (Calendar) o;
			if (this.size() != other.size() && weeks.size() != other.weeks.size())
				return false;
			
			for (int i = 0; i < weeks.size(); ++i) {
				if (! weeks.get(i).equals(other.weeks.get(i)))
					return false;
			}
			return true;
		}
	}
	
	public void add(int value, int weekNo) {
		//--------------------------------------------------------
		// Summary: adds a task id into a particular week
		// Precondition: value, weekNo are integers
		// Postcondition: {value} is added into week {weekNo-1}
		//--------------------------------------------------------
		weeks.get(weekNo - 1).add(value);
		++size;
	}
	
	public boolean contains(int v) {
		//--------------------------------------------------------
		// Summary: checks given task number is added in calendar or not
		// Precondition: v is integer
		// Postcondition: return boolean
		//--------------------------------------------------------
		
		for (ArrayList<Integer> week: weeks) {
			if (week.contains(v))
				return true;
		}
		
		return false;
	}
	
	public int size() {
		//--------------------------------------------------------
		// Summary: Returns total size of Calendar object
		// Precondition: -
		// Postcondition: returns total size
		//--------------------------------------------------------
		return size;
	}
	
	public ArrayList<Integer> getWeek(int weekNo) {
		//--------------------------------------------------------
		// Summary: Retrieves proper week arraylist according to given week number
		// Precondition: week is integer
		// Postcondition: return week as integer arraylist
		//--------------------------------------------------------
		return weeks.get(weekNo - 1);
	}
	
	public int getWeekNoOfTheTask(int v) {
		//--------------------------------------------------------
		// Summary: Searches given task number in a week and returns week number
		// Precondition: v is integer
		// Postcondition: return week number
		//--------------------------------------------------------
		
		for (int i = 0; i < weeks.size(); ++i) {
			if (weeks.get(i).contains(v)) {
				return (i + 1);
			}
		}
		
		return -1;
	}
	
	public ArrayList<ArrayList<Integer>> getAllWeeks() {
		//--------------------------------------------------------
		// Getter for "weeks".
		//--------------------------------------------------------
		return this.weeks;
	}
	
}
