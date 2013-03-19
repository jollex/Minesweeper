package com.jollex.Minesweeper;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

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

	public Controls() {
		setUp();
	}
	
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
