package mouseaction;

import mouse.Mouse;

public abstract class MouseAction {
	protected boolean finished;
	protected boolean calledInit;
	protected Mouse mouse;
	
	
	
	public MouseAction (Mouse mouse) {
		finished = false;
		calledInit = false;
		this.mouse = mouse;
	}
	
	public abstract void update (float dt);
	public boolean isFinished () { return finished; }
}
