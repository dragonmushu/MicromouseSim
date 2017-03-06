package maze;

import java.io.File;
import java.util.Scanner;



public class Reader {
	Scanner textReader; //reads the text file
	
	// initialize files
	// run first and store as mazes
	private String fileName;

	public Reader (){
		
	}
	
	public Reader (String fileName){
		this.fileName = fileName;
		File file = new File(fileName+".txt");
		try {
			textReader = new Scanner(file); 
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	

	public void setFile (File file) {
		try{
			textReader = new Scanner (file);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	//reads the text file initializes the maze
	//returns a copy of the maze
	public Maze initializeMaze (){
		Maze maze = new Maze (fileName);
		
		//read all the left 
		for(int r=0; r<Maze.ROW; r++){
			for (int c=0; c<Maze.COL; c++){
				boolean b = textReader.nextInt()==0?false:true;
				maze.setCell(r, c, 0, b);
			}
			
		}
		
		//read all the right 
		for(int r=0; r<Maze.ROW; r++){
			for (int c=0; c<Maze.COL; c++){
				boolean b =textReader.nextInt()==0?false:true;
				maze.setCell(r, c, 1, b);
			}
		}

		//read all the down
		for(int r=0; r<Maze.ROW; r++){
			for (int c=0; c<Maze.COL; c++){
				boolean b =textReader.nextInt()==0?false:true;
				maze.setCell(r, c, 2, b);
			}
		}

		//read all the up 
		for(int r=0; r<Maze.ROW; r++){
			for (int c=0; c<Maze.COL; c++){
				boolean b =textReader.nextInt()==0?false:true;
				maze.setCell(r, c, 3, b);
			}
		}
		
		return maze;
	}
	
}
