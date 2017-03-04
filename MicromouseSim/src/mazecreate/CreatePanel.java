package mazecreate;

import static gui.GUIPanel.BUTTON_HEIGHT;
import static gui.GUIPanel.BUTTON_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui.GUIPanel;

@SuppressWarnings("serial")
public class CreatePanel extends JPanel implements MouseListener{
	
	//walls for the maze 
	private Rectangle [] [] vertWalls;
	private Rectangle [] [] horWalls;
	
	//save button and file name fields
	private JButton saveButton;
	private JTextField fileName;
	
	//size for textField
	public static final int TEXTFIELD_WIDTH = 200;
	public static final int TEXTFIELD_HEIGHT = 50;
	
	
	
	
	/**
	 * 
	 */
	public CreatePanel () {
		//initialize dimensions and panel
		setPreferredSize(new Dimension(GUIPanel.WIDTH, GUIPanel.HEIGHT));
		setLayout(null);
		setBackground(Color.WHITE);
		
		//initialize walls
		vertWalls = new Rectangle [16] [17];
		horWalls = new Rectangle [17] [16];
		for (int i=0; i<16; i++){
			for(int t=0; t<17; t++){
				vertWalls [i][t] = new Rectangle(i, t, 0);
			}
		}
		for (int i=0; i<17; i++){
			for(int t=0; t<16; t++){
				horWalls [i][t] = new Rectangle(i, t, 1);
			}
		}
		
		//textField
		fileName = new JTextField("Enter Maze Name: ");
		int width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - TEXTFIELD_WIDTH)/2;
		fileName.setBounds(width, GUIPanel.HEIGHT/2-TEXTFIELD_HEIGHT/2 - 50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		fileName.setFont(new Font("Arial", Font.PLAIN, 20));
		add(fileName);
		
		//button
		saveButton = new JButton ("Save Maze");
		saveButton.setBackground(Color.WHITE);
		saveButton.setFont(new Font("Arial", Font.PLAIN, 20));
		saveButton.setForeground(Color.BLUE);
		saveButton.setBorder(new LineBorder(Color.BLUE, 5, true));
		width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - BUTTON_WIDTH)/2;
		saveButton.setBounds(width, GUIPanel.HEIGHT/2-BUTTON_HEIGHT/2 + 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save(fileName.getText());
				
			}
		});
		add(saveButton);
		
		//add mouse listener for mazes
		addMouseListener(this);
	}
	

	
	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		
		//paint the vert walls
		super.paintComponent(g);
		for (int i=0; i<16; i++){
			for(int t=0; t<17; t++) vertWalls[i][t].draw(g);
		}
		
		//paint the horizontal walls
		for (int i=0; i<17; i++){
			for(int t=0; t<16; t++) horWalls[i][t].draw(g);
		}
		
	}
	
	/**
	 * 
	 */
	public void save (String fileName){
		
		//make sure file name doesn't already exist
		if(fileNameExists(fileName)){
			this.fileName.setText("File Exists!");
			return;
		}
		
		//lines to add
		ArrayList <String> lines = new ArrayList<String>();
		
		//left walls
		String walls = "";
		for (int i=0; i<16; i++){
			for(int t=0; t<16; t++){
				if (vertWalls[i][t].wallPresent()) walls+="1 ";
				else walls+="0 ";
			}
			lines.add(walls);
			walls="";
		}
		lines.add("\n");
		
		//right walls
		walls = "";
		for (int i=0; i<16; i++){
			for(int t=1; t<17; t++){
				if (vertWalls[i][t].wallPresent()) walls+="1 ";
				else walls+="0 ";
			}
			lines.add(walls);
			walls="";
		}
		lines.add("\n");	
		
		//bottom walls
		for (int i=1; i<17; i++){
			for(int t=0; t<16; t++){
				if (horWalls[i][t].wallPresent()) walls+="1 ";
				else walls+="0 ";
			}
			lines.add(walls);
			walls="";
		}
		lines.add("\n");	
		
		//top walls
		for (int i=0; i<16; i++){
			for(int t=0; t<16; t++){
				if (horWalls[i][t].wallPresent()) walls+="1 ";
				else walls+="0 ";
			}
			lines.add(walls);
			walls="";
		}
		
		//create new file
		try {
			Files.write(Paths.get(fileName+".txt"), lines, StandardCharsets.UTF_8);
			saveFileName(fileName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean fileNameExists (String fileName){
		try{
			FileReader reader = new FileReader("Stored Maze");
			BufferedReader br = new BufferedReader (reader);
			String line;
			while ((line=br.readLine())!=null){
				if (line.equals(fileName)){
					br.close();
					return true;
				}
			}
			br.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void saveFileName (String fileName){
		try{
			FileWriter writer = new FileWriter("Stored Maze", true);
			writer.append(fileName+"\n");
			writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i=0; i<17; i++){
			for(int t=0; t<16; t++) vertWalls[t][i].clickWithinBounds(e.getX(), e.getY());
		}
		
		for (int i=0; i<17; i++){
			for(int t=0; t<16; t++) horWalls[i][t].clickWithinBounds(e.getX(), e.getY());
		}
		repaint();
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
