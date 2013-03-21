package com.jollex.Minesweeper;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;

public class Controls extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Container controlPanel = null;
	private JLabel bombs1;
	private JLabel bombs2;
	private JLabel bombs3;
	private JLabel time1;
	private JLabel time2;
	private JLabel time3;
	private JButton face;
	
	private int time = 0;
	private int bombsFlagged = 10;
	
	private ImageIcon faceOoh = new javax.swing.ImageIcon(getClass().getResource("images/faceooh.gif"));
	private ImageIcon faceSmile = new javax.swing.ImageIcon(getClass().getResource("images/facesmile.gif"));

	public Controls() {
		setUp();
	}
	
	//Create timer with an ActionListener
	ActionListener updater = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time >= 999) {
				timer.stop();
			}
			time++;
			updateTime(time);
		}
	};
	Timer timer = new Timer(1000, updater);
	
	private void setUp() {
		//Sets size of JPanel
		this.setSize(128, 26);
		
		//Sets JPanel to use GridBagLayout
		controlPanel = this;
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		controlPanel.setLayout(gridBag);
		
		//Load bomb counter icons
		bombs1 = new JLabel();
		bombs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		c.gridx = 0;
		this.add(bombs1, c);

		bombs2 = new JLabel();
		bombs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time1.gif")));
		c.gridx = 1;
		this.add(bombs2, c);
		
		bombs3 = new JLabel();
		bombs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		c.gridx = 2;
		this.add(bombs3, c);
		
		//Load face button and adds ActionListener to it
		face = new JButton();
		face.setIcon(faceSmile);
		face.setBorder(BorderFactory.createEmptyBorder());
		ActionListener faceClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
					newGame();
				}
			}
		};
		face.addActionListener(faceClick);
		c.gridx = 3;
		this.add(face, c);
		
		//Load timer icons
		time1 = new JLabel();
		time1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		c.gridx = 4;
		this.add(time1, c);
		
		time2 = new JLabel();
		time2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		c.gridx = 5;
		this.add(time2, c);
		
		time3 = new JLabel();
		time3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		c.gridx = 6;
		this.add(time3, c);
		
		//Sets JFrame to be visible
		this.setVisible(true);
	}
	
	//Restarts the game
	public void newGame() {
		time = 0;
		timer.start();
	}
	
	//Updates the timer display with the given 3 digit integer.
	public void updateTime(int time) {
		int hundreds, tens, ones;
		hundreds = time / 100;
		tens = (time % 100) / 10;
		ones = ((time % 100) % 10);
		
		time1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + hundreds + ".gif")));
		time2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + tens + ".gif")));
		time3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + ones + ".gif")));
	}
	
	public void updateCounter(int change) {
		bombsFlagged += change;
		
		int hundreds, tens, ones;
		hundreds = bombsFlagged / 100;
		tens = (bombsFlagged % 100) / 10;
		ones = ((bombsFlagged % 100) % 10);
		
		bombs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + hundreds + ".gif")));
		bombs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + tens + ".gif")));
		bombs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + ones + ".gif")));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
