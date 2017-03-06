package mazecreate;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle {
	
	private int row;
	private int col;
	
	private int xMin;
	private int yMin;
	private int width;
	private int height;
	
	private boolean fill;
	
	
	/**
	 * 0 - left
	 * 1 - top
	 */
	private int location;
	
	public Rectangle (int row, int col, int location){
		this.row = row;
		this.col = col;
		this.location = location;
		
		int x = col;
		int y;
		
		fill = false;
		
		
		
		switch (location){
		case 0:
			if (col==0 || col==16) fill = true;
			y = row;
			xMin = x*60;
			yMin = y*60;
			width = 10;
			height = 70;
			break;
		case 1:
			if (row ==0 || row == 16) fill = true;
			y = row;
			xMin = x*60;
			yMin = y*60;
			width = 70;
			height = 10;
			break;
		}
	}
	
	
	public boolean clickWithinBounds (double x, double y) {
		boolean click = (x>=xMin && x<=xMin+width && y>=yMin && y<=yMin+height);
		if (click){
			fill = !fill;
		}
		
		return click;
	}
	
	public void draw (Graphics g){
		g.setColor(Color.RED);
		if (!fill) g.drawRect(xMin, yMin, width, height);
		else g.fillRect(xMin, yMin, width, height);
	}
	
	public int getRow () { return row; }
	public int getCol () { return col; }
	public boolean wallPresent () { return fill; }
	
	
}
