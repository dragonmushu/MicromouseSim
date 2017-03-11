package maze;

import java.io.File;
import java.util.ArrayList;

public class MazeStorage {
	private ArrayList <Maze> mazes; //stores the different mazes

	//array of maze names
	private String [] mazeNames;
	
	/**
	 * 
	 */
	public MazeStorage (){
		//instantiate list of mazes
		mazes = new ArrayList <Maze> ();
		
		//add new mazes
		try{
			File [] files = new File("src/storedmazes").listFiles();
			for(File f: files){
				mazes.add(new Reader(f.getName()).initializeMaze());
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		mazeNames = new String [mazes.size()];
		for (int i = 0; i<mazeNames.length; i++) mazeNames[i] = mazes.get(i).getFileName();
		
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Maze getMaze (int index){
		return mazes.get(index);
	}
	
	/**
	 * 
	 * @return
	 */
	public String []  getMazeName (){
		return mazeNames;
	}
	
	
	
}
