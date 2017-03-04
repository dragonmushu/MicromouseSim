/*
 * Akshay Ben
 * 10/24/2016
 * 
 * 
 * contains information about one cell in a maze
 */
package maze;

public class Cell {
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean up;
	
	//size
	public static final int CELL_SIZE = 60;
	
	
	//constructor which sets all to false
	public Cell (){
		this.left = false;
		this.right = false;
		this.down = false;
		this.up = false;
	}

	
	public Cell (boolean left, boolean right, boolean down, boolean up){
		this.left = left;
		this.right = right;
		this.down = down;
		this.up = up;
	}
	
	public boolean getLeft () { return left; } //left wall
	public boolean getRight () { return right; } // right wall
	public boolean getDown () { return down; } // down wall
	public boolean getUp () { return up; } //up wall
	
	public void setLeft (boolean left) { this.left =left; }
	public void setRight (boolean right)  { this.right = right; }
	public void setUp (boolean up) { this.up = up; }
	public void setDown (boolean down) { this.down = down; }
	
}
