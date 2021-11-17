
import java.util.*;

public class ProjectScheduler {
	
	private final int jobsPerWeek = 3;
	private ArrayList<String> taskKeys;
	private Digraph tasks;
	private Digraph reversedTasks;
	private int numberOfTasks;
	private ArrayList<String> calendar;

	public ProjectScheduler(Scanner in) throws Exception {
		
		int taskCount = Integer.parseInt(in.nextLine().trim());
		tasks = new Digraph(taskCount);
		reversedTasks = new Digraph(taskCount);
		taskKeys = new ArrayList<>(taskCount);
		numberOfTasks = taskCount;
		
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
		
		createTheCalendar();
		
	}
	
	private void createTheCalendar() {
		ArrayList<String> scheduled = new ArrayList<>();
		ArrayList<Integer> marked = new ArrayList<>();
		
		for (int v = 0; v < numberOfTasks; ++v) {
						
			int dependencyCount = reversedTasks.outdegree(v);
			
			if (dependencyCount != 0) {
				for (int w: reversedTasks.adj(v)) {
					if (! marked.contains(w)) {
						scheduled.add(taskKeys.get(w));
						marked.add(w);
					}
				}
			}
			
			if (! marked.contains(v)) {
				scheduled.add(taskKeys.get(v));
				marked.add(v);
			}
		}
		
		calendar = scheduled;
	}
	
	public String List() {
		StringBuilder calendarStr = new StringBuilder();
		
		int week = 0;
		int taskAtTheWeek = jobsPerWeek + 1;
		for (String task: calendar) {
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
		
		int precedenceV = calendar.indexOf(firstTask);
		int precedenceW = calendar.indexOf(secondTask);
		
		if (precedenceV/jobsPerWeek == precedenceW/jobsPerWeek) return 0;
		else if (precedenceV < precedenceW) 					return 1;
		else 													return -1;
	}
	
	public ArrayList<String> getTaskKeys() {
		return taskKeys;
	}

	public Digraph getTasks() {
		return tasks;
	}

	public Digraph getReversedTasks() {
		return reversedTasks;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}
	
	public ArrayList<String> getCalendar() {
		return this.calendar;
	}
}
