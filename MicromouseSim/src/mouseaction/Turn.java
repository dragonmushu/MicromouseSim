package mouseaction;

import mouse.Mouse;

public class Turn extends MouseAction{
	
	//angle sent
	private int angleSpec;
	
	//angle to turn
	private int angle;
	
	//angle turned total
	private double totalTurned;
	
	//initial angle
	private int initialAngle;
	
	//direction of turn
	private boolean clockWise;
	
	public Turn (Mouse mouse, int angle){
		super (mouse);
		this.angleSpec = angle;
		this.angle = (Math.abs(angle));
		
	}
	
	public void init (){
		if (angleSpec>0) clockWise = true;
		else clockWise = false;
		
		totalTurned = 0;
		initialAngle = mouse.getAngle();
		calledInit = true;
	}


	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(!calledInit) init();
		
		double turnAngle = mouse.getAngularVelocity()*dt;
		totalTurned += turnAngle;
		if (totalTurned >= angle){
			totalTurned = angle;
		}
		
		if (clockWise) mouse.setAngle(initialAngle + (int) totalTurned);
		else mouse.setAngle(initialAngle - (int) totalTurned);

		if((int) totalTurned == angle) finished = true;
		
	}
	
	

}
