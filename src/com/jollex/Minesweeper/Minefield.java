package com.jollex.Minesweeper;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class Minefield extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Container mines = null;
	private int rows = 8, cols = 8;
	private JToggleButton buttons[][] = new JToggleButton[rows][cols];
	private int cells[][] = new int[rows][cols];
	private boolean shown[][] = new boolean[rows][cols];

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
							//markCell(e);
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							//showCell(e);
						}
					}
				});
				
				c.gridx = row;
				c.gridy = col;
				mines.add(buttons[row][col], c);
				
				cells[row][col] = bombCount(row, col);
				shown[row][col] = false;
			}
		}
		
		this.setVisible(true);
	}
	
	//Returns the amount of bombs around a cell
	private int bombCount(int x, int y){
		int bombCount = 0;
		for(int row = -1; row <= 1; row++){
			for(int col = -1; col <= -1; col++) {
				int i = x + row;
				int j = y + col;
 
				if(i >= 0 && i < cells.length && j >= 0 && j < cells[i].length){
					bombCount++;
				}
			}
		}
		return bombCount;
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
