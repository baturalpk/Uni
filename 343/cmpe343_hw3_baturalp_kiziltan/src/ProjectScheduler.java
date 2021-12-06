//-----------------------------------------------------
// Title: Project Scheduler
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 3
// Description: This class defines a project scheduler to create a calendar
// 				with proper order of jobs.
//-----------------------------------------------------

import java.util.*;

public class ProjectScheduler {
	
	private final int jobsPerWeek = 3;
	private final int minTotalWeekCount = 4;
	
	private ArrayList<String> taskKeys;
	private Digraph tasks;
	private Digraph reversedTasks;
	private int numberOfTasks;
	private int totalWeekCount;
	private Calendar calendar;

	public ProjectScheduler(Scanner in) throws Exception {
		//--------------------------------------------------------
		// Summary: Reads tasks and their dependencies from a file. Initializes and builds a 
		// calendar for scheduling.
		// Precondition: in is scanner object
		// Postcondition: all member variables are initialized, the calendar is build/scheduled
		//--------------------------------------------------------
		
		int taskCount = Integer.parseInt(in.nextLine().trim());
		tasks = new Digraph(taskCount);
		reversedTasks = new Digraph(taskCount);
		taskKeys = new ArrayList<>(taskCount);
		numberOfTasks = taskCount;
		int possibleTotalWeekCount = (int) Math.ceil((double)numberOfTasks / (double)jobsPerWeek);
		if (possibleTotalWeekCount > minTotalWeekCount)
			totalWeekCount = possibleTotalWeekCount;
		else
			totalWeekCount = minTotalWeekCount;
		
		// Read & save name of tasks
		for (int i = 0; i < taskCount; ++i) {
			String taskName = in.nextLine().trim();
			taskKeys.add(taskName);
		}
		
		// Read & save dependencies
		while (true) {
			int v = in.nextInt();
			int w = in.nextInt();
			
			if (v == -1 || w == -1) break;
			
			tasks.addEdge(v, w);
			reversedTasks.addEdge(w, v);
		}
		
		DirectedCycle dependencyChecker = new DirectedCycle(tasks);
		if (dependencyChecker.hasCycle()) {
			System.err.println("Tasks have cyclic dependency each other. Check validity of input file!");
			System.exit(1);
		}
		
		calendar = new Calendar(totalWeekCount);
		buildCalendar(new DepthFirstOrder(tasks).post());
	}
	
	private void buildCalendar(Iterable<Integer> postOrder) {
		//--------------------------------------------------------
		// Summary: 
		// 0- upperWeek is used to follow the week number of the vertex will be added to the calendar 
		// 1- Iterate over postOrder form of tasks digraph
		// 2- If the current vertex's outdegree is 0 resets upperWeek to 4 since it is on top of hierarchy. Otherwise its parent - 1 
		// (Note: hierarchly top vertices will be added to last week (4th) since they will be completed last and their dependencies/children into previous weeks)
		// 3- Adds the current vertex into calendar
		// 4- Decreases upperWeek value
		// 5- Stores current value of upperWeek with 2 instances (initMaxWeek, maxWeek)
		// 6- Iterates over adjacency list of the current vertex and puts them into calendar by decreasing week number in each step (wrap ups if week number decreases below 0)
		// Precondition: postOrder is integer iterable
		// Postcondition: the calendar is built/scheduled
		//--------------------------------------------------------
		
		int upperWeek = totalWeekCount;
		
		for (int v: postOrder) {
			if (calendar.contains(v)) continue;
			
			if (tasks.outdegree(v) == 0) {
				upperWeek = totalWeekCount;
			} else {
				ArrayList<Integer> parent = (ArrayList<Integer>) tasks.adj(v);
				upperWeek = calendar.getWeekNoOfTheTask(parent.get(0)) - 1;
			}
			
			if (calendar.getWeek(upperWeek).size() >= jobsPerWeek) {
				--upperWeek;
			}
			
			calendar.add(v, upperWeek);
			--upperWeek;
			
			int initMaxWeek = upperWeek;
			int maxWeek = upperWeek;
			for (int w: reversedTasks.adj(v)) {
				if (calendar.contains(w)) continue;
				
				if (maxWeek <= 0) {
					maxWeek = initMaxWeek;
				}
				
				if (calendar.getWeek(maxWeek).size() >= jobsPerWeek) {
					--maxWeek;
				}
					
				calendar.add(w, maxWeek);
				--maxWeek;
			}
		}
		
	}
	
	public String List() {
		//--------------------------------------------------------
		// Summary: build a table representation of the calendar as string 
		// Precondition: -
		// Postcondition: returns string view of the calendar
		//--------------------------------------------------------
		StringBuilder calendarStr = new StringBuilder();		
		int weekNo = 1;
		for (ArrayList<Integer> week: calendar.getAllWeeks()) {
			calendarStr.append("Week " + weekNo + ": ");
			
			for (int taskIndex: week) {
				String task = taskKeys.get(taskIndex);
				calendarStr.append(task + " ");
			}
			
			calendarStr.append("\n");
			weekNo += 1;
		}
		
		calendarStr.deleteCharAt(calendarStr.length() - 1);
		return calendarStr.toString();
	}
	
	public int CheckOrder(String firstTask, String secondTask) {
		//--------------------------------------------------------
		// Summary: Checks if there is a project with the given task names. Then,
		// if there is no projects with one of them, returns "-9". Otherwise compares
		// them according to week number and returns ==>
		// 	*  0, if week numbers are equal,
		// 	*  1, if first task is before the second one
		// 	* -1, if first task is after the second one
		// Precondition: firstTask, secondTask are strings
		// Postcondition: returns a integer flag according to the comparison result
		//--------------------------------------------------------
		int v = taskKeys.indexOf(firstTask);
		int w = taskKeys.indexOf(secondTask);
		boolean errorFlag = false;
		
		if (v == -1) {
			System.out.printf("There is no project with the name of '%s'\n", firstTask);
			errorFlag = true;
		}
		
		if (w == -1) {
			System.out.printf("There is no project with the name of '%s'\n", secondTask);
			errorFlag = true;
		}
		
		if (errorFlag) return -9;
		
		int precedenceV = calendar.getWeekNoOfTheTask(v);
		int precedenceW = calendar.getWeekNoOfTheTask(w);
		
		if 		(precedenceV == precedenceW) return  0;
		else if (precedenceV < precedenceW)  return  1;
		else								 return -1;
	}
	
	public ArrayList<String> getTaskKeys() {
		//--------------------------------------------------------
		// Getter for task names
		//--------------------------------------------------------
		return taskKeys;
	}

	public Digraph getTasks() {
		//--------------------------------------------------------
		// Getter for task digraph
		//--------------------------------------------------------
		return tasks;
	}

	public int getNumberOfTasks() {
		//--------------------------------------------------------
		// Getter for task count
		//--------------------------------------------------------
		return numberOfTasks;
	}
	
	public Calendar getCalendar() {
		//--------------------------------------------------------
		// Getter for calendar instance
		//--------------------------------------------------------
		return this.calendar;
	}
	
}
