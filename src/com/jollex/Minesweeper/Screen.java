package com.jollex.Minesweeper;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Panels are created for each component
	private JPanel buttons;
	
	private JPanel game = new JPanel();
	private JPanel main = new JPanel();
	private JPanel topBorder = new JPanel();
	private JPanel leftBorder = new JPanel();
	private JPanel rightBorder = new JPanel();
	private JPanel bottomBorder = new JPanel();
	private JPanel middleComps = new JPanel();
	
	//JLabels for each difficulty button
	private JLabel easy;
	private JLabel mid;
	private JLabel hard;
	
	//All border images are loaded
	private ImageIcon bordertl = new javax.swing.ImageIcon(getClass().getResource("images/bordertl.gif"));
	private ImageIcon bordertb = new javax.swing.ImageIcon(getClass().getResource("images/bordertb.gif"));
	private ImageIcon bordertr = new javax.swing.ImageIcon(getClass().getResource("images/bordertr.gif"));
	private ImageIcon borderlr = new javax.swing.ImageIcon(getClass().getResource("images/borderlr.gif"));
	private ImageIcon borderlr26 = new javax.swing.ImageIcon(getClass().getResource("images/borderlr26.gif"));
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
		newGame(0);
	}
	
	private void newGame(int gameType) {
		int x, y, bombAmount;
		switch (gameType) {
		case 0:
			x = 8;
			y = 8;
			bombAmount = 10;
			break;
		case 1:
			x = 16;
			y = 16;
			bombAmount = 40;
			break;
		case 2:
			x = 30;
			y = 16;
			bombAmount = 99;
			break;
		default:
			x = 8;
			y = 8;
			bombAmount = 10;
			break;
		}
		setUpBorders(x, y);
		setUp(x, y, bombAmount);
	}
	
	//Creates difficulty button layout
	private void setUpButtons() {
		//Sets up buttons panel
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setSize(148, 24);
		
		easy = new JLabel(button);
		easy.setText("Easy");
		easy.setIconTextGap(-38);
		easy.setBorder(BorderFactory.createEmptyBorder());
		easy.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				easy.setIcon(buttonpressed);
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				easy.setIcon(button);
				newGame(0);
			}
		});
		buttons.add(easy);
		
		mid = new JLabel(button);
		mid.setText("Mid");
		mid.setIconTextGap(-36);
		mid.setBorder(BorderFactory.createEmptyBorder());
		mid.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				easy.setIcon(buttonpressed);
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				easy.setIcon(button);
				newGame(1);
			}
		});
		buttons.add(mid);
		
		hard = new JLabel(button);
		hard.setText("Hard");
		hard.setIconTextGap(-38);
		hard.setBorder(BorderFactory.createEmptyBorder());
		hard.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				easy.setIcon(buttonpressed);
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				easy.setIcon(button);
				newGame(2);
			}
		});
		buttons.add(hard);
	}
	
	//Creates all borders
	private void setUpBorders(int x, int y) {
		//Set up top border
		topBorder.removeAll();
		GridBagLayout top = new GridBagLayout();
		GridBagConstraints t = new GridBagConstraints();
		topBorder.setLayout(top);
		t.gridx = 0;
		topBorder.add(new JLabel(bordertl), t);
		for (int i = 1; i <= x; i++) {
			t.gridx = i;
			topBorder.add(new JLabel(bordertb), t);
		}
		t.gridx = x + 1;
		topBorder.add(new JLabel(bordertr), t);
		
		//Set up left border
		leftBorder.removeAll();
		GridBagLayout left = new GridBagLayout();
		GridBagConstraints l = new GridBagConstraints();
		leftBorder.setLayout(left);
		l.gridy = 0;
		leftBorder.add(new JLabel(borderlr26), l);
		l.gridy = 1;
		leftBorder.add(new JLabel(borderjointl), l);
		for (int i = 2; i <= y + 1; i++) {
			l.gridy = i;
			leftBorder.add(new JLabel(borderlr), l);
		}
		
		//Set up right border
		rightBorder.removeAll();
		GridBagLayout right = new GridBagLayout();
		GridBagConstraints r = new GridBagConstraints();
		rightBorder.setLayout(right);
		r.gridy = 0;
		rightBorder.add(new JLabel(borderlr26), r);
		r.gridy = 1;
		rightBorder.add(new JLabel(borderjointr), r);
		for (int i = 2; i <= y + 1; i++) {
			r.gridy = i;
			rightBorder.add(new JLabel(borderlr), r);
		}
		
		//Set up bottom order
		bottomBorder.removeAll();
		GridBagLayout bottom = new GridBagLayout();
		GridBagConstraints b = new GridBagConstraints();
		bottomBorder.setLayout(bottom);
		b.gridy = 0;
		b.gridx = 0;
		bottomBorder.add(new JLabel(borderbl), b);
		for (int i = 1; i <= x; i++) {
			b.gridx = i;
			bottomBorder.add(new JLabel(bordertb), b);
		}
		b.gridx = x + 1;
		bottomBorder.add(new JLabel(borderbr), b);
	}
	
	private void setUp(int x, int y, int bombAmount) {
		//Sets up frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize((x * 16) + 20, (y * 16) + 102);
		this.setBackground(Color.RED);
		this.setResizable(false);
		
		game.removeAll();
		game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
		
		//Sets up main panel
		main.removeAll();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		//Adds top border to frame
		main.add(topBorder);
		
		middleComps.removeAll();
		middleComps.setLayout(new BoxLayout(middleComps, BoxLayout.X_AXIS));
		
		//Adds left border to frame
		middleComps.add(leftBorder);
		
		//Adds the minefield panel to the frame
		Minefield field = new Minefield(x, y, bombAmount);
		middleComps.add(field);
		
		//Adds right border to frame
		middleComps.add(rightBorder);
		
		main.add(middleComps);
		
		//Adds bottom border to frame
		main.add(bottomBorder);
		
		//Adds main and button panels to frame
		game.add(buttons);
		game.add(main);
		
		this.add(game);
		this.setVisible(true);
	}
}
