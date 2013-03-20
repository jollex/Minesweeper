package com.jollex.Minesweeper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel topLeft;
	private JLabel topRight;
	private JLabel jointLeft;
	private JLabel jointRight;
	private JLabel bottomLeft;
	private JLabel bottomRight;
	private JLabel top;
	private JLabel side;
	
	public Screen() {
		super("Minesweeper");
		setUp();
	}
	
	private void setUp() {
		//Sets up frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(148, 184);
		
		//Sets frame to use GridBagLayout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridBag);
		
		//Load borders
		topLeft = new JLabel();
		topLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/bordertl.gif")));
		topRight = new JLabel();
		topRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/bordertr.gif")));
		jointLeft = new JLabel();
		jointLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/borderjointl.gif")));
		jointRight = new JLabel();
		jointRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/borderjointr.gif")));
		bottomLeft = new JLabel();
		bottomLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/borderbl.gif")));
		bottomRight = new JLabel();
		bottomRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/borderbr.gif")));
		top = new JLabel();
		top.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/bordertb.gif")));
		side = new JLabel();
		side.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/borderlr.gif")));
		
		//Adds the controls panel to the frame
		Controls control = new Controls();
		c.gridx = 0;
		c.gridy = 0;
		this.add(control, c);
		
		//Adds the minefield panel to the frame
		Minefield field = new Minefield();
		c.gridx = 0;
		c.gridy = 1;
		this.add(field, c);
		
		this.setVisible(true);
	}
}
