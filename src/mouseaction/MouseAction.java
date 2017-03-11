/**
 * Base Actions that a mouse can perform
 * The class is abstract and thus cannot be instantiated
 * but has to be extended by child class
 * 
 * @author Akshay Ben
 */
package mouseaction;

import mouse.Mouse;

public abstract class MouseAction {
	
	//when the action is finished
	protected boolean finished;
	
	//first call to init
	protected boolean calledInit;
	
	//allows to control mouse object
	protected Mouse mouse;
	
	
	/**
	 * 
	 * @param mouse Mouse object used to control mouse motion
	 */
	public MouseAction (Mouse mouse) {
		finished = false;
		calledInit = false;
		this.mouse = mouse;
	}
	
	/**
	 * Called every dt when current action (this)
	 * is being run
	 * 
	 * @param dt change in time from last call of update
	 */
	public abstract void update (float dt);
	
	/**
	 * 
	 * @return true if action is finished, false otherwise
	 */
	public boolean isFinished () { return finished; }
}
