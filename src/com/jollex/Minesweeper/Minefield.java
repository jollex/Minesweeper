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
	private JLabel buttons[][] = new JLabel[rows][cols];
	private boolean[][] bomb = new boolean[rows][cols];
	private boolean shown[][] = new boolean[rows][cols];
	private boolean marked[][] = new boolean[rows][cols];
	private boolean flagged[][] = new boolean[rows][cols];
	private final int BOMB_AMOUNT = 10;
	private boolean gameStarted = false;
	private int markedAmount = 0;
	
	private ImageIcon blank = new javax.swing.ImageIcon(getClass().getResource("images/blank.gif"));
	private ImageIcon bombFlagged = new javax.swing.ImageIcon(getClass().getResource("images/bombflagged.gif"));
	private ImageIcon bombRevealed = new javax.swing.ImageIcon(getClass().getResource("images/bombrevealed.gif"));
	private ImageIcon bombDeath = new javax.swing.ImageIcon(getClass().getResource("images/bombdeath.gif"));

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
		
		//Populates the buttons array with JLabels, sets them to the blank.gif icon, and adds them to the panel
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setIcon(blank);
				buttons[row][col].setBorder(BorderFactory.createEmptyBorder());
				
				buttons[row][col].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
						if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
							markCell(e);
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							if (gameStarted == false) {
								startGame(e);
							}
							openCell(e);
							openAllMarked();
						}
					}
				});
				
				c.gridx = row;
				c.gridy = col;
				mines.add(buttons[row][col], c);
				
				flagged[row][col] = false;
				marked[row][col] = false;
				bomb[row][col] = false;
				shown[row][col] = false;
			}
		}
		
		this.setVisible(true);
	}
	
	private void startGame(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		
		gameStarted = true;
		//timer.start();
		createBombs(row, col);
	}
	
	//Returns the amount of bombs around a cell
	private int bombCount(int row, int col){
		int bombCount = 0;
		for(int x = -1; x <= 1; x++){
			for(int y = -1; y <= 1; y++) {
				int i = row + x;
				int j = col + y;
 
				if(i >= 0 && i < bomb.length && j >= 0 && j < bomb[i].length && isBomb(i, j)) {
					bombCount++;
				}
			}
		}
		return bombCount;
	}
	
	private void createBombs(int row, int col) {
		Random generator = new Random();
		int bombs = 0;
		
		while (bombs < BOMB_AMOUNT - 1) {
			int r = generator.nextInt(rows);
			int c = generator.nextInt(cols);
			
			if (bomb[r][c] == false && r != row && r != row-1 && r!= row+1 && c != col && c != col-1 && c != col+1) {
				bomb[r][c] = true;
				bombs++;
			}
		}
	}
	
	private void markCell(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;

		if (!flagged[row][col]) {
			flagged[row][col] = true;
			button.setIcon(bombFlagged);
		} else {
			flagged[row][col] = false;
			button.setIcon(blank);
		}
	}

	
	private void openCell(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		int bombAmount = bombCount(row, col);
		if (isBomb(row, col)) {
			endGame(row, col);
		} else {
			buttons[row][col].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open" + bombAmount + ".gif")));
			shown[row][col] = true;
			if (!isBomb(row, col) && (bombAmount == 0)) {
				openNeighbors(row, col);
			}
		}
	}
	
	private void openNeighbors(int row, int col) {
		for (int i = row-1; i <= row+1; i++) {
			for (int j = col-1; j <= col+1; j++) {
				if (i >= 0 && i < buttons.length && j >= 0 && j < buttons[i].length) {
					if (!shown[i][j] && !flagged[i][j]) {
						markCellToOpen(i, j);
					}
				}
			}
		}
	}
	
	private void markCellToOpen(int row, int col) {
		markedAmount++;
		marked[row][col] = true;
	}
	
	private void openAllMarked() {
		while (markedAmount > 0) {
			markedAmount--;
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					if (marked[row][col] == true) {
						int bombAmount = bombCount(row, col);
						marked[row][col] = false;
						buttons[row][col].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open" + bombAmount + ".gif")));
					}
				}
			}
		}
	}
	
	private boolean isBomb(int row, int col) {
		return bomb[row][col];
	}
	
	private void endGame(int row, int col) {
		System.out.println("Hit bomb.");
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				String button = buttons[row][col].getIcon().toString();
				String flag = bombFlagged.toString();
				if (bomb[row][col] && button.equals(flag)) {
					buttons[row][col].setIcon(bombRevealed);
				} else if (!bomb[row][col] && button.equals(flag)) {
					buttons[row][col].setIcon(bombFlagged);
				}
			}
		}
		buttons[row][col].setIcon(bombDeath);
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
