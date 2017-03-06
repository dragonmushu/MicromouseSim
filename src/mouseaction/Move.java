package mouseaction;

import mouse.Mouse;
import static maze.Cell.CELL_SIZE;

public class Move extends MouseAction{
	
	//number of steps to move
	private int steps;
	
	
	//total distance to travel that is left
	private int xDist;
	private int yDist;
	
	//total distance traveled
	private double xDistTraveled;
	private double yDistTraveled;
	
	//original x and y position
	private int origX;
	private int origY;
	
	//velocity in x and y
	private double xVelocity;
	private double yVelocity;
	
	public Move (Mouse mouse) {
		super (mouse);
		steps = 1;
		
	}
	
	public Move (Mouse mouse, int steps){
		super (mouse);
		this.steps = steps;
	}
	
	public void init () {
		//x and y velocity
		yVelocity = mouse.getVelocity()*Math.sin(Math.toRadians(mouse.getAngle()));
		xVelocity = mouse.getVelocity()*Math.cos(Math.toRadians(mouse.getAngle()));
		
		//original x and y positions
		origX = mouse.getX();
		origY = mouse.getY();
		
		//total distance
		if (mouse.getAngle() == 0 || mouse.getAngle() == 180){
			xDist = steps*CELL_SIZE;
			yDist = 0;
		}
		else if (mouse.getAngle() == 90 || mouse.getAngle() == 270){
			xDist = 0;
			yDist = steps*CELL_SIZE;
		}
		else {
			xDist = steps*CELL_SIZE;
			yDist = steps*CELL_SIZE;
		}
		

		//distance already traveled
		xDistTraveled = 0;
		yDistTraveled = 0;
		
		//init is called
		calledInit = true;
	}
	
	

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(!calledInit) init();
		
		double xDistToMove = xVelocity*dt;
		double yDistToMove = yVelocity*dt;
		
		xDistTraveled += xDistToMove;
		yDistTraveled += yDistToMove;
		
		if (Math.abs(xDistTraveled)>=xDist) {
			if (xDistTraveled < 0) xDistTraveled = -1*xDist;
			else xDistTraveled = xDist;
		}	
		if (Math.abs(yDistTraveled)>=yDist){
			if (yDistTraveled<0) yDistTraveled = -1*yDist;
			else yDistTraveled = yDist;
		}
		
		mouse.setX(origX+(int)xDistTraveled);
		mouse.setY(origY+(int)yDistTraveled);
		
		
		
		if ((int)Math.abs(xDistTraveled) == xDist && (int)Math.abs(yDistTraveled) == yDist){
			switch (mouse.getAngle()){
			case 0: 
				mouse.setCol(mouse.getCol()+steps);
				break;
			case 45:
				mouse.setCol(mouse.getCol()+steps);
				mouse.setRow(mouse.getRow()+steps);
				break;
			case 90:
				mouse.setRow(mouse.getRow()+steps);
				break;
			case 135:
				mouse.setCol(mouse.getCol()-steps);
				mouse.setRow(mouse.getRow()+steps);
				break;
			case 180:
				mouse.setCol(mouse.getCol()-steps);
				break;
			case 225:
				mouse.setCol(mouse.getCol()-steps);
				mouse.setRow(mouse.getRow()-steps);
				break;
			case 270:
				mouse.setRow(mouse.getRow()-steps);
				break;
			case 315:
				mouse.setCol(mouse.getCol()+steps);
				mouse.setRow(mouse.getRow()-steps);
				break;
			}
			finished = true;
		}
			
		
	}

}
