package task2;

public class Maze {

    public enum CellState {
        DEFAULT,
        VISITED,
        WALL
    }
    
    public static class Cell {
    	final int y;
    	final int x;
    	Cell (int y, int x) { this.y=y; this.x=x; }
    }

    private final int height;
    private final int width;
    private CellState[][] maze;

    public Maze(int h, int w) {
        this.height = h;
        this.width = w;
        this.maze = new CellState[h][w];
        
        for (int i = 0; i < H(); ++i) {
        	for (int j = 0; j < W(); ++j) {
        		maze[i][j] = CellState.DEFAULT;
        	}
        }        
    }

    public CellState[][] M() {
        return this.maze;
    }

    public int H() {
        return this.height;
    }

    public int W() {
        return this.width;
    }
    
    public boolean isVisited(Cell c) {
    	return maze[c.y][c.x] == CellState.VISITED;
    }
    
    public boolean isBlocked(Cell c) {
        return maze[c.y][c.x] == CellState.WALL;
    }

    public void setVisited(Cell c) {
        maze[c.y][c.x] = CellState.VISITED;
    }

    public void setWall(Cell c) {
        maze[c.y][c.x] = CellState.WALL;
    }
    
    public boolean isCellValid(Cell c) {
    	return (c.y < H() && c.x < W()
    			&& c.y >= 0 && c.x >= 0);
    }
    
    public boolean checkForBorder(Cell c) {
    	return (c.y == H()-1 || c.x == W()-1 
    			|| c.y == 0 || c.x == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < H(); ++i) {
            for (int j = 0; j < W(); ++j) {
                if (maze[i][j] == CellState.WALL) sb.append("#");
                else if (maze[i][j] == CellState.VISITED) sb.append(".");
                else sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
