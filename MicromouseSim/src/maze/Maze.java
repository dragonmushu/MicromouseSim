/*
 * Akshay Ben 	
 * 10/24/2016
 */
package maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

//stores information about one maze
public class Maze {
	
	//fileName
	private String fileName;
	
	public static final int ROW = 16;
	public static final int COL = 16;
	
	public Cell [] [] mazeInfo;
	
	public Maze (String fileName) {
		this.fileName = fileName;
		mazeInfo = new Cell [16] [16];
		
		for (int r=0; r<ROW; r++){
			for (int c=0; c<COL; c++){
				mazeInfo [r][c] = new Cell ();
			}
		}
		
	}
	
	//get cell information at specific row and col
	public Cell getCell(int r, int c) {
		return mazeInfo [r][c]; 
	}
	
	public String getFileName () {
		return fileName;
	}
	
	
	//set cell with row and col and cell
	public void setCell (int r, int c, Cell cell){
		mazeInfo[r][c]= cell;
	}
	
	//set cell with row and col and cell info
	public void setCell (int r, int c, boolean left, boolean right, boolean down, boolean up){
		mazeInfo [r][c].setDown(down);
		mazeInfo[r][c].setLeft(left);
		mazeInfo[r][c].setUp(up);
		mazeInfo[r][c].setRight(right);
	}
	
	//set cell with row, col, direction, and boolean
	/*
	 * left = 0
	 * right = 1
	 * down = 2
	 * up = 3
	 */
	public void setCell (int r, int c, int direction, boolean wall){
		switch (direction){
		case 1:
			mazeInfo[r][c].setRight(wall);
			break;
		case 2:
			mazeInfo[r][c].setDown(wall);
			break;
		case 3:
			mazeInfo[r][c].setUp(wall);
			break;
		case 0:
			mazeInfo[r][c].setLeft(wall);
			break;
		}
	}
	
	
	public void update (float dt){
		
	}
	
	/**
	 * 
	 * @param g
	 */
	public void render (Graphics2D g){
		int stroke = 6;
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(stroke));
		
		//draw border walls
		for (int i=0; i<16; i++){
			g.drawLine(stroke/2, i*60, stroke/2, i*60+60);
			g.drawLine(60*16-stroke/2, i*60, 60*16-stroke/2, i*60+60);
			g.drawLine(i*60, stroke/2, i*60+60, stroke/2);
			g.drawLine(i*60, 60*16-stroke/2, i*60+60, 60*16-stroke/2);
		}
		
		//draw all other walls
		for (int i=0; i<16; i++){
			for (int t=1; t<16; t++){
				if (mazeInfo[i][t].getLeft()) g.drawLine(t*60, i*60, t*60, i*60+60);
			}
		}
		for (int i=1; i<16; i++){
			for (int t=0; t<16; t++){
				if (mazeInfo [i][t].getUp()) g.drawLine(t*60, i*60, t*60+60, i*60);
			}
		}
		
		
	}
	
	public void print () {
		//print left walls
		
	
	}
	
	

}
