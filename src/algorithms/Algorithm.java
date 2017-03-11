/**
 * The main class that the user should 
 * extend off of
 * 
 * This class allows the user to turn, move 
 * forward, turn diagonally, and read walls
 * 
 * @author Akshay
 */
package algorithms;

import mouse.Mouse;
import mouseaction.MouseAction;
import mouseaction.Move;
import mouseaction.MoveHalf;
import mouseaction.Turn;

public abstract class Algorithm{
	
	
	
	//mouse object
	private Mouse mouse;
	
	
	//walls in front not adjusted for direction
	protected boolean leftWall;
	protected boolean rightWall;
	protected boolean frontWall;
	
	//runnning
	protected boolean running;

	
	/**
	 * sets up the algorithm by calling the 
	 * setupMaze () and
	 * init ()
	 * 
	 * Both of these functions should be implemented in the 
	 * simulator code that the user writes
	 */
	public Algorithm() {
		running = false;
		setupMaze();
		init();
	}
	
	/**
	 * MUST SPECIFY THE NAME OF THE ALGORITHM
	 * 
	 * THIS ALLOWS TO CHOOSE DIFFERENT ALGORITHMS
	 * MAKE SURE EACH ALGORITHM RETURNS A UNIQUE STRING
	 * IF TWO NAMES ARE THE SAME THEN IT WILL BE PRONE TO ERRORS
	 * 
	 * 
	 * @return THE NAME OF THE ALGORITHMS
	 */
	public abstract String getName ();
	
	/**
	 * THIS IS WHERE MAZE ASPECTS SHOULD BE SETUP
	 * 
	 * the user can implement any aspect of the maze here
	 * no information of the maze can be read without the maze
	 * being at the cell on the maze
	 * 
	 * CALLED ONCE AT START (OR WHEN RESET)
	 * 
	 * DO NOT SETUP ANYTHING IN THE CONSTRUCTOR
	 */
	public abstract void setupMaze ();
	
	/**
	 * THIS IS WHERE ANYTHING MISC. SHOULD BE SETUP
	 * 
	 * the user must implement this method but can leave it 
	 * blank but make sure to call the run () function
	 * It is for the users discretion
	 * 
	 * CALL THE run () METHOD TO MAKE SURE RUNNING IS SET TO TRUE
	 * CALLED ONCE AT START (OR WHEN RESET)
	 * 
	 * DO NOT SETUP ANYTHING IN THE CONSTRUCTOR
	 */
	public abstract void init ();
	
	/**
	 * 
	 * THIS IS CALLED AFTER ALL MOUSE MOVEMENTS ARE DONE
	 * 
	 * the user must implement this method
	 * the update method is called after all mouse movements are
	 * complete:
	 * for example if in the previous method call the user calls turnLeft(),
	 * and move() - only once the mouse has done these calls is the update function
	 * called again. 
	 * 
	 * UPDATE IS CALLED ONLY IF RUNNING IS SET TO TRUE
	 * TO SET RUNNING TO TRUE CALL run ()
	 * 
	 * @param dt the change in time from the previous update call
	 */
	public abstract void update (float dt);
	
	
	/**
	 * READS ALL THE WALLS. CALL THIS TO KNOW IF THERE ARE WALLS
	 * REMEMBER THE MOUSE DOESN'T AUTOMATICALLY ADJUST FOR ORIENTATION
	 * 
	 * IF THE MOUSE IS NOT FULLY IN A CELL (HALF/INBETWEEN TWO CELLS)
	 * ERROR WILL SHOW
	 * 
	 * user calls method to read the walls in the cell around the mouse
	 * the read is not adjusted to orientation. If the mouse is facing 
	 * left and there is a wall in front of it, frontWall == true, if it 
	 * facing to the front (90 deg) then the leftWall == true
	 * 
	 * ADJUST FOR ORIENTATION IN OWN ALGO
	 * 
	 */
	protected void readWalls (){
		leftWall = mouse.leftWall();
		rightWall = mouse.rightWall();
		frontWall = mouse.frontWall();
	}
	
	/**
	 * TURNS THE MOUSE 90 DEG CCW
	 */
	public void turnLeft (){
		mouse.addAction(new Turn(mouse, -90));
	}
	
	/**
	 * TURNS THE MOUSE 90 DEG CW
	 */
	public void turnRight (){
		mouse.addAction(new Turn(mouse, 90));
	}
	
	/**
	 * TURNS THE MOUSE 45 DEF CCW
	 */
	public void turnLeftDiag () {
		mouse.addAction(new Turn(mouse, -45));
	}
	
	/**
	 * TURNS THE MOUSE 45 DEG CW
	 */
	public void turnRightDiag (){
		mouse.addAction(new Turn(mouse, 45));
	}
	
	/**
	 * TURNS THE MOUSE 90 DEG CCW n TIMES
	 * 
	 * @param n the number of times to execute
	 */
	public void turnLeft (int n){
		mouse.addAction(new Turn(mouse, n*-90));
	}
	
	/**
	 * TURNS THE MOUSE 90 DEG CW n TIMES
	 * 
	 * @param n the number of times to execute
	 */
	public void turnRight (int n){
		mouse.addAction(new Turn(mouse, n*90));
	}
	
	/**
	 * TURNS THE MOUSE 45 DEG CCW n TIMES
	 * 
	 * @param n the number of times to execute
	 */
	public void turnLeftDiag (int n) {
		mouse.addAction(new Turn(mouse, n*-45));
	}
	
	/**
	 * TURNS THE MOUSE 45 DEG CW n TIMES
	 * 
	 * @param n the number of times to execute
	 */
	public void turnRightDiag (int n){
		mouse.addAction(new Turn(mouse, n*45));
	}
	
	/**
	 * MOVES THE MOUSE FORWARD n CELLS UP
	 * 
	 * @param steps the number of steps forward (cells forward)
	 */
	public void move (int steps){
		mouse.addAction(new Move(mouse, steps));
	}
	
	/**
	 * MOVES THE MOUSE FORWARD ONE CELL UP
	 */
	public void move (){
		mouse.addAction(new Move(mouse));
	}
	
	/**
	 * MOVES THE MOUSE FORWARD HALF A CELL UP
	 * 
	 */
	public void moveHalf (){
		mouse.addAction(new MoveHalf(mouse));
	}
	
	/**
	 * when called running is set to true
	 * UPDATE FUNCTION IS ONLY CALLED IF THIS IS CALLED AT ALGO START
	 */
	protected void run () {
		running = true;
	}
	
	/**
	 * calls setupMaze and init to restart program memory
	 * THIS IS WHY NOT HAVING ANYTHING EXCEPT
	 * CALL TO SUPER IN CONSTRUCTOR IS IMPORTANT
	 * 
	 * THIS MAKES SURE RESET WORKS PROPERLY
	 * 
	 * 
	 * @return true if everything works
	 */
	public boolean reset (){
		running = false;
		setupMaze();
		init();
		return true;
	}
	
	/**
	 * when called running is set to false
	 * UPDATE FUNCTION IS NOT CALLED IF THIS IS CALLED
	 */
	protected void stop (){
		running = false;
	}
	
	/**
	 * 
	 * @return true if running is true, false otherwise
	 */
	public boolean isRunning () {
		return running;
	}
	
	/**
	 * binds algorithms to mouse so mouse can be moved
	 * 
	 * @param mouse the mouse for the actions needed
 	 */
	public void setMouse (Mouse mouse){
		this.mouse = mouse;
	}
	
	/**
	 * ONLY USED FOR DEBUGGING AND CHECKING IF WALL READS ARE CORRECT
	 * 
	 * 
	 * 
	 * @param mazeVisited
	 * @param leftWalls
	 * @return
	 */
	public boolean checkLeftWallMismatch (boolean [][] mazeVisited, boolean [][] leftWalls){
		boolean correct = true;
		for (int r = 0; r<16; r++){
			for (int c=0; c<16; c++){
				if (mazeVisited[r][c]){
					if ((!leftWalls[r][c] || !mouse.mazeLeftWall(r, c)) && (leftWalls[r][c] || mouse.mazeLeftWall(r, c))){
						correct = false;
						System.out.println("LEFT WALL MISMATCH AT: ("+r+","+c+")");
					}
					
				}
				if(leftWalls[r][c] && !mouse.mazeLeftWall(r, c)){
					correct = false;
					System.out.println("LEFT WALL MISMATCH (NOT VISITED) AT: ("+r+","+c+")");
				}
			}
		}
		
		return correct;
	}
	
	/**
	 * ONLY USED FOR DEBUGGING AND CHECKING IF WALL READS ARE CORRECT
	 * 
	 * @param mazeVisited
	 * @param rightWalls
	 * @return
	 */
	public boolean checkRightWallMismatch (boolean [][] mazeVisited, boolean [][] rightWalls){
		boolean correct = true;
		for (int r = 0; r<16; r++){
			for (int c=0; c<16; c++){
				if (mazeVisited[r][c]){
					if ((!rightWalls[r][c] || !mouse.mazeRightWall(r, c)) && (rightWalls[r][c] || mouse.mazeRightWall(r, c))){
						correct = false;
						System.out.println("RIGHT WALL MISMATCH AT: ("+r+","+c+")");
					}
					
				}
				if(rightWalls[r][c] && !mouse.mazeRightWall(r, c)){
					correct = false;
					System.out.println("RIGHT WALL MISMATCH (NOT VISITED) AT: ("+r+","+c+")");
				}
				
			}
		}
		
		return correct;
	}
	
	/**
	 * ONLY USED FOR DEBUGGING AND CHECKING IF WALL READS ARE CORRECT
	 * 
	 * @param mazeVisited
	 * @param topWalls
	 * @return
	 */
	public boolean checkTopWallMismatch (boolean [][] mazeVisited, boolean [][] topWalls){
		boolean correct = true;
		for (int r = 0; r<16; r++){
			for (int c=0; c<16; c++){
				if (mazeVisited[r][c]){
					if ((!topWalls[r][c] || !mouse.mazeTopWall(r, c)) && (topWalls[r][c] || mouse.mazeTopWall(r, c))){
						correct = false;
						System.out.println("TOP WALL MISMATCH AT: ("+r+","+c+")");
					}
					
				}
				if(topWalls[r][c] && !mouse.mazeTopWall(r, c)){
					correct = false;
					System.out.println("TOP WALL MISMATCH (NOT VISITED) AT: ("+r+","+c+")");
				}
				
			}
		}
		
		return correct;
	}
	
	/**
	 * ONLY USED FOR DEBUGGING AND CHECKING IF WALL READS ARE CORRECT
	 * 
	 * @param mazeVisited
	 * @param bottomWalls
	 * @return
	 */
	public boolean checkBottomWallMismatch (boolean [][] mazeVisited, boolean [][] bottomWalls){
		boolean correct = true;
		for (int r = 0; r<16; r++){
			for (int c=0; c<16; c++){
				if (mazeVisited[r][c]){
					if ((!bottomWalls[r][c] || !mouse.mazeDownWall(r, c)) && (bottomWalls[r][c] || mouse.mazeDownWall(r, c))){
						correct = false;
						System.out.println("BOTTOM WALL MISMATCH AT: ("+r+","+c+")");
					}
					
				}
				if(bottomWalls[r][c] && !mouse.mazeDownWall(r, c)){
					correct = false;
					System.out.println("BOTTOM WALL MISMATCH (NOT VISITED) AT: ("+r+","+c+")");
				}
				
			}
		}
		
		return correct;
	}
}
