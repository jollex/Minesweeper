package com.jollex.Minesweeper;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.*;

public class Minefield extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Container mines = null;
	private int rows = 8, cols = 8;
	private JToggleButton buttons[][] = new JToggleButton[rows][cols];
	private boolean[][] bomb = new boolean[rows][cols];
	private boolean shown[][] = new boolean[rows][cols];
	private final int BOMB_AMOUNT = 10;

	public Minefield() {
		setUp();
	}
	
	//Sets up the minefield
	private void setUp() {
		//Sets the size of the minefield
		this.setSize(128, 128);
		
		//Sets the JPanel to use GridBagLayout
		mines = this;
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		mines.setLayout(gridBag);
		
		//Populates the buttons array with JToggleButtons, sets them to the blank.gif icon, and adds them to the panel
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				//Populate mine field
				buttons[row][col] = new JToggleButton();
				buttons[row][col].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/blank.gif")));
				buttons[row][col].setBorder(BorderFactory.createEmptyBorder());
				
				buttons[row][col].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
						if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
							markCell(e);
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							showCell(e);
						}
					}
				});
				
				c.gridx = row;
				c.gridy = col;
				mines.add(buttons[row][col], c);
				
				bomb[row][col] = false;
				shown[row][col] = false;
			}
		}
		
		createBombs();
		this.setVisible(true);
	}
	
	//Returns the amount of bombs around a cell
	private int bombCount(int x, int y){
		int bombCount = 0;
		for(int row = -1; row <= 1; row++){
			for(int col = -1; col <= -1; col++) {
				int i = x + row;
				int j = y + col;
 
				if(i >= 0 && i < bomb.length && j >= 0 && j < bomb[i].length && isBomb(i, j)) {
					bombCount++;
				}
			}
		}
		return bombCount;
	}
	
	private void createBombs() {
		Random generator = new Random();
		int bombs = 0;
		
		while (bombs < BOMB_AMOUNT) {
			int r = generator.nextInt(rows);
			int c = generator.nextInt(cols);
			
			if (!bomb[r][c]) {
				bomb[r][c] = true;
				bombs++;
			}
		}
	}
	
	private void markCell(java.awt.event.MouseEvent e) {
		JToggleButton button = (JToggleButton)e.getSource();
		
		ImageIcon blank = new javax.swing.ImageIcon(getClass().getResource("images/blank.gif"));
		ImageIcon flag = new javax.swing.ImageIcon(getClass().getResource("images/bombflagged.gif"));
		ImageIcon buttonIcon = (ImageIcon)button.getIcon();
		
		String buttonString = buttonIcon.toString();
		String blankString = blank.toString();
		String flagString = flag.toString();
		
		if (buttonString.equals(blankString)) {
			button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/bombflagged.gif")));
		} else if (buttonString.equals(flagString)) {
			button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/blank.gif")));
		}
	}
	
	private void showCell(java.awt.event.MouseEvent e) {
		JToggleButton button = (JToggleButton)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		int bombAmount = bombCount(row, col);
		
		if (isBomb(row, col)) {
			//endGame();
			//disableBoard();
		} else {
			switch (bombAmount) {
			case 0: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open0.gif"))); break;
			case 1: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open1.gif"))); break;
			case 2: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open2.gif"))); break;
			case 3: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open3.gif"))); break;
			case 4: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open4.gif"))); break;
			case 5: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open5.gif"))); break;
			case 6: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open6.gif"))); break;
			case 7: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open7.gif"))); break;
			case 8: button.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open8.gif"))); break;
			}
		}
	}
	
	private boolean isBomb(int row, int col) {
		return bomb[row][col];
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
