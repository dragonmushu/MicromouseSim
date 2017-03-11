package gui;

import static gui.GUIPanel.BUTTON_HEIGHT;
import static gui.GUIPanel.BUTTON_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Algorithm;
import maze.Maze;
import maze.MazeStorage;
import mouse.Mouse;


@SuppressWarnings("serial")
public class MazePanel extends JPanel implements Runnable{
	
	//thread variables
	public static final float FPS = 60.0f;
	private Thread thread;
	private boolean running;
	private float timeDelay; 
	
	//Mouse
	private Mouse mouse;
	
	//Algorithm
	private Algorithm algorithm;
	private ArrayList <Algorithm> allAlgorithms;
	private ArrayList <String> algoNames;
	private JComboBox <String> algorithmList;
	
	//Maze
	private MazeStorage storage;
	private Maze maze;
	
	//buttons
	private JButton restart;
	
	//text field
	private JComboBox <String> mazeList;
	private String [] mazeNames;
	public static final int COMBOBOX_WIDTH = 200;
	public static final int COMBOBOX_HEIGHT = 40;
	
	//slider for speed
	private JSlider speedSlider;
	private JSlider turnSlider;
	public static final int SLIDER_WIDTH = 300;
	public static final int SLIDER_HEIGHT = 50;
	
	
	/**
	 * This 
	 */
	public MazePanel (MazeStorage storage){
		
		//set size and initialize
		setPreferredSize (new Dimension(GUIPanel.WIDTH, GUIPanel.HEIGHT));
		setLayout(null);
		
		//maze
		this.storage = storage;
		mazeNames = storage.getMazeName();
		//gui
		mazeList = new JComboBox<String>(mazeNames) ;
		mazeList.setSelectedIndex(0);
		mazeList.setBackground(Color.WHITE);
		mazeList.setFont(new Font("Arial", Font.PLAIN, 20));
		mazeList.setForeground(Color.RED);
		mazeList.setBorder(new LineBorder(Color.RED, 5, true));
		int width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - COMBOBOX_WIDTH)/2;
		mazeList.setBounds(width, GUIPanel.HEIGHT/2-COMBOBOX_HEIGHT/2+100, COMBOBOX_WIDTH, COMBOBOX_HEIGHT);
		mazeList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox b = (JComboBox) e.getSource();
				String mazeFile = (String) b.getSelectedItem();
				
				for (int i = 0; i<mazeNames.length; i++){
					if (mazeNames[i].equals(mazeFile)){
						changeMaze(i);
						return;
					}
				}
			}
		});
		add(mazeList);
		maze = storage.getMaze(0);
		
		//mouse
		mouse = new Mouse(maze);
		
		//algorithm
		allAlgorithms = new ArrayList<Algorithm>();
		algoNames = new ArrayList<String>();
		try{
			File [] algoFiles = new File("src/algorithms").listFiles();
			for (File f: algoFiles){
				String name = f.getName();
				if (!name.equals("Algorithm.java")){
					allAlgorithms.add((Algorithm) Class.forName("algorithms."+name.substring(0, name.length()-5)).newInstance());
					name = allAlgorithms.get(allAlgorithms.size()-1).getName();
					if (name == null) throw new Exception();
					algoNames.add(name);
				}
			}
		}
		catch (Exception e){
			System.out.println("ADD ONLY ALGORITHMS THAT EXTEND ALGORITHM IN algorithms FOLDER");
			System.out.println("MAKE SURE VALID NAME FOR ALGO: NOT NULL");
			e.printStackTrace();
		}
		algorithmList = new JComboBox<String>();
		for (String s: algoNames) algorithmList.addItem(s);
		algorithmList.setSelectedIndex(0);
		algorithmList.setBackground(Color.WHITE);
		algorithmList.setFont(new Font("Arial", Font.PLAIN, 20));
		algorithmList.setForeground(Color.RED);
		algorithmList.setBorder(new LineBorder(Color.RED, 5, true));
		width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - COMBOBOX_WIDTH)/2;
		algorithmList.setBounds(width, GUIPanel.HEIGHT/2-COMBOBOX_HEIGHT/2+50, COMBOBOX_WIDTH, COMBOBOX_HEIGHT);
		algorithmList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox b = (JComboBox) e.getSource();
				String name = (String) b.getSelectedItem();
				
				for (int i = 0; i<algoNames.size(); i++){
					if (algoNames.get(i).equals(name)){
						changeAlgorithm(i);
						return;
					}
				}
			}
		});
		add(algorithmList);
		algorithm = allAlgorithms.get(0);
		algorithm.setMouse(mouse);
		
		
		//gui
		//button
		restart = new JButton("Restart");
		restart.setBackground(Color.WHITE);
		restart.setFont(new Font("Arial", Font.PLAIN, 20));
		restart.setForeground(Color.RED);
		restart.setBorder(new LineBorder(Color.RED, 5, true));
		width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - BUTTON_WIDTH)/2;
		restart.setBounds(width, GUIPanel.HEIGHT/2-BUTTON_HEIGHT/2+150, BUTTON_WIDTH, BUTTON_HEIGHT);
		restart.setFocusPainted(false);
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
			}
		});
		add(restart);
		
		
		//sliders
		speedSlider = new JSlider(JSlider.HORIZONTAL, 100, 400, 200);
		speedSlider.setMajorTickSpacing(50);
		speedSlider.setMinorTickSpacing(10);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setPaintTrack(false);
		speedSlider.setBackground(Color.WHITE);
		speedSlider.setForeground(Color.RED);
		speedSlider.setSnapToTicks(true);
		width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - SLIDER_WIDTH)/2;
		speedSlider.setBounds(width, GUIPanel.HEIGHT/2-SLIDER_HEIGHT/2-100, SLIDER_WIDTH, SLIDER_HEIGHT);
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				mouse.setVelocity(speedSlider.getValue()/1000.0);
			}
		});
		add(speedSlider);
		
		turnSlider = new JSlider(JSlider.HORIZONTAL, 100, 400, 200);
		turnSlider.setMajorTickSpacing(50);
		turnSlider.setMinorTickSpacing(10);
		turnSlider.setPaintTicks(true);
		turnSlider.setPaintLabels(true);
		turnSlider.setPaintTrack(false);
		turnSlider.setBackground(Color.WHITE);
		turnSlider.setForeground(Color.RED);
		turnSlider.setSnapToTicks(true);
		width = GUIPanel.HEIGHT+(GUIPanel.WIDTH - GUIPanel.HEIGHT - SLIDER_WIDTH)/2;
		turnSlider.setBounds(width, GUIPanel.HEIGHT/2-SLIDER_HEIGHT/2-50, SLIDER_WIDTH, SLIDER_HEIGHT);
		turnSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				mouse.setAngularVelocity(turnSlider.getValue()/1000.0);
			}
		});
		add(turnSlider);


		//thread
		timeDelay =  1000.0f/FPS;
		running = false;
		
		startThread();
	}
	
	/**
	 * 
	 */
	public void init () {
		running = true;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init ();
		long startTime, waitTime, delay;
		float dt = 0;
		while (running){
			startTime = System.nanoTime();
			
			update (dt);
			repaint();
			
			delay = (System.nanoTime()- startTime)/1000000;
			waitTime = (long) (timeDelay - delay);
			if (waitTime>0){
				try{
					thread.sleep(waitTime);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			
			dt = (System.nanoTime()- startTime)/1000000;
		}
	}
	
	/**
	 * 
	 */
	private void startThread (){
		if (thread==null){
			thread = new Thread (this);
			thread.start();
		}
	}
	
	/**
	 * 
	 * @param dt
	 */
	public void update (float dt){
		maze.update(dt);
		if (mouse.noActionsRunning() && algorithm.isRunning()) algorithm.update(dt);
		mouse.update(dt);
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GUIPanel.WIDTH, GUIPanel.HEIGHT);
		maze.render((Graphics2D) g);
		mouse.render((Graphics2D) g);
	}
	
	/**
	 * 
	 * @param index
	 */
	public void changeAlgorithm (int index){
		mouse.stopAllActions();
		algorithm = allAlgorithms.get(index);
		reset();
	}
	
	
	/**
	 * 
	 * @param index
	 */
	public void changeMaze (int index){
		mouse.stopAllActions();
		maze = storage.getMaze(index);
		reset();
	}
	
	/**
	 * 
	 */
	public void reset (){
		mouse.stopAllActions();
		mouse = new Mouse(maze);
		mouse.setVelocity(speedSlider.getValue()/1000.0);
		mouse.setAngularVelocity(turnSlider.getValue()/1000.0);
		algorithm.reset();
		algorithm.setMouse(mouse);
	}

}
