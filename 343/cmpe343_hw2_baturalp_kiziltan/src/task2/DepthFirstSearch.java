package task2;

public class DepthFirstSearch {

    public static int count;

    public static void exe(Maze maze, int y, int x) {
        if (! maze.isRoad(y, x)) return;

        ++count;
        maze.setVisited(y, x);
        exe(maze, y-1, x);
        exe(maze, y+1, x);
        exe(maze, y, x-1);
        exe(maze, y, x+1);
    }
}
