/**
 * Class used to create main frame 
 * base layer that controls all other
 * panels 
 * 
 * @author Akshay
 */

package gui;

import javax.swing.JFrame;

import algorithms.Algorithm;


/**
 * sets the content pane to be the initial
 * panel which is the GUI Panel
 * 
 * Sets title, and visibility
 */
@SuppressWarnings("serial")
public class GUIFrame extends JFrame{
	
	public GUIFrame (Algorithm algo) {
		setTitle ("Maze Simulator");
		setContentPane(new GUIPanel(this, algo));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	
}
