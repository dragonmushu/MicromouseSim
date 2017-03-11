/**
 * @author Akshay
 */
package mouse;

import static maze.Cell.CELL_SIZE;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import maze.Maze;
import mouseaction.MouseAction;

public class Mouse {
	
	//row and col
	private double row;
	private double col;
	
	
	//maze
	private Maze maze;

	
	//variables defining location and angle
	private int x;
	private int y;
	private int angle;

	//image of the mouse
	private Image image;
	public static final int MOUSE_WIDTH = 20;
	public static final int MOUSE_HEIGHT = 26;
	
	//actions list
	private ArrayList <MouseAction> actions;
	private MouseAction currentAction;
	
	//linear velocity
	private double velocity;
	
	//angular velocity
	private double angularVelocity;
	
	/**
	 * The constructor of the Mouse
	 * Initializes the Mouse object 
	 * Velocity and angular velocity are set to 192 pixels per second
	 * action list is initialized and is blank
	 * 
	 * @param maze this is the maze object used to detect walls
	 */
	public Mouse (Maze maze) {
		//maze
		this.maze = maze;
		
		//setup row and col
		row = 15;
		col = 0;
		
		//image
		ImageIcon icon = new ImageIcon("Mouse.png");
		image = icon.getImage();
		
		//x and y position
		x =(int)col*CELL_SIZE + CELL_SIZE/2 - MOUSE_WIDTH/2;
		y = (int)row*CELL_SIZE + CELL_SIZE/2 - MOUSE_HEIGHT/2;
		
		//angle
		angle = 270;
		
		//speed
		velocity = 200/1000.0;
		angularVelocity = 200/1000.0;
		
		
		//actions
		actions = new ArrayList<MouseAction>();
		currentAction = null;
		
	}
	
	/**
	 * 
	 * @return the x position of the mouse
	 */
	public int getX () { return x; }
	
	/**
	 * 
	 * @return the y position of the mouse
	 */
	public int getY () { return y; }
	
	/**
	 * Be careful this returns an integer and thus if the mouse 
	 * is inbetween cells during call the return will be erratic
	 * 
	 * @return the row of the mouse
	 */
	public double getRow () { return row; }
	
	/**
	 * 
	 * Be careful this returns an integer and thus if the mouse 
	 * is inbetween cells during call the return will be erratic
	 * 
	 * @return the col of the mouse
	 */
	public double getCol () { return col; }
	
	/**
	 * 
	 * @return the angle that the mouse is currently at
	 */
	public int getAngle () { return angle; }
	
	/**
	 * 
	 * @return the linear velocity that the mouse is moving at
	 */
	public double getVelocity () {return velocity;}
	
	/**
	 * 
	 * @return the angular velocity that the mouse is moving at
	 */
	public double getAngularVelocity () {return angularVelocity;}

	/**
	 * 
	 * @param x the x position of the mouse (pixel)
	 */
	public void setX (int x) { this.x = x; }
	
	/**
	 * 
	 * @param y the y position of the mouse (pixel)
	 */
	public void setY (int y) {this.y = y;}
	
	/**
	 * The mouse is at 270 degrees when it is facing up right
	 * The angle increases in a clockwise fashion
	 * 
	 * @param angle the angle in degrees of rotation for mouse
	 */
	public void setAngle (int angle) { 
		if (angle<0) angle+=360;
		this.angle = angle%360; 
	}
	
	/**
	 * 
	 * @param r the row of the mouse in the maze
	 */
	public void setRow (double r){ this.row = r; }
	
	/**
	 * 
	 * @param c the col of the mouse in the maze
	 */
	public void setCol (double c) { this.col = c;}
	
	/**
	 * 
	 * @param val the value to set the velocity to
	 */
	public void setVelocity (double val) { velocity = val; }
	
	/**
	 * 
	 * @param val the value to set the angular velocity to
	 */
	public void setAngularVelocity (double val) {angularVelocity = val; }

	/**
	 * 
	 * The update is called from the GUI panel
	 * updates the mouses actions every dt increment
	 * 
	 * @param dt the change in time from the last call
	 */
	public void update (float dt){
		runAction(dt);
		if (currentAction!=null && currentAction.isFinished()) currentAction = null;
	}
	
	/**
	 * draws the mouse on the panel with specific location (x, y) 
	 * and angle 
	 * 
	 * @param g the graphics2D object used for drawing
	 */
	public void render (Graphics2D g) {
		int angleChange = angle - 270;
		AffineTransform backup = g.getTransform();
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angleChange), x+MOUSE_WIDTH/2, y+MOUSE_HEIGHT/2);
		g.setTransform(transform);
		g.drawImage(image, x, y, MOUSE_WIDTH, MOUSE_HEIGHT, null);
		g.setTransform(backup);
	}
	
	/**
	 * adds a new action to the list of actions
	 * 
	 * @param action the MouseAction object to add
	 */
	public void addAction (MouseAction action){
		actions.add(action);
	}
	
	/**
	 * Action to run
	 * 
	 * @param dt change in time from last call
	 */
	private void runAction (float dt){
		if (currentAction==null){
			if (finishedAllActions()) return;
			else currentAction = pollAction ();
		}
		currentAction.update(dt);
	}
	
	/**
	 * 
	 * @return true if all actions are completed
	 */
	private boolean finishedAllActions () {
		return actions.isEmpty();
	}
	
	/**
	 * 
	 * @return true if there are no actions running
	 */
	public boolean noActionsRunning () {
		return currentAction == null && finishedAllActions();
	}
	
	/**
	 * Stops all ongoing actions and removes all
	 * actions added
	 */
	public void stopAllActions (){
		currentAction = null;
		actions.clear();
	}
	
	/**
	 * Stops current action and skips to next
	 */
	public void stopCurrentAction (){
		currentAction = null;
	}
	
	/**
	 * 
	 * @return the action at top of the list
	 */
	private MouseAction pollAction () {
		return actions.remove(0);
	}
	
	/**
	 * 
	 * @return the action at the end of the list
	 */
	private MouseAction popAction (){
		return actions.remove(actions.size());
	}
	
	/**
	 * 
	 * WILL THROW ERROR IF MOUSE IS INBETWEEN CELLS
	 * 
	 * @return true if there is a wall directly in front of the mouse
	 */
	public boolean frontWall (){
		if (angle == 270 && maze.getCell((int) row, (int) col).getUp()) return true;
		else if (angle == 0 && maze.getCell((int) row, (int) col).getRight()) return true;
		else if (angle == 90 && maze.getCell((int) row, (int) col).getDown()) return true;
		else if (angle == 180 && maze.getCell((int) row, (int) col).getLeft()) return true;
		else return false;
	}
	
	/**
	 * WILL THROW ERROR IF MOUSE IS INBETWEEN CELLS
	 * 
	 * @return true if there is a wall on the left of the mouse
	 */
	public boolean leftWall () {
		if (angle == 180 && maze.getCell((int) row, (int) col).getDown()) return true;
		else if (angle == 90 && maze.getCell((int) row, (int) col).getRight()) return true;
		else if (angle == 0 && maze.getCell((int) row, (int) col).getUp()) return true;
		else if (angle == 270 && maze.getCell((int) row, (int) col).getLeft()) return true;
		else return false;
	}
	
	/**
	 * WILL THROW ERROR IF MOUSE IS INBETWEEN CELLS
	 * 
	 * @return true if there is a wall on the right of the mouse
	 */
	public boolean rightWall (){
	
		
		if (angle == 0 && maze.getCell((int) row, (int) col).getDown()) return true;
		else if (angle == 270 && maze.getCell((int) row, (int) col).getRight()) return true;
		else if (angle == 180 && maze.getCell((int) row, (int) col).getUp()) return true;
		else if (angle == 90 && maze.getCell((int) row, (int) col).getLeft()) return true;
		else return false;
	}
	
	/**
	 * USED FOR DEBUGGING
	 */
	
	/**
	 * 
	 * @param r row
	 * @param c column
	 * @return true if wall at specified location
	 */
	public boolean mazeRightWall (int r, int c) {
		return maze.getCell(r, c).getRight();
	}
	
	/**
	 * 
	 * @param r row
	 * @param c column
	 * @return true if wall at specified location
	 */
	public boolean mazeLeftWall (int r, int c) {
		return maze.getCell(r, c).getLeft();
	}
	
	/**
	 * 
	 * @param r row
	 * @param c column
	 * @return true if wall at specified location
	 */
	public boolean mazeTopWall (int r, int c) {
		return maze.getCell(r, c).getUp();
	}
	
	/**
	 * 
	 * @param r row
	 * @param c column
	 * @return true if wall at specified location
	 */
	public boolean mazeDownWall (int r, int c) {
		return maze.getCell(r, c).getDown();
	}
}
