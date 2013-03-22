package com.jollex.Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.*;

public class Minefield extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//Creates container to put panels in
	private Container game = null;
	
	//Creates panels for game
	private JPanel mines = null;
	private JPanel border = null;
	private JPanel controlPanel = null;
	
	//Game settings
	private int rows = 8, cols = 8;
	private final int BOMB_AMOUNT = 10;
	
	//All cell related arrays
	private JLabel buttons[][] = new JLabel[rows][cols];
	private boolean[][] bomb = new boolean[rows][cols];
	private boolean shown[][] = new boolean[rows][cols];
	private boolean marked[][] = new boolean[rows][cols];
	private boolean flagged[][] = new boolean[rows][cols];

	//Variables needed during gameplay
	private boolean gameStarted = false;
	private int markedAmount = 0;
	private int openCells = 0;
	private int maxCells = rows * cols;
	private boolean rightClick = false, leftClick = false;
	private int time = 0;
	private int bombsFlagged = 10;
	private int timesPlayed = 0;
	
	//Load needed images
	private ImageIcon blank = new javax.swing.ImageIcon(getClass().getResource("images/blank.gif"));
	private ImageIcon bombFlagged = new javax.swing.ImageIcon(getClass().getResource("images/bombflagged.gif"));
	private ImageIcon bombRevealed = new javax.swing.ImageIcon(getClass().getResource("images/bombrevealed.gif"));
	private ImageIcon bombDeath = new javax.swing.ImageIcon(getClass().getResource("images/bombdeath.gif"));
	private ImageIcon bombMisflagged = new javax.swing.ImageIcon(getClass().getResource("images/bombmisflagged.gif"));
	private ImageIcon faceOoh = new javax.swing.ImageIcon(getClass().getResource("images/faceooh.gif"));
	private ImageIcon faceSmile = new javax.swing.ImageIcon(getClass().getResource("images/facesmile.gif"));
	private ImageIcon faceWin = new javax.swing.ImageIcon(getClass().getResource("images/facewin.gif"));
	private ImageIcon faceDead = new javax.swing.ImageIcon(getClass().getResource("images/facedead.gif"));
	private ImageIcon bordertb = new javax.swing.ImageIcon(getClass().getResource("images/bordertb.gif"));

	//Create labels for bomb counter, face, and timer
	private JLabel bombs1;
	private JLabel bombs2;
	private JLabel bombs3;
	private JLabel time1;
	private JLabel time2;
	private JLabel time3;
	private JButton face;

	//Creates the main game area
	public Minefield() {
		//Sets size of the main game area
		this.setSize(128, 164);
		
		//Calls the set up functions for each component of the main game area
		setUpMinefield();
		setUpBorder();
		setUpControls();
		
		//Sets up GridBagLayout to be used by the panel
		game = this;
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gameConstraints = new GridBagConstraints();
		game.setLayout(gridBag);
		
		gameConstraints.gridx = 0;
		
		//Adds control panel
		gameConstraints.gridy = 0;
		game.add(controlPanel, gameConstraints);
		
		//Adds border between control panel and minefield
		gameConstraints.gridy = 1;
		game.add(border, gameConstraints);
		
		//Adds minefield
		gameConstraints.gridy = 2;
		game.add(mines, gameConstraints);
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
	
	//Sets up the minefield
	private void setUpMinefield() {
		//Sets the JPanel to use GridBagLayout
		mines = new JPanel();
		mines.setSize(128, 128);
		GridBagLayout gridBag1 = new GridBagLayout();
		GridBagConstraints minefield = new GridBagConstraints();
		mines.setLayout(gridBag1);
		
		/*Populates the buttons array with JLabels, 
		*sets them to the blank.gif icon, 
		*gives them each a mouse listener, 
		*and adds them to the panel
		**/
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setIcon(blank);
				buttons[row][col].setBorder(BorderFactory.createEmptyBorder());
				
				buttons[row][col].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mousePressed(java.awt.event.MouseEvent e) {
						face.setIcon(faceOoh);
						if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
							rightClick = true;
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							leftClick = true;
						}
					}
					public void mouseReleased(java.awt.event.MouseEvent e) {
						face.setIcon(faceSmile);
						if (rightClick && leftClick) {
							doubleClick(e);
							rightClick = false;
							leftClick = false;
						} else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
							if (gameStarted == true) {
								markCell(e);
							}
							rightClick = false;
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							if (gameStarted == false) {
								startGame(e);
							}
							openCell(e);
							openAllMarked();
							leftClick = false;
						}
						if (openCells + BOMB_AMOUNT == maxCells) {
							win();
						}
					}
				});
				
				minefield.gridx = row;
				minefield.gridy = col;
				mines.add(buttons[row][col], minefield);
				
				flagged[row][col] = false;
				marked[row][col] = false;
				bomb[row][col] = false;
				shown[row][col] = false;
			}
		}
		
		this.setVisible(true);
	}
	
	//Sets up border
	private void setUpBorder() {		
		//Sets the JPanel to use GridBagLayout
		border = new JPanel();
		border.setSize(128, 10);
		GridBagLayout gridBag2 = new GridBagLayout();
		GridBagConstraints borderConstraints = new GridBagConstraints();
		border.setLayout(gridBag2);
		
		//Adds border pieces to make complete border
		borderConstraints.gridy = 0;
		for (int i = 0; i < 32; i++) {
			borderConstraints.gridx = i;
			border.add(new JLabel(bordertb), borderConstraints);
		}
	}
	
	//Sets up controls
	private void setUpControls() {
		//Sets JPanel to use GridBagLayout
		controlPanel = new JPanel();
		controlPanel.setSize(128, 26);
		GridBagLayout gridBag2 = new GridBagLayout();
		GridBagConstraints controls = new GridBagConstraints();
		controlPanel.setLayout(gridBag2);
		
		//Add bomb counter labels
		bombs1 = new JLabel();
		bombs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		controls.gridx = 0;
		controlPanel.add(bombs1, controls);

		bombs2 = new JLabel();
		bombs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time1.gif")));
		controls.gridx = 1;
		controlPanel.add(bombs2, controls);
		
		bombs3 = new JLabel();
		bombs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		controls.gridx = 2;
		controlPanel.add(bombs3, controls);
		
		//Load face button and adds ActionListener to it
		face = new JButton();
		face.setIcon(faceSmile);
		face.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
		ActionListener faceClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
					face.setIcon(faceSmile);
					gameStarted = false;
					time = 0;
					timer.stop();
					updateTime(0);
					newGame();
				}
			}
		};
		face.addActionListener(faceClick);
		controls.gridx = 3;
		controlPanel.add(face, controls);
		
		//Add timer icons
		time1 = new JLabel();
		time1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		controls.gridx = 4;
		controlPanel.add(time1, controls);
		
		time2 = new JLabel();
		time2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		controls.gridx = 5;
		controlPanel.add(time2, controls);
		
		time3 = new JLabel();
		time3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time0.gif")));
		controls.gridx = 6;
		controlPanel.add(time3, controls);
		
		//Sets JFrame to be visible
		controlPanel.setVisible(true);
	}

	//Starts the game the very first time
	private void startGame(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		
		gameStarted = true;
		timer.start();
		createBombs(row, col);
	}
	
	//Resets the game to be played again
	public void newGame() {
		face.setIcon(faceSmile);
		openCells = 0;
		bombsFlagged = BOMB_AMOUNT;
		setCounter(BOMB_AMOUNT);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				buttons[row][col].setIcon(blank);
				flagged[row][col] = false;
				marked[row][col] = false;
				bomb[row][col] = false;
				shown[row][col] = false;
			}
		}
	}
	
	//Updates the timer
	public void updateTime(int time) {
		int hundreds, tens, ones;
		hundreds = time / 100;
		tens = (time % 100) / 10;
		ones = ((time % 100) % 10);
		
		time1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + hundreds + ".gif")));
		time2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + tens + ".gif")));
		time3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + ones + ".gif")));
	}
	
	//Updates the counter by adding the change to the total
	public void updateCounter(int change) {
		bombsFlagged += change;
		setCounter(bombsFlagged);
	}
	
	//Sets the counter to a particular value
	public void setCounter(int num) {
		int hundreds, tens, ones;
		hundreds = num / 100;
		tens = (num % 100) / 10;
		ones = ((num % 100) % 10);
		
		bombs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + hundreds + ".gif")));
		bombs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + tens + ".gif")));
		bombs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/time" + ones + ".gif")));
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
	
	//Populates the field with bombs. First click will never be a bomb
	private void createBombs(int row, int col) {
		Random generator = new Random();
		int bombs = 0;
		
		while (bombs < BOMB_AMOUNT) {
			int r = generator.nextInt(rows);
			int c = generator.nextInt(cols);
			
			if (bomb[r][c] == false && r != row && r != row-1 && r!= row+1 && c != col && c != col-1 && c != col+1) {
				bomb[r][c] = true;
				bombs++;
			}
		}
	}
	
	//Marks cell with a flag if blank, or blank if flagged
	private void markCell(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;

		if (!shown[row][col]) {
			if (!flagged[row][col]) {
				flagged[row][col] = true;
				button.setIcon(bombFlagged);
				updateCounter(-1);
			} else {
				flagged[row][col] = false;
				button.setIcon(blank);
				updateCounter(1);
			}	
		}
	}
	
	//Handle right and left click at the same time. Clears board accordingly
	private void doubleClick(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		boolean bombPresent = false;
		
		for (int x = row-1; x <= row+1; x++) {
			for (int y = col-1; y <= col+1; y++) {
				boolean inBoard = x >= 0 && x < buttons.length && y >= 0 && y < buttons[x].length;
				if (inBoard) {
					if (bomb[x][y] && !flagged[x][y]) {
						bombPresent = true;
					}	
				}
			}
		}
		if (!bombPresent) {
			for (int x = row-1; x <= row+1; x++) {
				for (int y = col-1; y <= col+1; y++) {
					boolean inBoard = x >= 0 && x < buttons.length && y >= 0 && y < buttons[x].length;
					if (inBoard) {
						if (!shown[x][y] && !bomb[x][y] && !flagged[x][y]) {
							openCell(x, y);
							openAllMarked();
						}	
					}
				}
			}
		}
	}
	
	//Marks clicked cell to be opened and calls openNeighbors if no bombs around it
	private void openCell(java.awt.event.MouseEvent e) {
		JLabel button = (JLabel)e.getSource();
		int row = button.getX() / 16;
		int col = button.getY() / 16;
		int bombAmount = bombCount(row, col);
		if (isBomb(row, col)) {
			death(row, col);
		} else {
			if (!shown[row][col]) {
				markCellToOpen(row, col);
				shown[row][col] = true;
				if (bombAmount == 0) {
					openNeighbors(row, col);
				}
			}
		}
	}
	
	//Same as above but called with coordinates
	private void openCell(int row, int col) {
		int bombAmount = bombCount(row, col);
		if (isBomb(row, col)) {
			death(row, col);
		} else {
			if (!shown[row][col]) {
				markCellToOpen(row, col);
				shown[row][col] = true;
				if (bombAmount == 0) {
					openNeighbors(row, col);
				}
			}
		}
	}
	
	//Marks surrounding cells to be opened and calls openCell on them again to keep opening
	private void openNeighbors(int row, int col) {
		for (int i = row-1; i <= row+1; i++) {
			for (int j = col-1; j <= col+1; j++) {
				if (i >= 0 && i < buttons.length && j >= 0 && j < buttons[i].length) {
					if (!shown[i][j] && !flagged[i][j]) {
						markCellToOpen(i, j);
						openCell(i, j);
					}
				}
			}
		}
	}
	
	//Marks a cell to be opened
	private void markCellToOpen(int row, int col) {
		markedAmount++;
		marked[row][col] = true;
	}
	
	//Opens all marked cells
	private void openAllMarked() {
		while (markedAmount > 0) {
			markedAmount--;
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					if (marked[row][col] == true) {
						int bombAmount = bombCount(row, col);
						marked[row][col] = false;
						buttons[row][col].setIcon(new javax.swing.ImageIcon(getClass().getResource("images/open" + bombAmount + ".gif")));
						openCells++;
					}
				}
			}
		}
	}
	
	//Returns true if cell is a bomb
	private boolean isBomb(int row, int col) {
		return bomb[row][col];
	}
	
	//Handles victory, flags all bombs
	private void win() {
		if (timesPlayed >= 1) {
			controlPanel.setVisible(false);
			border.setVisible(false);
			mines.setVisible(false);
			Secret prom = new Secret();
			game.add(prom);
		}
		timer.stop();
		face.setIcon(faceWin);
		bombsFlagged = 0;
		setCounter(0);
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (bomb[row][col]) {
					buttons[row][col].setIcon(bombFlagged);
				}
			}
		}
		timesPlayed++;
	}
	
	//Handles death, shows clicked bomb, misflagged bombs, and all other bombs
	private void death(int row, int col) {
		face.setIcon(faceDead);
		timer.stop();
		gameStarted = false;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (flagged[r][c] && !bomb[r][c]) {
					buttons[r][c].setIcon(bombMisflagged);
				}
				if (bomb[r][c]) {
					if (r == row && c == col) {
						buttons[r][c].setIcon(bombDeath);
					} else if (bomb[r][c]) {
						buttons[r][c].setIcon(bombRevealed);
					}
				}
			}
		}
		timesPlayed++;
	}
}
