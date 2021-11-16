
import java.util.*;

public class ProjectScheduler {
	
	private final int jobsPerWeek = 3;
//	private final int maxWeekCount = 4; // 1 month
	private Digraph tasks;
	private Digraph reversedTasks;
//	private StrongComponents taskGroups;
//	private TopologicalOrder taskOrders;
	private ArrayList<String> taskKeys;
//	private ArrayList<ArrayList<String>> calendar;
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
		
//		taskGroups = new StrongComponents(tasks);
//		taskOrders = new TopologicalOrder(tasks);
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
	
	public void List() {
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
		
		System.out.println(calendarStr.toString());
	}
	
	public void CheckOrder(String firstTask, String secondTask) {
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
		
		if (errorFlag) return;
		
		int precedenceV = calendar.indexOf(firstTask);
		int precedenceW = calendar.indexOf(secondTask);
		
		if (precedenceV/jobsPerWeek == precedenceW/jobsPerWeek) {
			System.out.printf("You should do %s and %s on the same day.\n", firstTask, secondTask);
		}
		else if (precedenceV < precedenceW) {
			System.out.printf("You should do %s before %s.\n", firstTask, secondTask);
		}
		else {
			System.out.printf("You should do %s after %s.\n", firstTask, secondTask);
			
		}
		
	}
}
