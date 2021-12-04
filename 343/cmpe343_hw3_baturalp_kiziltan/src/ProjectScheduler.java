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
	private final int totalWeekCount = 4;
	private final int MAX_JOB_LIMIT = jobsPerWeek * totalWeekCount;
	
	private ArrayList<String> taskKeys;
	private Digraph tasks;
	private Digraph reversedTasks;
	private int numberOfTasks;
	private Calendar4 calendar;

	public ProjectScheduler(Scanner in) throws Exception {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		int taskCount = Integer.parseInt(in.nextLine().trim());
		tasks = new Digraph(taskCount);
		reversedTasks = new Digraph(taskCount);
		taskKeys = new ArrayList<>(taskCount);
		numberOfTasks = taskCount;
		
		if (numberOfTasks > MAX_JOB_LIMIT) {
			System.err.println("The number of tasks exceeds maximum limit (given 1 month for 3 jobs per week)"
					+ "\n Check validity of input file!");
			System.exit(1);
		}
		
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
		
		calendar = new Calendar4();
		buildCalendar(new DepthFirstOrder(tasks).post());
	}
	
	private void buildCalendar(Iterable<Integer> postOrder) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		/*
		 * 0) Create a list for each week (totally 4). => (Create "Calendar" class and 
		 *    add util. methods like "contains(x)").
		 * 1) Get topological order of tasks.
		 * 2) Get reversedTasks.
		 * 3) Iterate over topological order of tasks from the highest
		 *    (in each step check whether it is in the calendar or not).
		 * 4) Add the current vertex into calendar (check outdegree from tasks and
		 *    if outdeg=0, then it means it is the highest order, and add into week4)
		 * 5) Check adjacent elements of the current vertex, using reversedTasks.
		 * 6) Distribute adjacent vertices into stacks up to 4, wrap up 
		 * 	  and distribute the remaining if the count's higher than 4.
		 *    ! NOTE: First put the current vertex into last week => week4, and distribute
		 *    the remaining by starting from (last-1). week => week3. So after putting an element
		 *    decrease maxweek.
		 *    ! NOTE 2: The wrapping up process will be determined depending on the current vertex,
		 *    for example if the current vertex has adjacent elements & the current vertex is already
		 *    in the calendar, then set this vertex's week as max week and add the adjacent ones up to 
		 *    the max week (excluding the max week itself)
		 * 
		 * */
		
		int upperWeek = totalWeekCount;
		
		for (int v: postOrder) {
			if (calendar.size() >= numberOfTasks) break;
			if (calendar.contains(v)) continue;
			
			if (tasks.outdegree(v) == 0) {
				upperWeek = totalWeekCount;
			} else {
				ArrayList<Integer> parent = (ArrayList<Integer>) tasks.adj(v);
				upperWeek = calendar.weekOfTask(parent.get(0)) - 1;
			}
			
			if (calendar.getWeek(upperWeek).size() >= jobsPerWeek) {
				--upperWeek;
			}
			
			calendar.getWeek(upperWeek).add(v);
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
					
				calendar.getWeek(maxWeek).add(w);
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
		
		int week = 0;
		int taskAtTheWeek = jobsPerWeek + 1;
		for (int taskIndex: calendar.getCalendarAsOneList()) {
			String task = taskKeys.get(taskIndex);
			if (taskAtTheWeek == (jobsPerWeek+1)) {
				if (week != 0) calendarStr.append("\n");
				++week;
				taskAtTheWeek = 1;
				calendarStr.append("Week " + week + ": ");
			}
			
			calendarStr.append(task + " ");
			++taskAtTheWeek;
		}
		
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
		
		int precedenceV = calendar.weekOfTask(v);
		int precedenceW = calendar.weekOfTask(w);
		
		if 		(precedenceV == precedenceW) return 0;
		else if (precedenceV < precedenceW)  return 1;
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
	
	public Calendar4 getCalendar() {
		//--------------------------------------------------------
		// Getter for calendar instance
		//--------------------------------------------------------
		return this.calendar;
	}
	
}
