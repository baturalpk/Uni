package task2;

public class Maze {

    private enum State {
        ROAD,
        VISITED,
        WALL
    }

    private final int height;
    private final int width;
    private final State[][] maze;

    public Maze(int h, int w) {
        this.height = h;
        this.width = w;
        this.maze = new State[h][w];

        for (int i = 0; i < H(); ++i) {
            maze[i][0] = State.WALL;
            maze[i][W() - 1] = State.WALL;
        }

        for (int j = 0; j < W(); ++j) {
            maze[0][j] = State.WALL;
            maze[H() - 1][j] = State.WALL;
        }
    }

    public State[][] M() {
        return this.maze;
    }

    public int H() {
        return this.height;
    }

    public int W() {
        return this.width;
    }

    public boolean isRoad(int y, int x) {
        return (y < H() && x < W() &&
				y >= 0 && x >= 0 &&
				maze[y][x] == State.ROAD);
    }

    public boolean isVisited(int y, int x) {
        return maze[y][x] == State.VISITED;
    }

    public boolean isBlocked(int y, int x) {
        return maze[y][x] == State.WALL;
    }

    public void setVisited(int y, int x) {
        maze[y][x] = State.VISITED;
    }

    public void setWall(int y, int x) {
        maze[y][x] = State.WALL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < H(); ++i) {
            for (int j = 0; j < W(); ++j) {
                if (maze[i][j] == State.WALL) sb.append("*");
                else sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
