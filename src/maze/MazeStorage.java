package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MazeStorage {
	private ArrayList <Maze> mazes; //stores the different mazes
	private Reader reader;
	
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
			FileReader reader = new FileReader("Stored Maze");
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line=br.readLine())!=null){
				if (line.equals("")) continue;
				mazes.add(new Reader(line).initializeMaze());
			}
			br.close();
			
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
