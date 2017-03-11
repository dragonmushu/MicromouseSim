/**
 * Main Panel that is used to 
 * allow User to direct to the create page 
 * or the maze sim page
 * 
 * @author Akshay
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import maze.MazeStorage;
import mazecreate.CreatePanel;

@SuppressWarnings("serial")
public class GUIPanel extends JPanel{
	
	//frame
	private JFrame frame;
	
	//size constants
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 980;
	
	//button size
	public static final int BUTTON_WIDTH = 200;
	public static final int BUTTON_HEIGHT = 50;
	
	//buttons 
	private JButton create;
	private JButton maze;
	
	
	/**
	 * 
	 * This initializes the main GUI panel that is used to allow user
	 * to run algorithm simulation or the redirect to the
	 * creation panel
	 * 
	 * COLOR SCHEME : RED and WHITE
	 * 
	 * @param frame used to change between different panels
	 */
	public GUIPanel (JFrame frame) {
		this.frame = frame;
		setPreferredSize (new Dimension(WIDTH, HEIGHT));
		setLayout(null);
		setBackground(Color.WHITE);
		
		//setup buttons
		
		//create button
		create = new JButton("Create Maze");
		create.setBackground(Color.WHITE);
		create.setFont(new Font("Arial", Font.PLAIN, 20));
		create.setForeground(Color.RED);
		create.setBorder(new LineBorder(Color.RED, 5, true));
		create.setBounds(WIDTH/2-BUTTON_WIDTH/2, HEIGHT/2-BUTTON_HEIGHT/2-50, BUTTON_WIDTH, BUTTON_HEIGHT);
		create.setFocusPainted(false);
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeToCreate();
			}
		});
		
		//maze button
		maze = new JButton("Run");
		maze.setBackground(Color.WHITE);
		maze.setFont(new Font("Arial", Font.PLAIN, 20));
		maze.setForeground(Color.RED);
		maze.setBorder(new LineBorder(Color.RED, 5, true));
		maze.setBounds(WIDTH/2-BUTTON_WIDTH/2, HEIGHT/2-BUTTON_HEIGHT/2+50, BUTTON_WIDTH, BUTTON_HEIGHT);
		maze.setFocusPainted(false);
		maze.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeToMaze();
			}
		});
		
		//add to panel
		add(create);
		add(maze);
		
	}
	
	/**
	 * changes to the create panel where user can
	 * create different mazes
	 * 
	 * @return true if the operation worked 
	 */
	public boolean changeToCreate (){
		frame.setContentPane(new CreatePanel());
		frame.pack();
		frame.getContentPane().setFocusable(true);
		frame.getContentPane().requestFocus();
		return true;
	}
	
	/**
	 * changes to the maze panel where user can run
	 * simulation
	 * 
	 * @return true if the operation worked
	 */
	public boolean changeToMaze (){
		MazeStorage storage = new MazeStorage();
		frame.setContentPane(new MazePanel(storage));
		frame.pack();
		frame.getContentPane().setFocusable(true);
		frame.getContentPane().requestFocus();
		return true;
	}
	
	
	
	
}
