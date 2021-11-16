
import java.util.*;

public class ProjectScheduler {
	
	private final int jobsPerWeek = 3;
	private Digraph tasks;
	private StrongComponents taskGroups;
	private TopologicalOrder taskOrders;
	private ArrayList<String> taskKeys;
	private ArrayList<String>[] calendar;
	
	public ProjectScheduler(Scanner in) throws Exception {
		
		int taskCount = Integer.parseInt(in.nextLine().trim());
		tasks = new Digraph(taskCount);
		taskKeys = new ArrayList<>(taskCount);
		
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
		}
		
		taskGroups = new StrongComponents(tasks);
		taskOrders = new TopologicalOrder(tasks);
		createTheCalendar();
		
	}
	
	private void createTheCalendar() {
		
	}
	
	public void List() {
		
	}
	
	public void CheckOrder(String firstTask, String secondTask) {
		int v = taskKeys.indexOf(firstTask);
		int w = taskKeys.indexOf(secondTask);
		
		if (v == -1) {
			System.out.printf("There is no project with the name of '%s'\n", firstTask);
			return;
		}
		
		if (w == -1) {
			System.out.printf("There is no project with the name of '%s'\n", secondTask);
			return;
		}
		
		// TODO: Determine days according to the calendar.
		// TODO 2: Following code will be used for creating the calendar (@method createTheCalendar)
//		if (taskGroups.stronglyConnected(v, w)) {
//			int rankV = taskOrders.rank(v);
//			int rankW = taskOrders.rank(w);
//			
//			if (rankV > rankW) {
//				System.out.printf("You should do %s before %s.",
//						firstTask, secondTask);
//			} 
//			else {
//				System.out.printf("You should do %s after %s.",
//						firstTask, secondTask);
//			}
//		} 
//		else {
//			System.out.printf("You should do %s and %s on the same day.\n", 
//					firstTask, secondTask);
//		}
	}
}
