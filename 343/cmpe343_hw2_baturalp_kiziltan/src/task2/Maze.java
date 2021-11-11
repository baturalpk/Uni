//-----------------------------------------------------
// Title: Maze
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 2
// Description: An helper class that represents a Maze structure.
// It contains an enum class called CellState to mark each cell's situation.
// Also, it contains a helper class called Cell which basically represents a cell of maze,
// provides abstraction for x, y coordinates of 2D maze array.
//-----------------------------------------------------

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
        //--------------------------------------------------------
        // Summary: Create a maze depending on given height and width constraints.
    	// Fill maze object's each cell with Default state.
        // Precondition: h (height) is integer, w (width) is integer.
        // Postcondition: h & w are set. the maze is built.
        //--------------------------------------------------------
    	
        this.height = h;
        this.width = w;
        this.maze = new CellState[h][w];
        
        for (int i = 0; i < H(); ++i) {
        	for (int j = 0; j < W(); ++j) {
        		maze[i][j] = CellState.DEFAULT;
        	}
        }        
    }

    // Getter for maze itself
    public CellState[][] M() {
        return this.maze;
    }

    // Getter for height of maze
    public int H() {
        return this.height;
    }

    // Getter for width of maze
    public int W() {
        return this.width;
    }
    
    // Checks a particular cell whether it is visited or not
    public boolean isVisited(Cell c) {
    	return maze[c.y][c.x] == CellState.VISITED;
    }
    
    // Checks a particular cell whether it is wall or not
    public boolean isBlocked(Cell c) {
        return maze[c.y][c.x] == CellState.WALL;
    }

    // Setter to mark a particular cell as visited
    public void setVisited(Cell c) {
        maze[c.y][c.x] = CellState.VISITED;
    }
    
    // Setter to mark a particular cell as wall
    public void setWall(Cell c) {
        maze[c.y][c.x] = CellState.WALL;
    }
    
    public boolean isCellValid(Cell c) {
        //--------------------------------------------------------
        // Summary: Checks if the given cell is inside valid boundaries of the maze.
        // Precondition: c is a Cell
        // Postcondition: c is checked and returned boolean
        //--------------------------------------------------------
    	
    	return (c.y < H() && c.x < W()
    			&& c.y >= 0 && c.x >= 0);
    }
    
    public boolean checkForBorder(Cell c) {
        //--------------------------------------------------------
        // Summary: Checks whether the given cell is border itself or not.
        // Precondition: c is a Cell
        // Postcondition: c is checked and returned boolean
        //--------------------------------------------------------
    	
    	return (c.y == H()-1 || c.x == W()-1 
    			|| c.y == 0 || c.x == 0);
    }

    @Override
    public String toString() {
        //--------------------------------------------------------
        // Summary: Builds visual representation of the maze using `#`, ` ` and `.`
    	// Uses a stringbuilder and iterate over all cells.
    	// If cell is visited then put `.` If cell is a wall then put `#` Otherwise put whitespace (` `)
        // Precondition: -
        // Postcondition: returns string representation of maze object
        //--------------------------------------------------------
    	
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
