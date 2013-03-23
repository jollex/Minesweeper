package com.jollex.Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Panels are created for each component
	private JPanel buttons;
	
	private JPanel main;
	private JPanel topBorder;
	private JPanel leftBorder;
	private JPanel rightBorder;
	private JPanel bottomBorder;
	
	//JLabels for each difficulty button
	private JLabel easy;
	private JLabel mid;
	private JLabel hard;
	
	//All border images are loaded
	private ImageIcon bordertl = new javax.swing.ImageIcon(getClass().getResource("images/bordertl.gif"));
	private ImageIcon bordertb = new javax.swing.ImageIcon(getClass().getResource("images/bordertb.gif"));
	private ImageIcon bordertr = new javax.swing.ImageIcon(getClass().getResource("images/bordertr.gif"));
	private ImageIcon borderlr = new javax.swing.ImageIcon(getClass().getResource("images/borderlr.gif"));
	private ImageIcon borderlr1 = new javax.swing.ImageIcon(getClass().getResource("images/borderlr1.gif"));
	private ImageIcon borderjointl = new javax.swing.ImageIcon(getClass().getResource("images/borderjointl.gif"));
	private ImageIcon borderjointr = new javax.swing.ImageIcon(getClass().getResource("images/borderjointr.gif"));
	private ImageIcon borderbl = new javax.swing.ImageIcon(getClass().getResource("images/borderbl.gif"));
	private ImageIcon borderbr = new javax.swing.ImageIcon(getClass().getResource("images/borderbr.gif"));
	private ImageIcon button = new javax.swing.ImageIcon(getClass().getResource("images/button.gif"));
	private ImageIcon buttonpressed = new javax.swing.ImageIcon(getClass().getResource("images/buttonpressed.gif"));
	
	//Creates frame
	public Screen() {
		super("Minesweeper");
		setUpButtons();
		setUpBorders();
		setUp();
	}
	
	//Creates difficulty button layout
	private void setUpButtons() {
		//Sets up buttons panel
		buttons = new JPanel();
		buttons.setSize(148, 24);
		
		easy = new JLabel(buttonpressed);
		easy.setText("Easy");
		easy.setIconTextGap(-38);
		easy.setBorder(BorderFactory.createEmptyBorder());
		buttons.add(easy);
		
		mid = new JLabel(button);
		mid.setText("Mid");
		mid.setIconTextGap(-36);
		mid.setBorder(BorderFactory.createEmptyBorder());
		buttons.add(mid);
		
		hard = new JLabel(button);
		hard.setText("Hard");
		hard.setIconTextGap(-38);
		hard.setBorder(BorderFactory.createEmptyBorder());
		buttons.add(hard);
	}
	
	//Creates all borders
	private void setUpBorders() {
		//Set up top border
		topBorder = new JPanel();
		topBorder.setSize(148, 10);
		GridBagLayout top = new GridBagLayout();
		GridBagConstraints t = new GridBagConstraints();
		topBorder.setLayout(top);
		t.gridx = 0;
		topBorder.add(new JLabel(bordertl), t);
		for (int i = 1; i < 33; i++) {
			t.gridx = i;
			topBorder.add(new JLabel(bordertb), t);
		}
		t.gridx = 34;
		topBorder.add(new JLabel(bordertr), t);
		
		//Set up left border
		leftBorder = new JPanel();
		leftBorder.setSize(10, 164);
		GridBagLayout left = new GridBagLayout();
		GridBagConstraints l = new GridBagConstraints();
		leftBorder.setLayout(left);
		for (int i = 0; i < 8; i++) {
			l.gridy = i;
			leftBorder.add(new JLabel(borderlr), l);
		}
		l.gridy = 9;
		leftBorder.add(new JLabel(borderlr1), l);
		l.gridy = 10;
		leftBorder.add(new JLabel(borderjointl), l);
		for (int i = 11; i < 45; i++) {
			l.gridy = i;
			leftBorder.add(new JLabel(borderlr), l);
		}
		
		//Set up right border
		rightBorder = new JPanel();
		rightBorder.setSize(10, 164);
		GridBagLayout right = new GridBagLayout();
		GridBagConstraints r = new GridBagConstraints();
		rightBorder.setLayout(right);
		for (int i = 0; i < 8; i++) {
			r.gridy = i;
			rightBorder.add(new JLabel(borderlr), r);
		}
		r.gridy = 9;
		rightBorder.add(new JLabel(borderlr1), r);
		r.gridy = 10;
		rightBorder.add(new JLabel(borderjointr), r);
		for (int i = 11; i < 45; i++) {
			r.gridy = i;
			rightBorder.add(new JLabel(borderlr), r);
		}
		
		//Set up bottom order
		bottomBorder = new JPanel();
		bottomBorder.setSize(148, 10);
		GridBagLayout bottom = new GridBagLayout();
		GridBagConstraints b = new GridBagConstraints();
		bottomBorder.setLayout(bottom);
		b.gridy = 0;
		b.gridx = 0;
		bottomBorder.add(new JLabel(borderbl), b);
		for (int i = 1; i < 33; i++) {
			b.gridx = i;
			bottomBorder.add(new JLabel(bordertb), b);
		}
		b.gridx = 34;
		bottomBorder.add(new JLabel(borderbr), b);
	}
	
	private void setUp() {
		//Sets up frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(148, 230);
		this.setBackground(Color.RED);
		this.setResizable(false);
		
		//Sets up main panel
		main = new JPanel();
		main.setSize(148, 206);
		
		//Adds top border to frame
		main.add(topBorder, BorderLayout.PAGE_START);
		
		//Adds left border to frame
		main.add(leftBorder, BorderLayout.LINE_START);
		
		//Adds the minefield panel to the frame
		Minefield field = new Minefield();
		main.add(field, BorderLayout.CENTER);
		
		//Adds right border to frame
		main.add(rightBorder, BorderLayout.LINE_END);
		
		//Adds bottom border to frame
		main.add(bottomBorder, BorderLayout.PAGE_END);
		
		//Adds main and button panels to frame
		this.add(buttons, BorderLayout.PAGE_START);
		this.add(main, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}
}
