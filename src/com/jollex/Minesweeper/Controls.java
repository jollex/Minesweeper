package com.jollex.Minesweeper;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	private JToggleButton face;
	
	private int time = 0;

	public Controls() {
		setUp();
	}
	
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
		this.setSize(128, 26);
		
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
		
		//Load face picture
		face = new JToggleButton();
		face.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/facesmile.gif")));
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
		
		this.setVisible(true);
	}
	
	public void newGame() {
		time = 0;
		timer.start();
	}
	
	public void updateTime(int time) {
		int hundreds, tens, ones;
		hundreds = time / 100;
		tens = (time % 100) / 10;
		ones = ((time % 100) % 10);
		
		time1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + hundreds + ".gif")));
		time2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + tens + ".gif")));
		time3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + ones + ".gif")));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
