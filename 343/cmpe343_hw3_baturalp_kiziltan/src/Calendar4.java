import java.util.*;

public class Calendar4 {
	private ArrayList<Integer> week1;
	private ArrayList<Integer> week2;
	private ArrayList<Integer> week3;
	private ArrayList<Integer> week4;
	
	public Calendar4() {
		week1 = new ArrayList<>();
		week2 = new ArrayList<>();
		week3 = new ArrayList<>();
		week4 = new ArrayList<>();
	}
	
	public Calendar4(int w1[], int w2[], int w3[], int w4[]) {
		this();
		for (int v: w1) week1.add(v);
		for (int v: w2) week2.add(v);
		for (int v: w3) week3.add(v);
		for (int v: w4) week4.add(v);
	}
	
	public void addToWeek1(int v) {
		week1.add(v);
	}
	
	public void addToWeek2(int v) {
		week2.add(v);
	}
	
	public void addToWeek3(int v) {
		week3.add(v);
	}
	
	public void addToWeek4(int v) {
		week4.add(v);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		else {
			Calendar4 other = (Calendar4) o;
			return week1.equals(other.week1) && week2.equals(other.week2)
					&& week3.equals(other.week3) && week4.equals(other.week4);
		}
	}
	
	public ArrayList<Integer> getWeek(int week) {
		switch (week) {
			case 1: return week1;
			case 2: return week2;
			case 3: return week3;
			case 4: return week4;
		}
		
		return null;
	}
	
	public boolean contains(int v) {
		return week1.contains(v) || week2.contains(v)
				|| week3.contains(v) || week4.contains(v);
	}
	
	public int size() {
		return (week1.size() + week2.size() + week3.size() + week4.size());
	}
	
	public int weekOfTask(int v) {
		if (week1.contains(v)) return 1;
		if (week2.contains(v)) return 2;
		if (week3.contains(v)) return 3;
		else 				   return 4;
	}

	public ArrayList<Integer> getCalendarAsOneList() {
		ArrayList<Integer> all = new ArrayList<>();
		all.addAll(week1);
		all.addAll(week2);
		all.addAll(week3);
		all.addAll(week4);
		return all;
	}
}
